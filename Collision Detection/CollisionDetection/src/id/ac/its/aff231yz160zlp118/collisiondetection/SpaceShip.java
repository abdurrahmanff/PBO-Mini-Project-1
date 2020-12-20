package id.ac.its.aff231yz160zlp118.collisiondetection;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends Sprite {
	private int dx;
	private int dy;
	private List<Missile> missiles;
	
	public SpaceShip(int x, int y) {
		super(x, y);
		
		initCraft();
	}
	
	private void initCraft() {
		missiles = new ArrayList<>();
		loadImage("src/resources/spaceship.png");
		getImageDimensions();
	}

	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public void move() {
        x += dx;
        y += dy;

        if (x < 1) {
            x = 1;
        }

        if (y < 1) {
            y = 1;
        }
    }
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public void mouseClicked(MouseEvent e) {
        fire();

    }

    public void mousePressed(MouseEvent e) {
	    mouseClicked(e);
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        fire();
        mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
