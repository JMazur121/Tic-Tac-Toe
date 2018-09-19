package Model;

import Util.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCell extends Rectangle {
    private final Point position;
    private boolean wasUsed = false;

    public BoardCell(int x, int y){
        super(100,100);
        position = new Point(x,y);
        this.setFill(Color.WHITE);
        this.setStroke(Color.BLACK);
    }

    public boolean wasUsed(){
        return this.wasUsed;
    }

    public void setColorAndUsed(Color fill){
        this.setFill(fill);
        this.wasUsed = true;
    }

    public void cleanCell(){
        this.wasUsed = false;
        this.setFill(Color.WHITE);
    }

    public int getXCoordinate() {
        return position.getX();
    }

    public int getYCoordinate() {
        return position.getY();
    }

}
