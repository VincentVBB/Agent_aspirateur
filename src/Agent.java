public class Agent {
    private int energy;
    private int score;
    private Box currentPosition;
    private Captor captor;
    private Effector effector;
    private Environment environment;

    public Agent(Environment environment) {
        this.environment = environment;
        this.captor = new Captor();
        this.energy = 0;
        this.score = 0;
        this.currentPosition = environment.getManor().getCase(0,0);
        environment.getManor().getCase(0,0).setAgent(true);
        this.effector = new Effector(this);
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


}
