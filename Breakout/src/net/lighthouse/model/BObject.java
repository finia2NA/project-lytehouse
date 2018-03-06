package net.lighthouse.model;

import java.awt.Color;

/**
 * The most basic Breakout Object. Has a position.
 *
 * @author finite
 */
public class BObject {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;

	/**
	 * Constructs a new BObject with a position.
	 *
	 * @param x
	 *            x.
	 * @param y
	 *            y.
	 */
	BObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	BObject(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	// All the Methods beyond this point are just getters and setters and do pretty
	// much what you'd expect them to do.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[] getXY() {
		int[] re = { x, y };
		return re;
	}

	public int getWith() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setX(int X) {
		this.x = X;
	}

	public void setY(int Y) {
		this.y = Y;
	}

	public void setXY(int X, int Y) {
		this.x = X;
		this.y = Y;
	}
}
