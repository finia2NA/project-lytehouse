package net.lighthouse.view.rewrite;

import acm.graphics.GObject;
import net.lighthouse.model.BObject;

/**
 * A class that links an Object in the Model to a representation in the view.
 * 
 * @author finite
 *
 */
public class BLink {
	private BObject o;
	private GObject g;

	public BLink(BObject o, GObject g) {
		this.o = o;
		this.g = g;
	}

	/**
	 * @return the o
	 */
	public BObject getO() {
		return o;
	}

	/**
	 * @param o
	 *            the o to set
	 */
	public void setO(BObject o) {
		this.o = o;
	}

	/**
	 * @return the g
	 */
	public GObject getG() {
		return g;
	}

	/**
	 * @param g
	 *            the g to set
	 */
	public void setG(GObject g) {
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
	public boolean hasMoved() {
		int[] atm = { (int) g.getX(), (int) g.getY() };

		if (o.getXY().equals(atm)) {
			return false;
		} else {
			return true;
		}
	}
}
