package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import net.lighthouse.collision.CollisionChecker;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.MainView;

import java.awt.event.*;

/**
 * Main Controller of the game. Keeps track of the game loop and game status.
 * It also handles user interactions with the game.
 */
public class MainController extends GraphicsProgram {
    private MainView view;
    private MainModel model;
    private CollisionChecker ballChecker;

    private boolean isRunning;

    public void init() {
        isRunning = false;

        Settings.readUserSettings("settings.txt");
        // initializes default model with a paddle, one ball and 4 rows of blocks
        model = new MainModel();

        ballChecker = new CollisionChecker(model.getBall(0));

        view = new MainView(this);
        view.init();
        view.refresh(model);

        addMouseListeners();
        addKeyListeners();
    }

    /**
     * I need this to attach a debugger with IntelliJ. Otherwise IntelliJ is not
     * able to find a main method while using the acm library. See more:
     * https://stackoverflow.com/questions/28058665/java-runtime-error-could-not-initialize-class-formpreviewframe
     *
     * @param args Runtime arguments
     */
    public static void main(String[] args) {
        new MainController().start(args);
    }

    public void run() {

    }

    private void startNewGame() {
        System.out.println("I got executed!");
        isRunning = true;
        int[] speed = {2, 2};
        model.getBall(0).setSpeed(speed);

//        view.refresh(model);
        gameLoop();
        System.out.println("You lost! " + isRunning);
    }

    private void gameLoop() {
        long previousRefreshTime = System.currentTimeMillis();
        while (isRunning) {
            long nextTime = System.currentTimeMillis();

            // 1s == 1000ms => 50fps == 1/50s == 20ms
            if (nextTime - previousRefreshTime > 20) {
                ballChecker.handlePaddleCollision(model.getPaddle());
                isRunning = ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());
                BBlock[] hitBlocks = ballChecker.handleBlockCollision(model.getBlocks());

                for (BBlock block : hitBlocks) {
                    model.getBlocks().remove(block);
                }

                model.getBall(0).move();
                System.out.println("X: " + model.getBall(0).getX() + " Y: " + model.getBall(0).getY());
                view.refresh(model);
                previousRefreshTime = nextTime;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        model.movePaddle(e.getX() - model.getPaddle().getWith() / 2);
    }

    public void keyReleased(KeyEvent e) {
        if (!isRunning && e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("Space was pressed");
            startNewGame();
        }
    }
}
