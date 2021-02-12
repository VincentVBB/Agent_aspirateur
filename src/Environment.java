import java.util.Random;

public class Environment {
    private Map map;
    private Boolean isRunning;

    public Environment(Map map, Boolean isRunning) {
        this.map = map;
        this.isRunning = isRunning;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void makeRandomDust(){
        this.map.getCase(genererInt(0,5), genererInt(0,5));
    }


    private int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        return borneInf+random.nextInt(borneSup-borneInf);
    }
}
