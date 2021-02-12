
public class Map {
    private Box[][] matrix;
    private int size;

    public Map(int size) {
        this.size = size;
        this.matrix = new Box[size][size];
        for (int x = 0; x<size; x++ ){
            for (int y = 0; y<size; y++){
                this.matrix[x][y] = new Box(x,y);
            }
        }
    }

    public Map(Box[][] matrix, int size) {
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
}
