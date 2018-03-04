package net.lighthouse.model;

import java.awt.Color;

public class BBlock extends BObject implements Renderable {
	private Color color;
	private double opacity;

	public BBlock(int x, int y, Color color, double opacity, int width, int height) {
		super(x, y, width, height);
		this.opacity = opacity;
		this.color = color;
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
		assert opacity <= 1 && opacity >= 0;
		this.opacity = opacity;
	}

	@Override
	public double getOpacity() {
		return opacity;
	}
}
