package net.lighthouse.model;

import java.awt.Color;

/**
 * A renderable BObject allways has one or more BPixels. Each of these Pixels
 * has an Offset from the X/Y of the Object, a Color and an Opacity.
 * 
 * @author undif
 *
 */
public class BPixel {
	private int offsetX;
	private int offsetY;
	private Color color;
	private double opacity;

	public BPixel(int offsetX, int offsetY, Color color, double opacity) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.color = color;
		this.opacity = opacity;
	}

	public Color getColor() {
		return color;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public void setColor(Color color) {
		assert color != null;
		this.color = color;
	}

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		assert opacity > 0 && opacity <= 1;
		this.opacity = opacity;
	}
}
