package GameController;

import AIAlgorithm.*;
import Model.TicTacState;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class AIService extends Service<GameState> {

    private GameState state;
    private int AIDepth;

    public void setState(TicTacState state) {
        this.state = state;
    }

    public void setAIDepth(int AIDepth) {
        this.AIDepth = AIDepth;
    }

    @Override
    protected Task<GameState> createTask() {
        return new Task<GameState>() {
            @Override
            protected GameState call() throws Exception {
                return Algorithm.bestMove(state,AIDepth);
            }
        };
    }
}
