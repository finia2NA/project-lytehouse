package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import net.lighthouse.collision.CollisionChecker;
import net.lighthouse.model.*;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.rewrite.MainView;
import net.lighthouse.view.View;
import net.lighthouse.view.legacy.legacyMainView;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Main Controller of the game. Keeps track of the game loop and game status. It
 * also handles user interactions with the game.
 *
 * @author Christoph Fricke
 */
public class MainController extends GraphicsProgram {
    // Current state and game objects used throughout the hole game class. It is way more beautiful to store
    // them as instance variables than passing them with every function call.
    private View view;
    private MainModel model;
    private CollisionChecker ballChecker;
    private BBossController bossController;

    // States of the game that are used within the hole class.
    private boolean isRunning;
    private boolean isBossFight;
    private boolean startGame;

    // Little settings that are used for operation in the hole class.
    private boolean printFrametimes = false;
    private int frametime = 40;

    public void init() {
        Settings.readUserSettings("settings.txt");

        frametime = Integer.parseInt(Settings.getSetting("frametime"));

        if (Settings.getSetting("print-frametimes").equals("true")) {
            printFrametimes = true;
        }

        if (Settings.getSetting("use_new_Viewport").equals("true")) {
            view = new MainView(this);
        } else {
            view = new legacyMainView(this);
        }

        view.init();

        isRunning = false;
        startGame = false;

        // Creates a main menu model (MainModel with only text) and displays it
        ArrayList<BText> messages = new ArrayList<>();
        messages.add(new BText(150, 100, "BREAKOUT"));
        messages.add(new BText(100, 200, "press SPACE to start"));
        model = new MainModel(messages);
        view.update(model);

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

    /**
     * Waits to start the game. This busy waiting loop is needed since startGame()
     * in an event handler disables screen updating. An alternative would be a game thread
     * but this way we can be sure that no side effects occur other than some performance loss.
     */
    public void run() {
        while (true) {
            if (startGame) {
                startNewGame();
            } else {
                pause(frametime);
            }
        }
    }

    /**
     * Starts a new game and calls the game loop.
     */
    private void startNewGame() {
        isRunning = true;
        isBossFight = false;

        // Updates the model to a model which contains objects that should be drawn during
        // a running game.
        model = new MainModel();
        ballChecker = new CollisionChecker(model.getBall(0));
        view.update(model);

        // Generates a random start speed
        RandomGenerator rnd = RandomGenerator.getInstance();
        int[] speed = {rnd.nextInt(-4, 4), 8};
        model.getBall(0).setSpeed(speed);

        gameLoop();
    }

    /**
     * This runs the game.
     */
    private void gameLoop() {
        boolean playerLost = false;
        boolean playerWon = false;

        long previousRefreshTime = System.currentTimeMillis();
        while (!playerLost && !playerWon) {
            long nextTime = System.currentTimeMillis();

            // Performs an update when the time has come
            if (nextTime - previousRefreshTime >= frametime) {
                // Little performance measure option
                if (printFrametimes) {
                    System.out.println(nextTime - previousRefreshTime);
                }
                // Creates a boss fight when the time has come
                if (!isBossFight && model.getBlocks().size() == 0) {
                    initBossFight();
                }

                // Perform ball updates
                ballChecker.handlePaddleCollision(model.getPaddle());
                playerLost = !ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());
                if (playerLost) {
                    // Stops game early since this could be false again when the boss move gets performed
                    break;
                }

                if (!isBossFight) {
                    // Logic when no boss fight is happening
                    BBlock[] hitBlocks = ballChecker.handleBlockCollision(model.getBlocks());

                    // Remove blocks that got hit in this frame
                    for (BBlock block : hitBlocks) {
                        model.userScore += 10;
                        model.getBlocks().remove(block);
                    }
                } else {
                    // Logic during a boss fight
                    assert isBossFight && model.getBoss() != null : "Woops looks like it is not a boss fight";

                    ballChecker.handleBossCollision(model.getBoss());
                    if (model.getBoss().getHealth() > 0) {
                        // Performs boss move and lasers
                        BLaser laser = bossController.playBossMove();

                        if (laser != null) {
                            model.addObject(laser);
                        }

                        playerLost = BLaserController.updateLasers(model, this.getWidth());
                    } else {
                        playerWon = true;
                    }
                }

                // Finalizes ball position 
                model.getBall(0).move();
                
               // makes the changes visible
                view.update(model);

                model.userScore += 0.01;
                previousRefreshTime = nextTime;
            }
        }

        // Determines which screen should be shown next.
        if (playerLost) {
            lossScreen();
        } else if (playerWon) {
            winScreen();
        }
    }

    /**
     * Shows a loss screen
     */
    private void lossScreen() {
        ArrayList<BText> messages = new ArrayList<>();
        messages.add(new BText(150, 100, "YOU LOST!"));
        messages.add(new BText(100, 200, "Your score is: " + (int) model.userScore));
        messages.add(new BText(100, 300, "press SPACE to start"));
        model = new MainModel(messages);
        model.addObject(new BBlock(0, 0, 560, 840, Color.RED));
        view.update(model);

        isRunning = false;
        startGame = false;
    }

    /**
     * Shows a win screen
     */
    private void winScreen() {
        ArrayList<BText> messages = new ArrayList<>();
        messages.add(new BText(150, 100, "YOU WON!"));
        messages.add(new BText(100, 200, "Your score is: " + (int) model.userScore));
        messages.add(new BText(100, 300, "press SPACE to start"));
        model = new MainModel(messages);
        model.addObject(new BBlock(0, 0, 560, 840, Color.GREEN));
        view.update(model);

        isRunning = false;
        startGame = false;
    }

    /**
     * Creates a boss fight scene
     */
    private void initBossFight() {
        BBoss boss = new BBoss(10, Color.GREEN);
        model.addObject(boss);
        bossController = new BBossController(boss);

        // Resets ball position
        model.getBall(0).setX(560 / 2);
        model.getBall(0).setY(840 / 2);
        RandomGenerator rnd = RandomGenerator.getInstance();
        int[] speed = {rnd.nextInt(-4, 4), 8};
        model.getBall(0).setSpeed(speed);

        isBossFight = true;
    }

    /**
     * Moves the paddle.
     *
     * @param e MouseEvent
     */
    public void mouseMoved(MouseEvent e) {
        if (model != null && model.getPaddle() != null) {
            model.getPaddle().move(e.getX() - model.getPaddle().getWith() / 2);
        }
    }

    /**
     * Starts new game if no game is currently running.
     *
     * @param e KeyEvent
     */
    public void keyPressed(KeyEvent e) {
        if (!isRunning && e.getKeyCode() == KeyEvent.VK_SPACE) {
            startGame = true;
        }
    }
}
