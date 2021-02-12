public class Effector {
    private Agent agent;

    public Effector(Agent agent){
        this.agent = agent;
    }

    public void move(Box box){
        this.agent.getEnvironment().getManor().getCase(box.getPosition_x(), box.getPosition_y()).setAgent(true);
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y()).setAgent(false);
        agent.setCurrentPosition(box);
    }

    //verif si il n'y a pas de bijoux avant d'aspirer
    public void vacuum(){
        agent.getCurrentPosition().setDust(false);
        agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(),agent.getCurrentPosition().getPosition_y()).setDust(false);
        if (agent.getCurrentPosition().getJewelry()){
            agent.setScore(-2);
            agent.getCurrentPosition().setJewelry(false);
            agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(),agent.getCurrentPosition().getPosition_y()).setJewelry(false);
        }
        else {
            agent.setScore(1);
        }

    }
    public void pick(){
        agent.setScore(1);
        agent.getCurrentPosition().setJewelry(false);
        agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(),agent.getCurrentPosition().getPosition_y()).setJewelry(false);
    }
}
