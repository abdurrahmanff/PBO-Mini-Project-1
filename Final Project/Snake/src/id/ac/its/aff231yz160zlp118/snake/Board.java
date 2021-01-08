package id.ac.its.aff231yz160zlp118.snake;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public abstract class Board extends BasePanel implements ActionListener {
    protected final int DOT_SIZE = 10;
    protected final int ALL_DOTS = 900;
    protected final int RAND_POS = 29;
    protected final int DELAY = 140;

    Button mainMenuButton = new Button(135, 100, 50, B_WIDTH, "MAIN MENU");

    protected final int x[] = new int[ALL_DOTS];
    protected final int y[] = new int[ALL_DOTS];

    protected int dots;
    protected int apple_x;
    protected int apple_y;

    protected boolean leftDirection = false;
    protected boolean rightDirection = true;
    protected boolean upDirection = false;
    protected boolean downDirection = false;
    protected boolean inGame = true;
    protected int highScore;
    protected int levelID;

    protected Timer timer;
    protected Image ball;
    protected Image apple;
    protected Image head;

    protected abstract void initGame();

    protected abstract void doDrawing(Graphics g);

    protected abstract void checkCollision();

    protected abstract void locateApple();
    
    protected abstract void locateRottenApple();

    public Board() {
        super();
        addKeyListener(new TAdapter());
        loadImages();
        initGame();
    }

    protected void loadImages() {
        ImageIcon iid = new ImageIcon("src/resources/dot.jpg");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/resources/apple.jpg");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/resources/head.jpg");
        head = iih.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    protected void drawBorder(Graphics g) {
        Rectangle border = new Rectangle(1,1,300,300);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.setColor(Color.RED);
        g2.draw(border);
    }

    protected abstract void drawObstacles(Graphics g);

    protected void gameOver(Graphics g) {
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
    
    protected void showScore(Graphics g) {
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

    protected void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    protected void move() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    protected class TAdapter extends KeyAdapter {
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