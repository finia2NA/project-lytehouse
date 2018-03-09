package net.lighthouse.view.rewrite;

import acm.graphics.GImage;
import acm.graphics.GLabel;
//ACM
import acm.graphics.GObject;
import acm.graphics.GFillable;
//LIGHTHOUSE
import net.lighthouse.model.BObject;

/**
 * A class that links an Object in the Model to a representation in the View.
 * 
 * @author finite
 *
 */
class BLink {
	// The Model Representation that is linked to..
	private BObject b;
	// ..the ACM GObject Representation.
	private GObject g;

	/**
	 * Constructs a new Link between a BObject and a GObject.
	 * 
	 * @param b
	 *            the BObject.
	 * @param g
	 *            the GObject.
	 */
	public BLink(BObject b, GObject g) {
		this.b = b;
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

	/**
	 * indicates wether o and g are out of sync in terms of coordinates.
	 * 
	 * @return {@code true} g needs to be updated, {@code false} g and o are in
	 *         sync.
	 */
	public boolean hasChameleoned() {
		if (g instanceof GImage||g instanceof GLabel) {
			return false;
		}
		if (b.getColor().equals(((GFillable) g).getFillColor())) {
			return false;
		} else {
			return true;
		}
	}
}
