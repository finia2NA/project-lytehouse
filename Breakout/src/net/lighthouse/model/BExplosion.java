package net.lighthouse.model;

import java.awt.Color;

public class BExplosion extends BObject implements multiframe, renderable {
	BPixel[][] animation;
	int numberOfFrame;

	BExplosion(int X, int Y, Color color, boolean visible) {
		super(X, Y);
		// TODO: frame Array.
	}

	@Override
	public void setColor(Color color) {
		for (BPixel[] currentFrame : animation) {
			for (BPixel p : currentFrame) {
				p.setColor(color);
			}
		}
	}

	@Override
	public Color getColor() {
		return animation[0][0].getColor();
	}

	@Override
	public BPixel[] getShape() {
		return animation[numberOfFrame];
	}

	@Override
	public void setOpacity(double opacity) {
		for (BPixel[] currentFrame : animation) {
			for (BPixel p : currentFrame) {
				p.setOpacity(opacity);
			}
		}
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
