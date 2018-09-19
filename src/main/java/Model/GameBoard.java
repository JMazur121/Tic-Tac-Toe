package Model;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameBoard extends Parent {
    private VBox rows = new VBox();
    private static final int boardsize = 5;

    public GameBoard(EventHandler<? super MouseEvent> mouseClickHandler) {
        for (int y = 0; y < boardsize; y++) {
            HBox row = new HBox();
            for (int x = 0; x < boardsize; x++) {
                BoardCell cell = new BoardCell(x, y);
                cell.setOnMouseClicked(mouseClickHandler);
                row.getChildren().add(cell);
            }
            rows.getChildren().add(row);
        }
        this.getChildren().add(rows);
    }

    public BoardCell getCell(int x, int y){
        return (BoardCell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    public void resetBoard() {
        for (int i = 0; i < boardsize; i++)
            for (int j = 0; j < boardsize ; j++)
                getCell(i,j).cleanCell();
    }

    public void repaint(int x, int y, Color color){
        BoardCell cell = getCell(x,y);
        cell.setColorAndUsed(color);
    }
}
