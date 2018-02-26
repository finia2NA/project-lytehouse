package net.lighthouse.view;

import java.awt.Color;

//UPDATE: Diese klasse ist doch nützlich.
/**
 * A renderable BObject allways has one or more BPixels. Each of these Pixels
 * has an Offset from the X/Y of the Object, a Color and an Opacity.
 * 
 * @author undif
 *
 */
public class BPixel {
	private int x;
	private int y;
	private Color color;
	private double opacity;

	public BPixel(int x, int y, Color color, double opacity) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.opacity = opacity;
	}

	public Color getColor() {

		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int offsetX) {
		this.x = offsetX;
	}

	public void setY(int offsetY) {
		this.y = offsetY;
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
