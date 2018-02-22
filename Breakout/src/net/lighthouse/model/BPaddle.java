package net.lighthouse.model;

import java.awt.Color;

public class BPaddle extends BObject implements renderable {
	BPixel[] shape;

	BPaddle(int X, int Y, Color color, boolean visible) {
		super(X, Y);
		BPixel links = new BPixel(-1, 0, color, 1);
		BPixel mitte = new BPixel(0, 0, color, 1);
		BPixel rechts = new BPixel(1, 0, color, 1);
		shape = new BPixel[1];
		shape[0] = links;
		shape[1] = mitte;
		shape[2] = rechts;
	}

	@Override
	public void setColor(Color color) {
		for (BPixel atm : shape) {
			atm.setColor(color);
		}
	}

	@Override
	public Color getColor() {
		// returnt die Farbe vom mittleren weil sie eigentlich alle die selbe Farbe
		// haben sollten.
		return shape[0].getColor();

	}

	@Override
	public BPixel[] getShape() {
		return shape;
	}

	@Override
	public void setOpacity(double opacity) {
		for (BPixel atm : shape) {
			atm.setOpacity(opacity);;
		}
	}

}
