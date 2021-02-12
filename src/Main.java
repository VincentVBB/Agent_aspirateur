import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Environment environment = new Environment(new Manor(5),true);
        Agent agent = new Agent(environment);
        while (true){

            environment.makeRandomDust();
            environment.makeRandomJewelry();


            Thread.sleep(2000);
            System.out.println("--------------------------------------------");
        }
    }
}
