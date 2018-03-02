package net.lighthouse.view;

import java.util.HashMap;

import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import net.lighthouse.model.BBall;
import net.lighthouse.model.BObject;
import net.lighthouse.model.MainModel;


/**
 * the main View. Controlls what goes on in the client and lighthouse views. The
 * windows for the client view gets provided by the Graphicsprogram that
 * constructs this view.
 * 
 * @author undif
 *
 */
public class MainView {

	private DarkhouseScaler darkhouse;
	private GraphicsProgram top;
	private MainModel model;
	private HashMap<BObject, GObject> link;

	public MainView(GraphicsProgram top) {
		this.top = top;
	}

	public void init() {
		darkhouse.init();
	}

	public void update(MainModel model) {
		// Reihenfolge überprüfung: Ball, Paddle, Blocks, Explosions, Text.
		if (model.getAllBalls() != null) {
			for (BBall ball : model.getAllBalls()) {

				moveConditional(ball);
			}
		}

	}

	/**
	 * looks if the representations of a in the view have different coordinates than
	 * the concept in the model. if yes, the view needs to be updated.
	 * 
	 * @param concept
	 */
	public void moveConditional(BObject concept) {
		GObject representation = link.get(concept);
		if (!(representation.getX() == concept.getX() && representation.getY() == concept.getY())) {

			double moveX = concept.getX() - representation.getX();
			double moveY = concept.getY() - representation.getY();

			representation.move(moveX, moveY);
		}
	}
}
