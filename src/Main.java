import Agent.*;
import Environment.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Boolean run = true;

        while (run){
            System.out.println("Welcome to the intelligent Agent");
            System.out.println("He was named 'VacuumCleanerMax'");

            System.out.println("Please, choose the desired exploration\n\t1) Exploration BFS\n\t2) Exploration A*");
            System.out.println("1 or 2 : ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            int choice_number = Integer.parseInt(choice.trim());

            if (choice_number != 1 && choice_number != 2) {
                System.out.println("This choice isn't correct !");
                continue;
            }
            run = false;

            Agent.Exploration exploration = Agent.Exploration.BFS;

            if (choice_number == 2) {
                exploration = Agent.Exploration.A_STAR;
            }
            Environment environment = new Environment(new Manor(5));
            Thread environmentThread = new Thread(environment);
            Thread agentThread = new Thread(new Agent(environment, exploration));
            environmentThread.start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            agentThread.start();
        }
    }
}
