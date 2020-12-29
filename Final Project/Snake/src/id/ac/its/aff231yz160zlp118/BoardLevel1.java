package id.ac.its.aff231yz160zlp118;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.Timer;

public class BoardLevel1 extends Board {
	private final int levelID = 1;
	
	 public BoardLevel1() {
	        super();
	        super.levelID = levelID;
	 }
	 
	protected void drawBorder(Graphics g) {
    	Rectangle border = new Rectangle(1,1,300,300);
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setStroke(new BasicStroke(10));
    	g2.setColor(Color.RED);
    	g2.draw(border);
    }
	
	@Override
	protected void initGame() {
		dots = 20;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
	}

	@Override
	protected void doDrawing(Graphics g) {
		if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
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

	@Override
	protected void checkCollision() {
		for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
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

	@Override
	protected void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        if(r<10||r>B_WIDTH-10||r>B_HEIGHT-10) r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
	}
	
	@Override
	protected void locateRottenApple() {
		
	}

}
