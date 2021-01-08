package id.ac.its.aff231yz160zlp118.snake;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public abstract class Board extends BasePanel implements ActionListener {
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    Button mainMenuButton = new Button(135, 100, 50, B_WIDTH, "MAIN MENU");

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    protected Vector<Integer> obstacleX = new Vector<Integer>();
    protected Vector<Integer> obstacleY = new Vector<Integer>();

    protected int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private int highScore;
    protected int levelID;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image obstacle;

    protected abstract void setLevelID();

    protected abstract void setObstaclePos();

    protected abstract void initSnakeLength();

    public Board() {
        super();
        addKeyListener(new TAdapter());
        loadImages();
        initGame();
        setLevelID();
        setObstaclePos();
    }

    private void initGame() {
        initSnakeLength();
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        if(r<10||r>B_WIDTH-10||r>B_HEIGHT-10) r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    private void locateRottenApple() {

    }

    private void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.jpg");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.jpg");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.jpg");
        head = iih.getImage();

        ImageIcon obs = new ImageIcon("src/resources/obstacle.jpg");
        obstacle = obs.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            drawObstacles(g);
            showScore(g);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            drawBorder(g);
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }

    }

    private void drawBorder(Graphics g) {
        Rectangle border = new Rectangle(1,1,300,300);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.RED);
        g2.draw(border);
    }

    private void drawObstacles(Graphics g) {
        for(int i=0; i<obstacleX.size(); i++) {
            g.drawImage(obstacle, obstacleX.get(i), obstacleY.get(i), this);
        }
    }

    private void gameOver(Graphics g) {
        String gameOver = "Game Over";
        getCurrentHighScore();
        String highScoreStr = String.format("High Score : %d", (highScore > dots ? highScore : dots));
        saveCurrentHighScore((highScore > dots ? highScore : dots));
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        mainMenuButton.drawComponent(g);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(gameOver, (B_WIDTH - metr.stringWidth(gameOver)) / 2, B_HEIGHT / 2 - 60);
        g.drawString(highScoreStr, (B_WIDTH - metr.stringWidth(highScoreStr)) / 2, B_HEIGHT / 2 - 85);
    }
    
    private void showScore(Graphics g) {
        String Score = String.format("Score : %d", dots);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(Score, 15, 25);
    }

    public boolean isInGame() {
        return inGame;
    }

    public ButtonClicked getButtonClicked(MouseEvent e) {
        if(mainMenuButton.isClicked(e))
            return ButtonClicked.MAIN_MENU;
        return ButtonClicked.NOT_CLICKED;
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection)
            x[0] -= DOT_SIZE;

        if (rightDirection)
            x[0] += DOT_SIZE;

        if (upDirection)
            y[0] -= DOT_SIZE;

        if (downDirection)
            y[0] += DOT_SIZE;
    }

    private void getCurrentHighScore() {
        Scanner input = null;
        highScore = ReadFromFile.readScore("score" + Integer.toString(levelID) + ".txt");
    }

    private void saveCurrentHighScore(int currentHighScore) {
        Formatter output = null;
        try {
            output = new Formatter("score" + Integer.toString(levelID) + ".txt");
        } catch (SecurityException securityException) {
            System.err.println("Write permission denied. Terminating.");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Error opening file. Terminating.");
            System.exit(1);
        }
        try {
            output.format("%d", currentHighScore);
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Error writing to file. Terminating.");
        } catch (NoSuchElementException elementException) {
            System.err.println("Invalid input. Please try again");
        }
        if(output != null)
            output.close();
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if(isCollideWithObstacles(x[0], y[0])) {
            inGame = false;
        }

        if (y[0] >= B_HEIGHT-10) {
            inGame = false;
        }

        if (y[0] < 10) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH-10) {
            inGame = false;
        }

        if (x[0] < 10) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private boolean isCollideWithObstacles(int x, int y) {
        for(int i=0; i<obstacleX.size(); i++)
            if(x == obstacleX.get(i) && y == obstacleY.get(i))
                return true;
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}