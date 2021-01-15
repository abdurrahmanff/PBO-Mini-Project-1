package id.ac.its.aff231yz160zlp118.snake;

import java.io.Serializable;
import java.util.Vector;

public class HighScore implements Serializable {
    private Vector<Integer> highScore = new Vector<>();

    public HighScore() {
        for(int i=0; i<LevelSelector.getBanyakLevel(); i++)
            highScore.add(0);
    }

    public void checkForBanyakLevelChanges() {
        if(LevelSelector.getBanyakLevel() > highScore.size()) {
            for(int i=0; i < LevelSelector.getBanyakLevel() - highScore.size(); i++) {
                highScore.add(0);
            }
        } else if(LevelSelector.getBanyakLevel() < highScore.size()) {
            int currentSize = highScore.size();
            for(int i=highScore.size() - 1; i >= currentSize; i--) {
                highScore.remove(i);
            }
        }
    }

    public void updateHighScore(int level, int highScore) {
        this.highScore.set(level, highScore);
    }

    public int getHighScore(int level) {
        return highScore.get(level);
    }
}
