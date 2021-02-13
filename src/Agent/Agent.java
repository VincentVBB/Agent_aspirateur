package Agent;

import Environment.*;
import b.b.m.B;

import java.util.*;

public class Agent {
    private int energy;
    private int score;
    private Box currentPosition;
    private Captor captor;
    private Effector effector;
    private Environment environment;
    private Exploration exploration_type;

    //Liste des actions possibles a effectuer par l'agent
    public enum Action {
        PICK,
        VACUUM,
        MOVE_LEFT,
        MOVE_RIGHT,
        MOVE_UP,
        MOVE_DOWN,
    }

    //Selon le type d'eploration en vigueur (informée ou non informée), on aura recours a l'algorithme BFS ou A*
    public enum Exploration{
        BFS,
        A_STAR
    }

    public Agent(Environment environment) {
        this.environment = environment;
        this.captor = new Captor(environment);
        this.energy = 0;
        this.score = 0;
        this.currentPosition = environment.getManor().getCase(0,0);
        environment.getManor().getCase(0,0).setAgent(true);
        this.effector = new Effector(this);
        this.exploration_type = Exploration.A_STAR;
    }

    public void increaseEnergy(){
        this.energy += 1;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int points) {
        this.score += points;
    }

    public Box getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Box currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Captor getCaptor() {
        return captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }

    public Effector getEffector() {
        return effector;
    }

    public void setEffector(Effector effector) {
        this.effector = effector;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void runAgent(){

        ArrayList<Box> beliefs = getCaptor().getBelief();
        Box desire = getDesire(beliefs);
        Stack<Agent.Action> intentions = getIntention(getEnvironment().getManor(), desire);
        while (!intentions.isEmpty()){
            Action action = intentions.pop();
            makeAction(action);
        }

    }



    public void makeAction(Action action){
        if(action == null) { return; }
        switch (action){
            case PICK:
                this.effector.pick();
                break;
            case VACUUM:
                this.effector.vacuum();
                break;
            case MOVE_LEFT:
                this.effector.move_left();
                break;
            case MOVE_RIGHT:
                this.effector.move_right();
                break;
            case MOVE_UP:
                this.effector.move_up();
                break;
            case MOVE_DOWN:
                this.effector.move_down();
                break;
            default:
                return;
        }

    }

    public Box getDesire(ArrayList<Box> beliefs){
        Box bestBox = null;
        double bestPerformance = Integer.MIN_VALUE;

        for(Box box: beliefs) {
            double currentPerformance = getScoreBox(box);
            if(currentPerformance > bestPerformance)  {
                bestBox = box;
                bestPerformance = currentPerformance;
            }
        }
        if (bestBox != null){
            System.out.println("Meilleure case | x : " + bestBox.getPosition_x() + " y : " + bestBox.getPosition_y());

        }
        return bestBox;
    }
    
    private double getScoreBox(Box box) {
        int scoreBox = 0;
        int costAction = 0;
        if (box.getDust()){
            scoreBox += 1;
            costAction += 1;
        }
        if (box.getJewelry()){
            scoreBox += 1;
            costAction += 1;
        }
        double costDistance = this.environment.getManor().getDistance(currentPosition,box);
        double energy = costDistance + costAction;
        return scoreBox - energy;
    }

    public Stack<Action> getIntention(Manor manor, Box desiredBox){
        if(desiredBox == null){
            return new Stack<>();
        }
        return explore(manor, desiredBox);
    }

    public Stack<Action> explore(Manor manor, Box desiredBox){
        Stack<Action> actions = null;
        switch (this.exploration_type){
            //exploration non informee
            case BFS:
                actions = bFS(desiredBox);
                break;
            //exploration informee
            case A_STAR:
                actions = a_STAR(desiredBox);
                break;
        }
        return actions;
    }

    protected Stack<Action> bFS(Box desiredBox){

        //<enfant,parent>
        Map<Box,Box> parent = new HashMap<Box, Box>();
        Box startBox = this.getCurrentPosition();

        //on cree la frontiere
        LinkedList<Box> border = new LinkedList<Box>();
        border.add(startBox);

        //on repertorie les cases deja visitee
        LinkedList<Box> explored = new LinkedList<Box>();
        explored.add(startBox);
        parent.put(startBox,null);

        while (!border.isEmpty()){
            //on recupere le 1er element et on l enleve de la frontiere
            Box currentBox = border.poll();

            if (currentBox == desiredBox){
                break;
            }

            List<Box> neighbors = getEnvironment().getManor().getNeighbors(currentBox);
            for (Box box: neighbors){
                if (explored.contains(box)){
                    continue;
                }
                border.add(box);
                parent.put(box,currentBox);
                explored.add(box);
            }
        }

        Stack<Box> paths = new Stack<Box>();
        Box end = desiredBox;

        while (end != null){
            paths.push(end);
            end = parent.get(end);
        }

        Stack<Action> actions = getActions(paths);
        return actions;
    }

    protected Stack<Action> getActions(Stack<Box> path){

        List<Action> actionPath = new ArrayList<Action>();
        while (!path.isEmpty() && path.size() >= 2){
            Box box = path.pop();
            Box nextBox = path.peek();
            if (this.getEnvironment().getManor().isAbove(box,nextBox)){
                actionPath.add(0,Action.MOVE_DOWN);
            }
            if (this.getEnvironment().getManor().isBellow(box,nextBox)){
                actionPath.add(0,Action.MOVE_UP);
            }
            if (this.getEnvironment().getManor().isAtRight(box,nextBox)){
                actionPath.add(0,Action.MOVE_LEFT);
            }
            if (this.getEnvironment().getManor().isAtLeft(box,nextBox)){
                actionPath.add(0,Action.MOVE_RIGHT);
            }
        }

        Stack<Action> actions = new Stack<Action>();

        // Ajoute l'action finale, soit aspirer, soit ramasser soit les deux !
        if (!actionPath.isEmpty() || path.size() == 1){
            Box desiredBox = path.get(path.size() -1);
            if (desiredBox.getDust()){
                actions.push(Action.VACUUM);
            }
            if (desiredBox.getJewelry()){
                actions.push(Action.PICK);
            }

            while (!actionPath.isEmpty()){
                actions.push(actionPath.get(0));
                actionPath.remove(0);
            }

        }
        return actions;
    }

    protected Stack<Action> a_STAR(Box desiredBox){
        List<Box> openList = new LinkedList<Box>();
        Map<Box, Box> parent = new HashMap<>();

        Map<Box, Double> gCost = new HashMap<>();
        Map<Box, Double> fCost = new HashMap<>();
        Box start = this.getCurrentPosition();

        gCost.put(start, 0.0);
        fCost.put(start, this.environment.getManor().getDistance(start,desiredBox));

        openList.add(start);

        while (!openList.isEmpty()){
            Box currentBox = findBestScoreBox(openList, fCost);
            if (currentBox == desiredBox){
                return getActions(getHistoricPath(parent,currentBox));
            }
            openList.remove(currentBox);

            List<Box> neighbors = this.getEnvironment().getManor().getNeighbors(currentBox);
            for(Box box : neighbors){
                Double score = gCost.getOrDefault(currentBox,Double.MAX_VALUE)+1;
                if (score < gCost.getOrDefault(box, Double.MAX_VALUE)){
                    parent.put(box,currentBox);
                    gCost.put(box,score);
                    fCost.put(box, gCost.get(box) + this.getEnvironment().getManor().getDistance(box,desiredBox));
                    if(!openList.contains(box)){
                        openList.add(box);
                    }
                }
            }
        }
        return new Stack<>();
    }

    private Box findBestScoreBox(List<Box> openList, Map<Box,Double> fCost){
        Double bestScore = Double.MAX_VALUE;
        Box bestBox = openList.get(0);
        for (Box box: openList) {
            Double score_box = fCost.get(box);
            if (score_box < bestScore){
                bestBox = box;
                bestScore = score_box;
            }
        }

        return bestBox;
    }

    private Stack<Box> getHistoricPath(Map<Box, Box> parent, Box currentBox){
        Stack<Box> path = new Stack<Box>();
        path.push(currentBox);
        while (parent.keySet().contains(currentBox)){
            currentBox = parent.get(currentBox);
            path.push(currentBox);
        }
        return path;
    }

}
