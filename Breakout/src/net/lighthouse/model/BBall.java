package net.lighthouse.model;

import java.awt.Color;

//Extended BBlock weil ich die beiden Klassen geschrieben habe und dann bemerkt habe dass ein BBall alle Methoden von BBlock braucht + die speedmethoden.
public class BBall extends BBlock {
	// index 0->x-Speed, index 1->y-Speed
	private int[] speed;

	BBall(int x, int y, Color color, int[] speed) {
		super(x, y, 40, 60, color);
		this.speed = speed;
	}

    /**
     * Ball constructor.
     *
     * @param x       pos of the ball.
     * @param y       pos of the ball.
     * @param color   of the ball.
     * @param opacity of the ball.
     * @param speedX  of the ball.
     * @param speedY  of the ball.
     */
    BBall(int x, int y, Color color, int speedX, int speedY) {
        super(x, y, 40, 60, color);
        speed = new int[2];
        speed[0] = speedX;
        speed[1] = speedY;
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

	/**
	 * Updates the position of the ball depending on the current position and the
	 * speed.
	 */
	public void move() {
		this.setX(nextX());
		this.setY(nextY());
	}

	/**
	 * Returns the next x position of a ball.
	 *
	 * @return next x position
	 */
	public int nextX() {
		return this.getX() + this.speed[0];
	}

	/**
	 * Returns the next y position of a ball.
	 *
	 * @return next y position
	 */
	public int nextY() {
		return this.getY() + this.speed[1];
	}
}
