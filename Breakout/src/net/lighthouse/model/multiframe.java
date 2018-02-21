package net.lighthouse.model;

/**
 * BObjects that implement multiframe have intrinsic animations. When they are
 * renderable and getShape is called they return the shape of the current frame.
 * 
 * @author undif
 *
 */
public interface multiframe {
	/**
	 * returns the number of the current frame of the animation.
	 * 
	 * @return the number of the current frame of the animation.
	 */
	public int getFrame();

	/**
	 * sets the number of the current frame of the animation.
	 * 
	 * @param frame
	 *            the number of the current frame of the animation.
	 */
	public void setFrame(int frame);

	/**
	 * increments the number of the current frame.
	 */
	public void incrementFrame();
}