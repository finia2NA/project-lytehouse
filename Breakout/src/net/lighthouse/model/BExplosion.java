package net.lighthouse.model;

import java.awt.Color;

public class BExplosion extends BObject implements Multiframe {
	private int numberOfFrame;
	private double opacity;
	private Color color;

	BExplosion(int x, int y, Color color, double opacity) {
		super(x, y, 0, 0);
		this.color = color;
		this.opacity = opacity;
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
