package Environment;
import Agent.*;

import java.util.ArrayList;

public class Manor {
    private Box[][] matrix;
    private int size;

    public Manor(int size) {
        this.size = size;
        this.matrix = new Box[size][size];
        for (int x = 0; x<size; x++ ){
            for (int y = 0; y<size; y++){
                this.matrix[x][y] = new Box(x,y);
            }
        }
    }

    public Manor(Box[][] matrix, int size) {
        this.matrix = matrix;
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public Box getCase(int x, int y){
        return this.matrix[x][y];
    }

    public void setCase(Box newBox, int x, int y){
        this.matrix[x][y] = newBox;
    }

    public int getDistance(Box box1, Box box2){
        return Math.abs(box2.getPosition_x() - box1.getPosition_x()) + Math.abs(box2.getPosition_y() - box1.getPosition_y());
    }
    public Box[][] getMatrix(){
        return this.matrix;
    }

    public ArrayList<Box> getNeighbors(Box currentBox){
        ArrayList<Box> neighbors = new ArrayList<>();
        //if currentBox have left neighbor
        if (currentBox.getPosition_x() > 0){
            neighbors.add(matrix[currentBox.getPosition_x() - 1][currentBox.getPosition_y()]);
        }
        //if currentBox have right neighbor
        if (currentBox.getPosition_x() < size-1){
            neighbors.add(matrix[currentBox.getPosition_x() + 1][currentBox.getPosition_y()]);
        }
        //if currentBox have up neighbor
        if (currentBox.getPosition_y() > 0){
            neighbors.add(matrix[currentBox.getPosition_x()][currentBox.getPosition_y() - 1]);
        }
        //if currentBox have down neighbor
        if (currentBox.getPosition_y() < size-1){
            neighbors.add(matrix[currentBox.getPosition_x()][currentBox.getPosition_y() + 1]);
        }
        return neighbors;
    }

    //regarde si la case 1 et au dessus de la case 2
    public boolean isAbove(Box box1, Box box2){
        return box1.getPosition_y() < box2.getPosition_y();
    }

    //regarde si la case 1 et en dessous de la case 2
    public boolean isBellow(Box box1, Box box2){
        return box1.getPosition_y() > box2.getPosition_y();
    }

    //regarde si la case 1 et a droite de la case 2
    public boolean isAtRight(Box box1, Box box2){
        return box1.getPosition_x() > box2.getPosition_x();
    }

    //regarde si la case 1 et a gauche de la case 2
    public boolean isAtLeft(Box box1, Box box2){
        return box1.getPosition_x() < box2.getPosition_x();
    }

}
