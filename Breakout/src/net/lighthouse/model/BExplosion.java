package net.lighthouse.model;

import java.awt.Color;

public class BExplosion extends BObject implements Multiframe, Renderable {
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
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setOpacity(double opacity) {
		this.opacity = opacity;
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

	@Override
	public double getOpacity() {
		return opacity;
	}
}
