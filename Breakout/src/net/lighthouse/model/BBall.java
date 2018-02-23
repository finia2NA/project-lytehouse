package net.lighthouse.model;

import java.awt.Color;

//Extended BBlock weil ich die beiden Klassen geschrieben habe und dann bemerkt habe dass ein BBall alle Methoden von BBlock braucht + die speedmethoden.
public class BBall extends BBlock {
	// index 0->x-Speed, index 1->y-Speed
	private int[] speed;

	BBall(int x, int y, Color color, double opacity, int[] speed) {
		super(x, y, color, opacity);
		this.speed = speed;
	}

	/**
	 * returns the speed of the object {@code index 0} x-speed, {@code index 1}
	 * y-speed.
	 * 
	 * @return the speed of the object {@code index 0} x-speed, {@code index 1}
	 *         y-speed.
	 */
	public int[] getSpeed() {
		return speed;
	}

	/**
	 * sets the speed of the object {@code index 0} x-speed, {@code index 1}
	 * y-speed.
	 * 
	 * @param speed
	 *            of the object {@code index 0} x-speed, {@code index 1} y-speed.
	 */
	public void setSpeed(int[] speed) {
		this.speed = speed;
	}
}
