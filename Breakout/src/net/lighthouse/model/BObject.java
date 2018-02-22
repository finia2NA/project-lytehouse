package net.lighthouse.model;

/**
 * the most basic Breakout Object. Has a position.
 * 
 * @author undif
 *
 */
public class BObject {
	private int X;
	private int Y;

	/**
	 * Constructs a new BObject with a position.
	 * 
	 * @param X
	 *            X.
	 * @param Y
	 *            Y.
	 */
	BObject(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	// All the Methods beyond this point are jsut getters and setters and do pretty
	// much what you'd expect them to do.
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int[] getXY() {
		int[] re = { X, Y };
		return re;
	}

	public void setX(int X) {
		this.X = X;
	}

	public void setY(int Y) {
		this.Y = Y;
	}

	public void setXY(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

}
