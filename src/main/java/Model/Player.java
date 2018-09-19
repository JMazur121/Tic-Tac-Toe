package Model;

import javafx.scene.paint.Color;

public class Player {

    private int AIDepth;
    private Character pchar;
    private Color playerColor;

    public Player() {
        this.AIDepth = 0;
        this.pchar = Character.HUMAN;
        this.playerColor = Color.RED;
    }

    public Player(int AIDepth) {
        this.AIDepth = AIDepth;
        this.pchar = Character.AI;
        this.playerColor = Color.AQUAMARINE;
    }

    public int getAIDepth() {
        return AIDepth;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public boolean isHuman(){
        return this.pchar == Character.HUMAN;
    }

    public enum Character {HUMAN, AI, NONE}
}
