package id.ac.its.aff231yz160zlp118;

import com.sun.tools.javac.Main;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

public class Snake extends JFrame {
    Board gameBoard;
    MainMenu mainMenu;
    CreditMenu creditMenu;
    boolean isInGame;

    public Snake() {
        initUI();
    }

    private void initUI() {
        mainMenu = new MainMenu();
        creditMenu = new CreditMenu();
        creditMenu.setVisible(false);
        MouseHandler mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        mainMenu.setVisible(true);
        add(mainMenu);

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }

    private class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(mainMenu.isVisible()) {
                if(mainMenu.getButtonClicked(e) == ButtonClicked.PLAY) {
                    mainMenu.setVisible(false);
                    getContentPane().remove(mainMenu);
                    gameBoard = new Board();
                    gameBoard.setVisible(true);
                    add(gameBoard);
                    gameBoard.requestFocusInWindow();
                } else if(mainMenu.getButtonClicked(e) == ButtonClicked.CREDITS) {
                    mainMenu.setVisible(false);
                    getContentPane().remove(mainMenu);
                    creditMenu.setVisible(true);
                    add(creditMenu);
                }
            } else if(creditMenu.isVisible() && creditMenu.getButtonClicked(e) == ButtonClicked.MAIN_MENU) {
                creditMenu.setVisible(false);
                getContentPane().remove(creditMenu);
                mainMenu.setVisible(true);
                add(mainMenu);
            } else if(gameBoard.isVisible() && gameBoard.getButtonClicked(e) == ButtonClicked.MAIN_MENU && !gameBoard.isInGame()) {
                gameBoard.setVisible(false);
                getContentPane().remove(gameBoard);
                mainMenu.setVisible(true);
                add(mainMenu);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
