package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import net.lighthouse.model.MainModel;
import net.lighthouse.view.MainView;
import java.awt.event.*;

/**
 * MainController ist das Oberste Ding.
 */
public class MainController extends GraphicsProgram {
    private MainView view;
    private MainModel model;

    public void init() {
        // initializes the Model with default values(ball, paddle, buch o' blocks)
        model = new MainModel();

        view = new MainView(this);
        view.init();
        addMouseListeners();
    }

    /**
     * I need this to attach a debugger with IntelliJ. Otherwise IntelliJ is not able
     * to find a main method while using the acm library.
     * See more: https://stackoverflow.com/questions/28058665/java-runtime-error-could-not-initialize-class-formpreviewframe
     *
     * @param args Runtime arguments
     */
    public static void main(String[] args) {
        new MainController().start(args);
    }

    public void run() {
        view.refresh(model);

        //Game Loop
        long previousRefreshTime = System.currentTimeMillis();
        while (true) {
            long nextTime = System.currentTimeMillis();

            // 1s == 1000ms => 50fps == 1/50s == 20ms
            if (nextTime - previousRefreshTime > 20) {
                view.refresh(model);
                previousRefreshTime = nextTime;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
        model.movePaddle(e.getX() - 80); // -80 cuz Paddle is 160 wide.
        view.refresh(model);
    }
}
