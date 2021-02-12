import java.util.Random;

public class Environment {
    private Manor manor;
    private Boolean isRunning;

    public Environment(Manor manor, Boolean isRunning) {
        this.manor = manor;
        this.isRunning = isRunning;
    }

    public Manor getManor() {
        return manor;
    }

    public void setManor(Manor manor) {
        this.manor = manor;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void makeRandomDust(){
        if (genererInt(1,100)<=30){
            int x = genererInt(0,manor.getSize());
            int y = genererInt(0,manor.getSize());
            this.manor.getCase(x,y).setDust(true);
            System.out.println("New dust in :" + x + " , "+ y);
        }
    }

    public void makeRandomJewelry(){
        if (genererInt(1,100)<=30){
            int x = genererInt(0,manor.getSize());
            int y = genererInt(0,manor.getSize());
            this.manor.getCase(x,y).setJewelry(true);
            System.out.println("New Jewelry in :" + x + " , "+ y);
        }
    }


    private int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        return borneInf+random.nextInt(borneSup-borneInf);
    }
}
