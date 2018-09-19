package GameController;

import Model.BoardCell;
import Model.GameBoard;
import Model.GameModel;
import Model.GameModel.State;
import Model.TicTacState;
import View.WindowController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class Controller {
    private WindowController windowController;
    private GameBoard gameBoard;
    private GameModel gameModel;
    private SimpleObjectProperty<State> currentState;
    private AIService aiserv;
    private boolean boardLock;
    private int x,y;

    public Controller(WindowController windowController) {
        this.windowController = windowController;
        currentState = new SimpleObjectProperty<>(State.INIT);
        initAIService();
        addStateListener();
        this.gameBoard = new GameBoard(event -> {
            if (boardLock)
                return;
            BoardCell source = (BoardCell)event.getSource();
            if (source.wasUsed()) {
                this.windowController.setInfo("To pole zostało wykorzystane. Wybierz inne");
                return;
            }
            boardLock = true;
            x = source.getXCoordinate();
            y = source.getYCoordinate();
            TicTacState newState = new TicTacState(gameModel.getState());
            newState.setHumanMove(5*y+x);
            newState.print();
            gameBoard.repaint(x,y,gameModel.getPlayer(0).getPlayerColor());
            makeMove(newState);

        });
        this.gameModel = new GameModel();
        this.windowController.setBoardProperty(gameBoard);
        this.windowController.setInfo("Witaj w grze kółko i krzyżyk. Aby zacząć wciśnij przycisk \"Rozpocznij grę\"");
    }

    public void initGame() {
        resetBoard();
        gameModel.initGame();
        boardLock = false;
    }

    public void addStateListener() {
        this.currentState.addListener((observable, oldValue, newValue) -> {
            if(newValue == State.TIE) {
                this.windowController.setInfo("Gra zakończyła się remisem");
                boardLock = true;
            }
            else if (newValue == State.PLAYER_1_WIN || newValue == State.PLAYER_2_WIN)
                System.out.println("Ktoś wygrał");
            else {
                if (newValue == State.PLAYER_1_MOVE || newValue == State.PLAYER_2_MOVE) {
                    System.out.println("Zmienione old:"+oldValue+" new:"+newValue);
                    if (!gameModel.getCurrentPlayer().isHuman())
                        makeAIMove();
                    else
                        this.windowController.setInfo("Kliknij na pole aby wykonać ruch");
                }
            }
        });
    }

    public void initAIService(){
        this.aiserv = new AIService();
        this.aiserv.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                this.gameModel.setState((TicTacState) newValue);
                ((TicTacState) newValue).print();
                gameBoard.repaint(gameModel.getState().getX(),gameModel.getState().getY(),gameModel.getPlayer(1).getPlayerColor());
                boardLock = false;
                updateState();
            }
        });
    }

    public void restart() {
        initGame();
        startGame();
    }

    public void startGame() {
        initGame();
        this.gameModel.initCurrent();
        updateState();
    }

    public void makeAIMove(){
        System.out.println("Jestem tu");
        this.aiserv.setState(this.gameModel.getState());
        this.aiserv.setAIDepth(this.gameModel.getCurrentPlayer().getAIDepth());
        this.aiserv.restart();
    }

    public void makeMove(TicTacState next) {
        this.gameModel.setState(next);
        System.out.println("Przed "+this.currentState.get());
        updateState();
        System.out.println("Po "+this.currentState.get());
    }

    private void updateState(){
        this.currentState.set(gameModel.getCurrentMove());
    }

    public void resetBoard() {
        gameBoard.resetBoard();
    }
}
