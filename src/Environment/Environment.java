package Environment;
import Agent.*;

import java.util.Random;

public class Environment implements Runnable {
    private Manor manor;

    public Environment(Manor manor) {
        this.manor = manor;
    }

    public Manor getManor() {
        return manor;
    }

    public void setManor(Manor manor) {
        this.manor = manor;
    }

    public void makeRandomDust(){
        if (genererInt(1,100)<=30){
            int x = genererInt(0,manor.getSize());
            int y = genererInt(0,manor.getSize());
            this.manor.getCase(x,y).setDust(true);
        }
    }

    public void makeRandomJewelry(){
        if (genererInt(1,100)<=30){
            int x = genererInt(0,manor.getSize());
            int y = genererInt(0,manor.getSize());
            this.manor.getCase(x,y).setJewelry(true);
        }
    }


    private int genererInt(int borneInf, int borneSup){
        Random random = new Random();
        return borneInf+random.nextInt(borneSup-borneInf);
    }

    @Override
    public void run() {
        while (true){
            makeRandomDust();
            makeRandomJewelry();

            manorUI();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void manorUI(){
        clearScreen();
        System.out.println("---------------------------------");
        for (int x = 0; x<this.getManor().getSize(); x++ ){
            String line = "";
            for (int y = 0; y<this.getManor().getSize(); y++){
                if (this.getManor().getCase(x,y).getAgent()){
                    line += "X";
                }
                else {
                    if (this.getManor().getCase(x,y).getDust() && this.getManor().getCase(x,y).getJewelry()){
                        line += "B";
                    }
                    else if (this.getManor().getCase(x,y).getJewelry()){
                        line += "J";
                    }
                    else if (this.getManor().getCase(x,y).getDust()){
                        line += "D";
                    }
                    else {
                        line += "*";
                    }
                }
            }
            System.out.println(line);
        }
        System.out.println("--------------------------------");

    }

    public final static void clearConsole()
    {

        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
                System.out.flush();
            }

        }

        catch (final Exception e)
        {
            System.out.println(e);
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
