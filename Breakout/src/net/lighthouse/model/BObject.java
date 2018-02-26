package net.lighthouse.model;

/**
 * the most basic Breakout Object. Has a position.
 * 
 * @author undif
 *
 */
public class BObject {
	private int x;
	private int y;

	/**
	 * Constructs a new BObject with a position.
	 * 
	 * @param x
	 *            x.
	 * @param y
	 *            y.
	 */
	BObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// All the Methods beyond this point are just getters and setters and do pretty
	// much what you'd expect them to do.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[] getXY() {
		int[] re = { x, y };
		return re;
	}

	public void setX(int X) {
		this.x = X;
	}

	public void setY(int Y) {
		this.y = Y;
	}

	public void setXY(int X, int Y) {
		this.x = X;
		this.y = Y;
	}

}
