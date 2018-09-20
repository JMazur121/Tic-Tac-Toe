package Model;

public class GameModel {
    private TicTacState gameState;
    private State currentMove;
    private Player[] players;
    private Player currentPlayer;
    private int fieldsLeft;

    public void initGame() {
        currentMove = State.PLAYER_1_MOVE;
        gameState = new TicTacState();
        fieldsLeft = 25;
        initCurrent();
    }

    public GameModel() {
        players = new Player[2];
        this.players[0] = new Player();
        this.players[1] = new Player(7);
    }

    public void initCurrent(){
        this.currentPlayer = this.players[0];
    }

    private void updateGameState() {
        if (fieldsLeft == 0) {
            currentMove = State.TIE;
            return;
        }
        if (currentMove == State.PLAYER_1_MOVE) {
            if (gameState.isTerminated())
                currentMove = State.PLAYER_1_WIN;
            else {
                currentMove = State.PLAYER_2_MOVE;
                currentPlayer = players[1];
            }
        } else {
            if (gameState.isTerminated())
                currentMove = State.PLAYER_2_WIN;
            else {
                currentMove = State.PLAYER_1_MOVE;
                currentPlayer = players[0];
            }
        }
    }

    public TicTacState getState() {
        return gameState;
    }

    public void setState(TicTacState nextState) {
        gameState = nextState;
        fieldsLeft -= 1;
        updateGameState();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public State getCurrentMove() {
        return currentMove;
    }

    public boolean isFinished() {
        return gameState.isTerminated();
    }

    public enum State {PLAYER_1_MOVE, PLAYER_2_MOVE, PLAYER_1_WIN, PLAYER_2_WIN,INIT,TIE}

    public Player getPlayer(int index){
        return this.players[index];
    }
}
