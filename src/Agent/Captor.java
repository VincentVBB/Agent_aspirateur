package Agent;

import Environment.*;

import java.util.ArrayList;

public class Captor {
    Environment environment;

    public Captor(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    //return all boxes to clean
    public ArrayList<Box> getBelief(){
        Manor manor = environment.getManor();
        ArrayList<Box> boxesToClean = new ArrayList<>();
        for (int x = 0; x<manor.getSize(); x++){
            for (int y = 0; y<manor.getSize(); y++){
                if (manor.getCase(x,y).toClean()){
                    boxesToClean.add(manor.getCase(x,y));
                }
            }
        }
        return boxesToClean;
    }
}
