package net.lighthouse.model;

/**
 * BObjects that implement Multiframe have intrinsic animations. When they are
 * Renderable and getShape is called they return the shape of the current frame.
 * 
 * @author undif
 *
 */
public interface Multiframe {
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
	public void setFrame(int numberOfFrame);

	/**
	 * increments the number of the current frame.
	 */
	public void incrementFrame();
}
