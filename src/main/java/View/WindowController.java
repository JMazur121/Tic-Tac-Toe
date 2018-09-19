package View;

import GameController.Controller;
import Model.GameBoard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable{
    @FXML
    private AnchorPane boardPane;
    @FXML
    private ListView<String> list;

    private Controller gameController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = new Controller(this);
    }

    public void setBoardProperty(GameBoard board) {
        boardPane.getChildren().add(board);
    }

    public void setInfo(String info) {
        list.getItems().clear();
        list.getItems().add(info);
    }

    @FXML
    private void onStartPressed(ActionEvent event) {
        gameController.startGame();
    }

    @FXML
    private void onStopPressed(ActionEvent event) {}

    @FXML
    private void onExitPressed(ActionEvent event) {
        System.exit(0);
    }
}
