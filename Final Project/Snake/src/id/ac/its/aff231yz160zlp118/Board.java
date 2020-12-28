package id.ac.its.aff231yz160zlp118;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
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

    protected Timer timer;
    protected Image ball;
    protected Image apple;
    protected Image head;

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

    protected abstract void initGame();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    protected abstract void doDrawing(Graphics g);

    protected void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        mainMenuButton.drawComponent(g);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2 - 60);
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

    protected abstract void checkCollision();

    protected abstract void locateApple();

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