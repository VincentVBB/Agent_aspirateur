import Agent.*;
import Environment.*;

import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Environment environment = new Environment(new Manor(5),true);
        Agent agent = new Agent(environment);
        while (true){

            environment.makeRandomDust();
            environment.makeRandomJewelry();
            agent.setEnvironment(environment);

            agent.runAgent();


            Thread.sleep(2000);
            System.out.println("--------------------------------------------");
        }
    }
}
