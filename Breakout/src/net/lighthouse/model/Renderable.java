package net.lighthouse.model;

import java.awt.Color;
import java.awt.Shape;

/**
 * BObjects that implement this interface are Renderable. Pretty much all
 * BObjects do that, but maybe one day there'll be a day where we need a
 * non-Renderable BObject. And that day will be great bc then we can say that
 * there was a reason why we didn't just write these methods into BObject.
 *
 * @author undif
 */
public interface Renderable {

	/**
	 * sets the Color of an Object
	 */
	public void setColor(Color color);

	/**
	 * returns the color of the Object.
	 *
	 * @return the color of the Object.
	 */
	public Color getColor();

	/**
	 * sets the opacity of an object.
	 *
	 * @param opacity
	 *            {@code 0} object is invisible, {@code 1} object is visible
	 */
	public void setOpacity(double opacity);

	public double getOpacity();

}
