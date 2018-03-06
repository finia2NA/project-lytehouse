package net.lighthouse.view.rewrite;

//JAVA
import java.awt.Color;
import java.util.ArrayList;
//ACM
import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GFillable;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//MODEL
import net.lighthouse.model.BBall;
import net.lighthouse.model.BBoss;
import net.lighthouse.model.BExplosion;
import net.lighthouse.model.BLaser;
import net.lighthouse.model.BObject;
import net.lighthouse.model.BText;
import net.lighthouse.model.MainModel;
//SETTINGS
import net.lighthouse.settings.Settings;
//VIEW
import net.lighthouse.view.DarkhouseScaler;
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

	private DarkhouseScaler darkhouse;
	private GraphicsProgram top;

	// things in this Compound get added to both canvases.
	private GCompound sharedCompound;

	private MainModel model;
	private BLinkList links;

	// stored as a variable so we don't have to lookup settings each update.
	private boolean use_darkhouse;

	public MainView(GraphicsProgram top) {
		assert top != null;
		this.top = top;
		sharedCompound = new GCompound();
		GRect backGround = new GRect(600, 900);
		backGround.setFilled(true);
		backGround.setFillColor(Color.black);
		sharedCompound.add(backGround);
	}

	/**
	 * initializes the client with a size and the lighthouse if settings say so.
	 */
	public void init() {
		System.out.println("Viewport info : Using new Viewport");
		if (Settings.getSetting("web-view").equals("true")) {
			use_darkhouse = true;
			darkhouse = new DarkhouseScaler();
			darkhouse.init();
			// TODO: repaintflag?
		} else {
			use_darkhouse = false;
		}
		top.setSize(560, 840);
		// TODO: this doesn't work. it's not essential, but it would be kinda cool.
		top.setTitle("Breakout pre-release");

		top.add(sharedCompound);

		// top.getGCanvas().setAutoRepaintFlag(false);
		top.setBackground(Color.BLACK);
	}

	/**
	 * updates Client and lighthouse (if setting says so) to reflect a model.
	 *
	 * @param model
	 *            the base model.
	 */

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

		if (use_darkhouse) {
			darkhouse.update(sharedCompound);
		}
		top.repaint();
		;
	}

	/**
	 * Adds a GObject representing a given BObject to the view.
	 *
	 * @param o
	 *            the BObject to represent.
	 */
	private void addObject(BObject o) {
		GObject g;
		if (o instanceof BLaser) {
			g = new GOval(o.getX(),o.getY(),o.getWith(),o.getHeight());
			((GFillable) g).setFilled(true);
			((GFillable) g).setFillColor(o.getColor());
		} else if (o instanceof BBall) {
			// TODO: width and height scaling.
			g = new GImage("FootballLQ.png", o.getX(), o.getY());

		} else if (o instanceof BText) {
			g = new GLabel(((BText) o).getText(), o.getX(), o.getY());
			g.setColor(o.getColor());
			top.add(g);
			return;
		} else if (o instanceof BExplosion) {
			g = new GLabel("hier sollte eine Explosion sein xD", o.getX(), o.getY());
			g.setColor(o.getColor());
		} else if (o instanceof BBoss) {
			g = new GRect(o.getX(), o.getHeight(), o.getWith(), o.getHeight());
			((GFillable) g).setFilled(true);
			((GFillable) g).setFillColor(o.getColor());
		} else {
			g = new GRect(o.getX(), o.getY(), o.getWith(), o.getHeight());
			((GFillable) g).setFilled(true);
			((GFillable) g).setFillColor(o.getColor());
		}
		sharedCompound.add(g);
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
			if (link.getB() instanceof BText) {
				top.remove(link.getG());
			} else {
				sharedCompound.remove(link.getG());
			}
			links.remove(link);
		}
	}

	/**
	 * looks if the representations of a in the view have different coordinates than
	 * the concept in the model. if yes, the view needs to be updated.
	 */
	public void moved() {
		for (BLink link : links) {
			if (link.hasMoved()) {
				link.getG().setLocation(link.getB().getX(), link.getB().getY());
			}
		}
	}
}
