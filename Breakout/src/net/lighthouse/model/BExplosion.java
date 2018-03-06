package net.lighthouse.model;

import java.awt.Color;

/**
 * A model representation for an explosion.
 * 
 * @author finite
 *
 */
public class BExplosion extends BObject implements Multiframe {
	private int numberOfFrame;

	/**
	 * Construcs a new Explosion.
	 * 
	 * @param x
	 *            the xpos of the Explosion.
	 * @param y
	 * @param color
	 */
	BExplosion(int x, int y, Color color) {
		// Explosions don't really have a width/height, so I'm setting them as -1.
		super(x, y, -1, -1, color);
		numberOfFrame = 0;
	}

	@Override
	public int getFrame() {
		return numberOfFrame;
	}

	@Override
	public void setFrame(int numberOfFrame) {
		this.numberOfFrame = numberOfFrame;
	}

	@Override
	public void incrementFrame() {
		numberOfFrame++;
	}

}
