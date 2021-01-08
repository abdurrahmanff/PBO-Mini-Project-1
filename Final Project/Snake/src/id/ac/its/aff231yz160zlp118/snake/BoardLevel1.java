package id.ac.its.aff231yz160zlp118.snake;

public class BoardLevel1 extends Board {
	private final int levelID = 1;

    @Override
    protected void setLevelID() {
        super.levelID = levelID;
    }

    @Override
    protected void setObstaclePos() {
        obstacleX.add(286);
        obstacleY.add(286);
    }
	
	@Override
    protected void initSnakeLength() {
        dots = 20;
    }

}
