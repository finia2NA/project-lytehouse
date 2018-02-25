package net.lighthouse.controller;

import acm.program.GraphicsProgram;
import net.lighthouse.model.MainModel;
import net.lighthouse.view.MainView;

/**
 * Wir brauchen sowieso die Mausevents, deshalb mache ich das Ding jetzt mal zu
 * einem Grafikprogram.
 */
public class MainController extends GraphicsProgram {
	private MainView view;
	private MainModel model;

	public void init() {
		// initializes the Model with default values(ball, paddle, buch o' blocks)
		model = new MainModel();

		view = new MainView(this);
		view.init();
	}

	public void run() {
		view.refresh(model);
	}
}
