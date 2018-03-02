package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
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
    private boolean startGame;

    public void init() {
        Settings.readUserSettings("settings.txt");
        // initializes default model with a paddle, one ball and 4 rows of blocks
        view = new MainView(this);
        view.init();
        System.out.println(this.getWidth() + " " + this.getHeight());

        isRunning = false;
        startGame = false;

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
        while (true) {
            if (startGame) {
                startNewGame();
            } else {
                pause(20);
            }
        }
    }

    private void startNewGame() {
        isRunning = true;

        model = new MainModel();
        ballChecker = new CollisionChecker(model.getBall(0));
        view.refresh(model);

        // Generates a random start speed
        RandomGenerator rnd = RandomGenerator.getInstance();
        int[] speed = {rnd.nextInt(-4, 4), rnd.nextInt(2, 6)};
        model.getBall(0).setSpeed(speed);

        gameLoop();
    }

    private void gameLoop() {
        boolean playerLost = false;
        long previousRefreshTime = System.currentTimeMillis();
        while (!playerLost) {
            long nextTime = System.currentTimeMillis();

            // 1s == 1000ms => 50fps == 1/50s == 20ms
            if (nextTime - previousRefreshTime > 20) {
                ballChecker.handlePaddleCollision(model.getPaddle());
                playerLost = !ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());
                BBlock[] hitBlocks = ballChecker.handleBlockCollision(model.getBlocks());

                for (BBlock block : hitBlocks) {
                    model.userScore++;
                    model.getBlocks().remove(block);
                }

                model.getBall(0).move();
                view.refresh(model);
                previousRefreshTime = nextTime;
            }
        }
        stopGame();
    }

    private void stopGame() {
        model.getAllBalls().remove(0);
        view.refresh(model);
        System.out.println("You lost! Your score was: " + model.userScore);

        isRunning = false;
        startGame = false;
    }

    public void mouseMoved(MouseEvent e) {
        if(model != null) {
            model.getPaddle().move(e.getX() - model.getPaddle().getWith() / 2);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!isRunning && e.getKeyCode() == KeyEvent.VK_SPACE) {
            startGame = true;
        }
    }
}
