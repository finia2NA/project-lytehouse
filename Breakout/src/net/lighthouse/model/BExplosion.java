package net.lighthouse.model;

import java.awt.Color;

public class BExplosion extends BObject implements multiframe, renderable {
	private int numberOfFrame;
	private double opacity;
	private Color color;

	BExplosion(int X, int Y, Color color, double opacity) {
		super(X, Y);
		this.color = color;
		this.opacity = opacity;
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
