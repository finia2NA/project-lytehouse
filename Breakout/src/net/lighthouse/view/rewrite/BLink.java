package net.lighthouse.view.rewrite;

import acm.graphics.GObject;
import net.lighthouse.model.BObject;

public class BLink {
	private BObject o;
	private GObject g;
	private int[] xy;

	public BLink(BObject o, GObject g) {
		this.o = o;
		this.g = g;
		this.xy = o.getXY();
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
	public int[] getXY() {
		return xy;
	}

	/**
	 * @param xy
	 *            the xy to set
	 */
	public void setXY(int[] xy) {
		this.xy = xy;
	}

	public boolean hasMoved() {
		if (o.getXY().equals(xy)) {
			return false;
		} else {
			return true;
		}
	}
}
