package id.ac.its.aff231yz160zlp118.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

public class Board extends BasePanel implements ActionListener {
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 28;
    private final int DELAY = 140;
    private final int appleMinXPos = 10;
    private final int appleMaxXPos = 280;
    private final int appleMinYPos = 10;
    private final int appleMaxYPos = 280;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private Vector<Integer> obstacleX = new Vector<Integer>();
    private Vector<Integer> obstacleY = new Vector<Integer>();

    private int dots;
    private int apple_x;
    private int apple_y;
    private int gapple_x;
    private int gapple_y;
    private int rapple_x;
    private int rapple_y;
    private int skull_x;
    private int skull_y;
    private int appleXCandidate;
    private int appleYCandidate;
    private int appleEaten;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private int levelID;
    private HighScore highScore;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image obstacle;
    private Image goldenApple;
    private Image rottenApple;
    private Image skull;

    private void setObstaclePos() {
        Scanner input = null;
        String filename = "assets/level/Level" + Integer.toString(levelID) + ".txt";

        try {
            input = new Scanner(Paths.get(filename));
        } catch (IOException ioException) {
            System.err.println("Can't open " + filename);
            return;
        }

        try {
            int obstacle_count = input.nextInt();
            for(int i=0; i<obstacle_count; i++) {
                obstacleX.add(input.nextInt());
                obstacleY.add(input.nextInt());
            }
        } catch (NoSuchElementException | IllegalStateException stateException) {
            return;
        }

        input.close();
    }

    private void initSnakeLength() {
        dots = 3;
    }

    public Board(int levelID, Snake mainClass) {
        super(mainClass);
        this.levelID = levelID;
        setObstaclePos();
        addKeyListener(new TAdapter());
        loadImages();
        initGame();
    }

    private void initGame() {
        initSnakeLength();
        for (int z = 0; z < dots; z++) {
            x[z] = 150 - z * 10;
            y[z] = 150;
        }
        
        appleEaten = 0;
        initApple();
        resetGoldenApple();
        resetRottenApple();
        resetSkull();
        
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void initApple() {
    	locateApple();
    }
    
    private void resetGoldenApple() {
    	gapple_x = gapple_y = 500;
    }
    
    private void resetRottenApple() {
    	rapple_x = rapple_y = 500;
    }
    
    private void resetSkull() {
    	skull_x = skull_y = 500;
    }

    private void randomizeApplePos() {
        int appleXCandidate = ((int) (Math.random() * RAND_POS)) * DOT_SIZE + appleMinXPos;
        int appleYCandidate = ((int) (Math.random() * RAND_POS)) * DOT_SIZE + appleMinYPos;
        while(!isAppleInProperYPos(appleYCandidate) || !isAppleInProperXPos(appleXCandidate)
                || isCollideWithObstacles(appleXCandidate, appleYCandidate)
                || isAppleSpawnAtSnake(appleXCandidate, appleYCandidate)) {
            appleYCandidate = ((int) (Math.random() * RAND_POS)) * DOT_SIZE + appleMinYPos;
            appleXCandidate = ((int) (Math.random() * RAND_POS)) * DOT_SIZE + appleMinXPos;
        }
        this.appleXCandidate = appleXCandidate;
        this.appleYCandidate = appleYCandidate;
    }

    private void locateApple() {
        randomizeApplePos();
        apple_x = this.appleXCandidate;
        apple_y = this.appleYCandidate;
    }

    private void locateGoldenApple() {
    	randomizeApplePos();
        gapple_x = this.appleXCandidate;
        gapple_y = this.appleYCandidate;
    }
    
    private void locateRottenApple() {
    	randomizeApplePos();
    	rapple_x = this.appleXCandidate;
    	rapple_y = this.appleYCandidate;
    }
    
    private void locateSkull() {
    	randomizeApplePos();
    	skull_x = this.appleXCandidate;
    	skull_y = this.appleYCandidate;
    }

    private boolean isAppleSpawnAtSnake(int x, int y) {
        for(int i=0; i<dots; i++) {
            if(x == this.x[i] && y == this.y[i])
                return true;
        }
        return false;
    }

    private boolean isAppleInProperXPos(int x) {
        return x >= appleMinXPos && x <= appleMaxXPos;
    }

    private boolean isAppleInProperYPos(int y) {
        return y >= appleMinYPos && y <= appleMaxYPos;
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
        
        ImageIcon iiga = new ImageIcon("src/resources/goldapple.jpg");
        goldenApple = iiga.getImage();
        
        ImageIcon iira = new ImageIcon("src/resources/GreenApple.jpg");
        rottenApple = iira.getImage();
        
        ImageIcon iis = new ImageIcon("src/resources/skull.png");
        skull = iis.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            if(timeGoldenSpawn() || !isGoldenAppleEaten()) 
            	g.drawImage(goldenApple, gapple_x, gapple_y, this);
            if(timeRottenSpawn() || !isRottenAppleEaten()) 
            	g.drawImage(rottenApple, rapple_x, rapple_y, this);
            if(timeSkullSpawn() || !isSkullEaten())
            	g.drawImage(skull, skull_x, skull_y, this);
            drawObstacles(g);
            showScore(g);
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }

    }

    private void drawObstacles(Graphics g) {
        for(int i=0; i<obstacleX.size(); i++) {
            g.drawImage(obstacle, obstacleX.get(i), obstacleY.get(i), this);
        }
    }

    private void gameOver(Graphics g) {
        String gameOver = "Game Over";
        highScore = LoadFromFile.loadScore();
        highScore.checkForBanyakLevelChanges();
        String highScoreStr = String.format("High Score : %d", (highScore.getHighScore(levelID) > (dots-3) ? highScore.getHighScore(levelID) : (dots-3)));
        highScore.updateHighScore(levelID, (highScore.getHighScore(levelID) > (dots-3) ? highScore.getHighScore(levelID) : (dots-3)));
        saveHighScore();


        JButton playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setBounds(98, 130,100, 50);
        setButtonStyle(playAgainButton);
        playAgainButton.addActionListener(this);
        add(playAgainButton);

        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setBounds(98, 210,100, 50);
        setButtonStyle(mainMenuButton);
        mainMenuButton.addActionListener(this);
        add(mainMenuButton);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(gameOver, (B_WIDTH - metr.stringWidth(gameOver)) / 2, B_HEIGHT / 2 - 60);
        g.drawString(highScoreStr, (B_WIDTH - metr.stringWidth(highScoreStr)) / 2, B_HEIGHT / 2 - 85);
    }
    
    private void showScore(Graphics g) {
        String Score = String.format("Score : %d", (dots-3));

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(Score, 15, 25);
    }

    private void checkApple() {
        if (isAppleEaten()) {
        	appleEaten++;
            dots++;
            locateApple();
        }
    }
    
    private void checkGoldenApple() {
        if (isGoldenAppleEaten()) {
        	appleEaten++;
            dots=dots+2;
            resetGoldenApple();
        }
        if (timeGoldenSpawn() && isGoldenAppleNotSpawn())
        	locateGoldenApple();
        else if(!timeGoldenSpawn())
        	resetGoldenApple();
    }
    
    private void checkRottenApple() {
        if (isRottenAppleEaten()) {
        	appleEaten++;
            dots=dots-2;
            resetRottenApple();
        }
        if(timeRottenSpawn() && isRottenAppleNotSpawn())
        	locateRottenApple();
        else if(!timeRottenSpawn())
        	resetRottenApple();
    }
    
    private void checkSkull() {
    	if (isSkullEaten())
    		inGame = false;
    	if(timeSkullSpawn() && isSkullNotSpawn())
        	locateSkull();
        else if(!timeSkullSpawn())
        	resetSkull();
    }
    
    private boolean isAppleEaten() {
    	return ((x[0] == apple_x) && (y[0] == apple_y));
    }
    
    private boolean isGoldenAppleEaten() {
    	return ((x[0] == gapple_x) && (y[0] == gapple_y));
    }
    
    private boolean isRottenAppleEaten() {
    	return ((x[0] == rapple_x) && (y[0] == rapple_y));
    }
    
    private boolean isSkullEaten() {
    	return ((x[0] == skull_x) && (y[0] == skull_y));
    }
    
    private boolean timeGoldenSpawn() {
    	return (((appleEaten%3) == 0 || (appleEaten%4) == 0) && dots > 4);
    }
    
    private boolean timeRottenSpawn() {
    	return (((appleEaten%3) == 0 | (appleEaten%4) == 0 || (appleEaten%5) == 0 || (appleEaten%6) == 0) && dots > 4);
    }
    
    private boolean timeSkullSpawn() {
    	return ((appleEaten%10) == 0 && dots > 3);
    }
    
    private boolean isGoldenAppleNotSpawn() {
    	return (gapple_x == 500 && gapple_y == 500);
    }
    
    private boolean isRottenAppleNotSpawn() {
    	return (rapple_x == 500 && rapple_y == 500);
    }
    
    private boolean isSkullNotSpawn() {
    	return (skull_x == 500 && skull_y == 500);
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

    private void saveHighScore() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data/highscorestats.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(highScore);

            outputStream.close();
            fileOutputStream.close();
        } catch (IOException ioException) {
            System.out.println("Error when saving file");
        }
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

        if (y[0] >= B_HEIGHT) {
            y[0] = 0;
        }

        if (y[0] < 0) {
        	y[0] = B_HEIGHT;
        }

        if (x[0] >= B_WIDTH) {
        	x[0] = 0;
        }

        if (x[0] < 0) {
        	x[0] = B_HEIGHT;
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
            checkGoldenApple();
            checkRottenApple();
            checkSkull();
            checkCollision();
            move();
        } else if(e.getActionCommand().equals("MAIN MENU")) {
            mainClass.closeGameBoard();
            mainClass.openMainMenu();
        } else if(e.getActionCommand().equals("PLAY AGAIN")) {
            mainClass.closeGameBoard();
            mainClass.playSnake(levelID);
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