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
 *
 * @author Christoph Fricke
 */
public class MainController extends GraphicsProgram {
    private MainView view;
    private MainModel model;
    private CollisionChecker ballChecker;

    private boolean isRunning;

    public void init() {
        Settings.readUserSettings("settings.txt");
        // initializes default model with a paddle, one ball and 4 rows of blocks
        model = new MainModel();

        ballChecker = new CollisionChecker(model.getBall(0));

        view = new MainView(this);
        view.init();
        System.out.println(this.getWidth() + " " + this.getHeight());
        view.refresh(model);

        isRunning = false;

        addMouseListeners();
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

    private void startNewGame() {
        System.out.println("I got executed!");
        isRunning = true;
        int[] speed = {2, 2};
        model.getBall(0).setSpeed(speed);

        gameLoop();
    }

    private void gameLoop() {
        boolean playerLost = false;
        long previousRefreshTime = System.currentTimeMillis();
        do {
            long nextTime = System.currentTimeMillis();

            // 1s == 1000ms => 50fps == 1/50s == 20ms
            if (nextTime - previousRefreshTime > 20) {
                ballChecker.handlePaddleCollision(model.getPaddle());
                playerLost = !ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());
                BBlock[] hitBlocks = ballChecker.handleBlockCollision(model.getBlocks());

                for (BBlock block : hitBlocks) {
                    model.getBlocks().remove(block);
                }

                model.getBall(0).move();
                System.out.println("X: " + model.getBall(0).getX() + " Y: " + model.getBall(0).getY());
                view.refresh(model);
                previousRefreshTime = nextTime;
            }
        } while (!playerLost);
        stopGame();
    }

    private void stopGame() {
        model.getAllBalls().remove(0);
        view.refresh(model);
        isRunning = false;
        System.out.println("You lost! " + isRunning);
    }

    public void mouseMoved(MouseEvent e) {
        model.getPaddle().move(e.getX() - model.getPaddle().getWith() / 2);
    }

    public void mouseClicked(MouseEvent e) {
        if (!isRunning) {
            System.out.println("Mouse was clicked");
            startNewGame();
        }
    }
}
