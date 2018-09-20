package Model;

import AIAlgorithm.GameState;
import Model.Player.Character;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacState extends GameState {

    private Character board[];
    private int x, y;

    public TicTacState() {
        board = new Character[25];
        Arrays.fill(board, Character.NONE);
    }

    public TicTacState(TicTacState other) {
        this.board = Arrays.copyOfRange(other.board, 0, other.board.length);
    }

    public void setHumanMove(int index) {
        board[index] = Character.HUMAN;
    }

    @Override
    public int valuate() {
        int val = 12;
        for (int row = 0; row < 25; row += 5) {
            if ((board[row] == Character.HUMAN) || (board[row + 1] == Character.HUMAN) || (board[row + 2] == Character.HUMAN)
                    || (board[row + 3] == Character.HUMAN) || (board[row + 4] == Character.HUMAN))
                val--;
        }
        for (int col = 0; col < 5; col++) {
            if ((board[col] == Character.HUMAN) || (board[col + 5] == Character.HUMAN) || (board[col + 10] == Character.HUMAN)
                    || (board[col + 15] == Character.HUMAN) || (board[col + 20] == Character.HUMAN))
                val--;
        }
        if ((board[0] == Character.HUMAN) || (board[6] == Character.HUMAN) || (board[12] == Character.HUMAN) ||
                (board[18] == Character.HUMAN) || (board[24] == Character.HUMAN))
            val--;
        if ((board[4] == Character.HUMAN) || (board[8] == Character.HUMAN) || (board[12] == Character.HUMAN) ||
                (board[16] == Character.HUMAN) || (board[20] == Character.HUMAN))
            val--;
        return val;
    }

    @Override
    public GameState[] getPossibleStates(boolean isMaximizingPlayer) {
        ArrayList<GameState> gameStates = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            if (board[i] == Character.NONE) {
                TicTacState newState = new TicTacState(this);
                if (isMaximizingPlayer)
                    newState.board[i] = Character.AI;
                else
                    newState.board[i] = Character.HUMAN;
                newState.x = i % 5;
                newState.y = (i - x) / 5;
                gameStates.add(newState);
            }
        }
        GameState[] states = new GameState[gameStates.size()];
        return gameStates.toArray(states);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //todo naprawic sprawdzanie wygranej
    @Override
    public boolean isTerminated() {
        if (checkRows())
            return true;
        else if (checkColumns())
            return true;
        else
            return checkDiagonals();
    }

    private boolean checkRows() {
        for (int i = 0; i < 25; i += 5) {
            Character character = board[i];
            if (character == Character.NONE)
                continue;
            if ((board[i + 1] == character) && (board[i + 2] == character) && (board[i + 3] == character) && (board[i + 4] == character))
                return true;
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < 5; i++) {
            Character character = board[i];
            if (character == Character.NONE)
                continue;
            if ((board[i + 5] == character) && (board[i + 10] == character) && (board[i + 15] == character) && (board[i + 20] == character))
                return true;
        }
        return false;
    }

    private boolean checkDiagonals() {
        Character character = board[0];
        if (character == Character.NONE)
            return false;
        if ((board[6] == character) && (board[12] == character) && (board[18] == character) && (board[24] == character))
            return true;
        character = board[4];
        if (character == Character.NONE)
            return false;
        return (board[8] == character) && (board[12] == character) && (board[16] == character) && (board[20] == character);
    }

    public void print() {
        for (int i = 0, j = 0; i < 25; i++, j++) {
            if (j == 5) {
                System.out.println();
                j = 0;
            }
            if (board[i] == Character.NONE)
                System.out.print("N");
            else if (board[i] == Character.AI)
                System.out.print("A");
            else
                System.out.print("H");
        }
        System.out.println();
    }

}
