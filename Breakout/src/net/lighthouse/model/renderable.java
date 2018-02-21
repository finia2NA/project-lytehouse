package net.lighthouse.model;

import java.awt.Color;
import java.awt.Shape;

/**
 * BObjects that implement this interface are renderable. Pretty much all
 * BObjects do that, but maybe one day there'll be a day where we need a
 * non-renderable BObject. And that day will be great bc then we can say that
 * there was a reason why we didn't just write these methods into BObject.
 * 
 * @author undif
 *
 */
public interface renderable {

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
	 * sets the opacity of an object to true or false.
	 * 
	 * @param visibility
	 *            {@code 0} object is invisible, {@code 100} object is visible
	 */
	public void setOpacity(double opacity);

	/**
	 * returns the Shape ob an object so the renderer knows what to do .
	 * 
	 * @return a BPixel array representing the Object.
	 */
	public BPixel[] getShape();

}