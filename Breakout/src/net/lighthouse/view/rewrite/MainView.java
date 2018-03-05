package net.lighthouse.view.rewrite;

//JAVA
import java.awt.Color;
import java.util.ArrayList;
//ACM
import acm.graphics.GCanvas;
import acm.graphics.GFillable;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//MODEL
import net.lighthouse.model.BBall;
import net.lighthouse.model.BExplosion;
import net.lighthouse.model.BObject;
import net.lighthouse.model.BText;
import net.lighthouse.model.MainModel;
//SETTINGS
import net.lighthouse.settings.Settings;
//VIEW
import net.lighthouse.view.DarkhouseView;
import net.lighthouse.view.View;
import net.lighthouse.view.rewrite.BLink;

/**
 * The main View. Controlls what goes on in the client and lighthouse views. The
 * window for the client view gets provided by the GraphicsProgram that
 * constructs this view.
 * 
 * @author finite
 *
 */
public class MainView implements View {

	private DarkhouseView darkhouse;
	private GraphicsProgram top;
	private MainModel model;
	private BLinkList links;

	public MainView(GraphicsProgram top) {
		assert top != null;
		this.top = top;

	}

	public void init() {
		System.out.println("using new Viewport");
		if (Settings.getSetting("web-view").equals("true")) {
			darkhouse = new DarkhouseView();
			darkhouse.init();
		}
		top.setSize(560, 840);
		// TODO: this doesn't work. it's not essential, but it would be kinda cool.
		top.setTitle("Breakout pre-release");
		top.getGCanvas().setAutoRepaintFlag(false);
		top.setBackground(Color.BLACK);
	}

	public void update(MainModel model) {
		this.model = model;
		if (links == null) {
			// initial fill.
			links = new BLinkList();
			for (BObject o : model) {
				addObject(o);
			}
			return;
		}
		deleted();
		added();
		moved();

		if (Settings.getSetting("web-view").equals("true")) {
			darkhouse.update(top.getGCanvas());
		}
		top.repaint();
		;
	}

	private void addObject(BObject o) {
		GObject g;
		if (o instanceof BBall) {
			// TODO: width and height scaling.
			g = new GImage("FootballLQ.png", o.getX(), o.getY());

		} else if (o instanceof BText) {
			g = new GLabel(((BText) o).getText(), o.getX(), o.getY());
			g.setColor(o.getColor());
			return;
		} else if (o instanceof BExplosion) {
			g = new GLabel("hier sollte eine Explosion sein xD", o.getX(), o.getY());
			g.setColor(o.getColor());
		} else {
			g = new GRect(o.getX(), o.getY(), o.getWith(), o.getHeight());
			((GFillable) g).setFilled(true);
			((GFillable) g).setFillColor(o.getColor());
		}
		// TODO: don't know if i can just write it like this or if the add needs to be
		// specific to the type of g.
		top.add(g);
		links.add(new BLink(o, g));

	}

	/**
	 * looks if there are any Objects in the model that have not jet been added to
	 * the screen. If yes, adds those objects to the screen and the links.
	 */
	private void added() {
		for (BObject o : model) {
			if (!links.containsBObject(o)) {
				addObject(o);
			}
		}
	}

	/**
	 * looks if there are any Objects on the screen that have been removed from the
	 * model. if yes, removes those objects from the screen and the links.
	 */
	private void deleted() {
		// a link cannot be removed while iterating through links. Workaround: save
		// which links to remove and remove them outside of the loop.
		ArrayList<BLink> toDelete = new ArrayList<BLink>();
		for (BLink link : links) {
			if (!(model.contains(link.getB()))) {
				toDelete.add(link);
			}
		}
		for (BLink link : toDelete) {
			top.remove(link.getG());
			links.remove(link);
		}
	}

	/**
	 * looks if the representations of a in the view have different coordinates than
	 * the concept in the model. if yes, the view needs to be updated.
	 * 
	 * @param concept
	 */
	public void moved() {
		for (BLink link : links) {
			if (link.hasMoved()) {
				int moveX = (int) (link.getB().getX() - link.getG().getX());
				int moveY = (int) (link.getB().getY() - link.getG().getY());
				link.getG().move(moveX, moveY);
			}
		}
	}
}
