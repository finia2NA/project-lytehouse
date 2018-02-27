package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import net.lighthouse.collision.CollisionChecker;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.MainView;

import java.awt.event.*;

/**
 * MainController ist das Oberste Ding.
 */
public class MainController extends GraphicsProgram {
    private MainView view;
    private MainModel model;
    private CollisionChecker ballChecker;

    public void init() {
        Settings.readUserSettings("settings.txt");
        // initializes the Model with default values(ball, paddle, buch o' blocks)
        model = new MainModel();

        ballChecker = new CollisionChecker(model.getBall(0));

        view = new MainView(this);
        view.init();
        addMouseListeners();

        int[] speed = {2, 4};
        model.getBall(0).setSpeed(speed);
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
        view.refresh(model);

        // Game Loop
        boolean runGame = true;
        long previousRefreshTime = System.currentTimeMillis();
        while (runGame) {
            long nextTime = System.currentTimeMillis();

            // 1s == 1000ms => 50fps == 1/50s == 20ms
            if (nextTime - previousRefreshTime > 20) {
                ballChecker.handlePaddleCollision(model.getPaddle());
                runGame = ballChecker.handleBorderCollision(this.getWidth(), model.getPaddle().getY());
                BBlock[] hitBlocks = ballChecker.handleBlockCollision(model.getBlocks());

                for (BBlock block : hitBlocks) {
                    model.getBlocks().remove(block);
                }

                model.getBall(0).move();
                view.refresh(model);
                previousRefreshTime = nextTime;
            }
        }
        System.out.println("You lost!");
    }

    public void mouseMoved(MouseEvent e) {
        model.movePaddle(e.getX() - model.getPaddle().getWith() / 2);
    }
}
