package id.ac.its.aff231yz160zlp118.snake;

public class BoardLevel0 extends Board {
    private final int levelID = 0;

    @Override
    protected void setLevelID() {
        super.levelID = levelID;
    }

    @Override
    protected void setObstaclePos() {
        for(int i=0, yPos = 70; i < 16; i++, yPos += 10) {
            obstacleX.add(50);
            obstacleY.add(yPos);
            obstacleX.add(250);
            obstacleY.add(yPos);
        }
    }

    @Override
    protected void initSnakeLength() {
        dots = 3;
    }
}
