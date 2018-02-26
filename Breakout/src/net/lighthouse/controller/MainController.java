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

	public void run() {
		view.refresh(model);
	}

	public void mouseMoved(MouseEvent e) {
		model.movePaddle(e.getX() - 80); // -80 cuz Paddle is 160 wide.
	}

	// until we have automatic refreshs you can refresh the view by clicking
	// anywhere.
	public void mouseClicked(MouseEvent e) {
		view.refresh(model);
	}
}
