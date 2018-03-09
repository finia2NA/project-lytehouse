package net.lighthouse.view.rewrite;

//ACM
import acm.graphics.GObject;
//LIGHTHOUSE
import net.lighthouse.model.BObject;

/**
 * A class that links an Object in the Model to a representation in the View.
 * 
 * @author finite
 *
 */
class BLink {
	private BObject b;
	private GObject g;

	public BLink(BObject o, GObject g) {
		this.b = o;
		this.g = g;
	}

	/**
	 * @return the b
	 */
	BObject getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	void setB(BObject b) {
		this.b = b;
	}

	/**
	 * @return the g
	 */
	GObject getG() {
		return g;
	}

	/**
	 * @param g
	 *            the g to set
	 */
	protected void setG(GObject g) {
		this.g = g;
	}

	/**
	 * @return the xy
	 */
	/**
	 * indicates wether o and g are out of sync in terms of coordinates.
	 * 
	 * @return {@code true} g needs to be moved, {@code false} g and o are in sync.
	 */
	boolean hasMoved() {
		int[] atm = { (int) g.getX(), (int) g.getY() };

		if (b.getXY().equals(atm)) {
			return false;
		} else {
			return true;
		}
	}
}
