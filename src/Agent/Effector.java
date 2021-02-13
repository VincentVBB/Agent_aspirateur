package Agent;

import Environment.*;

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

    public void move_left(){
        System.out.println("Moving left");
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y()).setAgent(false);
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x() - 1, agent.getCurrentPosition().getPosition_y()).setAgent(true);
        agent.setCurrentPosition(this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x() - 1, agent.getCurrentPosition().getPosition_y()));
    }
    public void move_right(){
        System.out.println("Moving right");
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y()).setAgent(false);
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x() + 1, agent.getCurrentPosition().getPosition_y()).setAgent(true);
        agent.setCurrentPosition(this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x() + 1, agent.getCurrentPosition().getPosition_y()));
    }
    public void move_up(){
        System.out.println("Moving up");
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y()).setAgent(false);
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y() - 1).setAgent(true);
        agent.setCurrentPosition(this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y() - 1));
    }
    public void move_down(){
        System.out.println("Moving down");
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y()).setAgent(false);
        this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y() + 1).setAgent(true);
        agent.setCurrentPosition(this.agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(), agent.getCurrentPosition().getPosition_y() + 1));
    }


    //verif si il n'y a pas de bijoux avant d'aspirer
    public void vacuum(){
        System.out.println("J'aspire la poussière");
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
        System.out.println("Je ramasse un bijoux");
        agent.setScore(1);
        agent.getCurrentPosition().setJewelry(false);
        agent.getEnvironment().getManor().getCase(agent.getCurrentPosition().getPosition_x(),agent.getCurrentPosition().getPosition_y()).setJewelry(false);
    }
}
