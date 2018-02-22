package net.lighthouse.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

public class BBlock extends BObject implements renderable {
	BPixel[] shape;

	BBlock(int X, int Y, Color color, boolean visible) {
		super(X, Y);
		BPixel atm = new BPixel(0, 0, color, 1);
		shape = new BPixel[1];
		shape[0] = atm;
	}

	@Override
	public void setColor(Color color) {
		shape[0].setColor(color);
	}

	@Override
	public Color getColor() {
		return shape[0].getColor();
	}

	@Override
	public BPixel[] getShape() {
		return shape;
	}

	@Override
	public void setOpacity(double opacity) {
		shape[0].setOpacity(opacity);

	}
}
