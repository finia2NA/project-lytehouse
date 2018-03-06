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
    private View view;
    private MainModel model;
    private CollisionChecker ballChecker;
    private BBossController bossController;

    private boolean isRunning;
    private boolean isBossFight;
    private boolean startGame;

    private boolean printFrametimes = false;
    private int frametime = 30;

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
        System.out.println(this.getWidth() + " " + this.getHeight());

        isRunning = false;
        startGame = false;

        ArrayList<BText> messages = new ArrayList<>();
        messages.add(new BText(100, 100, "BREAKOUT"));
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
        isBossFight = false;

        model = new MainModel();
        ballChecker = new CollisionChecker(model.getBall(0));
        view.update(model);

        // Generates a random start speed
//		RandomGenerator rnd = RandomGenerator.getInstance();
//		int[] speed = { rnd.nextInt(-4, 4), rnd.nextInt(4, 6) };
        int[] speed = {0, 6};
        model.getBall(0).setSpeed(speed);

        gameLoop();
    }

    private void gameLoop() {
        boolean playerLost = false;
        boolean playerWon = false;

        long previousRefreshTime = System.currentTimeMillis();
        while (!playerLost && !playerWon) {
            long nextTime = System.currentTimeMillis();

            if (nextTime - previousRefreshTime >= frametime) {
                if (printFrametimes) {
                    System.out.println(nextTime - previousRefreshTime);
                }
                if (!isBossFight && model.getBlocks().size() == 0) {
                    initBossFight();
                }

                ballChecker.handlePaddleCollision(model.getPaddle());
                playerLost = !ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());

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
                    assert isBossFight && model.getBoss() != null : "Wops looks like it is not a boss fight";

                    ballChecker.handleBossCollision(model.getBoss());
                    if (model.getBoss().getHealth() > 0) {
                        BLaser laser = bossController.playBossMove();

                        if (laser != null) {
                            model.addObject(laser);
                        }

                        playerLost = BLaserController.updateLasers(model, this.getWidth());
                    } else {
                        playerWon = true;
                    }
                }

                model.getBall(0).move();
                view.update(model);

                model.userScore += 0.01;
                previousRefreshTime = nextTime;
            }
        }

        if (playerLost) {
            stopGame();
        } else if (playerWon) {
            winScreen();
        }
    }

    private void stopGame() {
        if (model.getBoss() != null) {
            model.removeBoss();
        }

        if (model.getLasers().size() != 0) {
            model.getLasers().clear();
        }
        model.getAllBalls().remove(0);
        view.update(model);
        System.out.println("You lost! Your score was: " + (int) model.userScore);

        isRunning = false;
        startGame = false;
    }

    private void winScreen() {
        model.getAllBalls().remove(0);
        model.getLasers().clear();
        model.removeBoss();
        view.update(model);
        System.out.println("You won! Hoorray! Your score was: " + (int) model.userScore);

        isRunning = false;
        startGame = false;
    }

    private void initBossFight() {
        BBoss boss = new BBoss(10, Color.MAGENTA);
        model.addObject(boss);
        bossController = new BBossController(boss);

        model.getBall(0).setX(560 / 2);
        model.getBall(0).setY(840 / 2);
        RandomGenerator rnd = RandomGenerator.getInstance();
        int[] speed = {rnd.nextInt(-4, 4), rnd.nextInt(4, 6)};
        model.getBall(0).setSpeed(speed);

        isBossFight = true;
    }

    public void mouseMoved(MouseEvent e) {
        if (model != null && model.getPaddle() != null) {
            model.getPaddle().move(e.getX() - model.getPaddle().getWith() / 2);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (!isRunning && e.getKeyCode() == KeyEvent.VK_SPACE) {
            startGame = true;
        }
    }
}
