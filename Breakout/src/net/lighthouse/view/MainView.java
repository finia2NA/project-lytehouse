package net.lighthouse.view;

import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import net.lighthouse.model.BBall;
import net.lighthouse.model.BObject;
import net.lighthouse.model.MainModel;

/**
 * the main View. Controlls what goes on in the client and lighthouse views. The
 * window for the client view gets provided by the Graphicsprogram that
 * constructs this view.
 * 
 * @author undif
 *
 */
public class MainView implements View{

	private DarkhouseScaler darkhouse;
	private GraphicsProgram top;
	private MainModel model;
	private MainModel lastModel;

	public MainView(GraphicsProgram top) {
		this.top = top;
	}

	public void init() {
		darkhouse.init();
	}

	public void update(MainModel model) {
		this.model = model;
		if (lastModel == null) {
			// initial fill.
			for (BObject o : model) {
				addObject(o);
			}
			return;
		}
		deleted();
		moveConditional();
		added();
		lastModel = model;
		darkhouse.update(top.getGCanvas());
		addText();
	}

	private void addText() {
		// TODO Auto-generated method stub

	}

	private void added() {
		for (BObject o : model) {
			if (!lastModel.contains(o)) {
				addObject(o);
			}

		}

	}

	private void addObject(BObject o) {
		// TODO Auto-generated method stub

	}

	private void deleted() {
		for (BObject o : lastModel) {
			if (!model.contains(o)) {
				deleteObject(o);
			}
		}
	}

	private void deleteObject(BObject o) {
		// TODO Auto-generated method stub

	}

	/**
	 * looks if the representations of a in the view have different coordinates than
	 * the concept in the model. if yes, the view needs to be updated.
	 * 
	 * @param concept
	 */
	public void moveConditional() {
		for (BObject o : model) {
			
		}
	}
}
