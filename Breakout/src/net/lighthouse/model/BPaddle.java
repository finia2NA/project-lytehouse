package net.lighthouse.model;

import java.awt.Color;

import net.lighthouse.view.BPixel;

public class BPaddle extends BObject implements renderable {
	private Color color;
	private double opacity;

	BPaddle(int x, int y, Color color, double opacity) {
		super(x, y);
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
		assert opacity <= 1 && opacity >= 0;
		this.opacity = opacity;
	}

	@Override
	public double getOpacity() {
		return opacity;
	}

}
