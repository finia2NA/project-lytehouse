package net.lighthouse.model;

import java.awt.Color;

/**
 * A BBall is a block that can move. Therefor every object of BBall has a speed property which
 * is needed to calculate a new position.
 *
 * @author finite, Christoph Fricke
 */
public class BBall extends BObject {
    /**
     * Speed of object. {@code index 0} is the x speed and
     * {@code index 1} is the y speed.
     */
    private int[] speed;

    /**
     * Creates a new BBall. A ball has a default size of 40x60.
     *
     * @param x     x position of the ball
     * @param y     y position of the ball
     * @param color color of the ball
     * @param speed Array which represents the speed of a BBall object. {@code index 0} is the x speed and
     *              {@code index 1} is the y speed.
     *
     * @throws IllegalArgumentException if {@code speed} is {@code null}.
     */
    BBall(int x, int y, Color color, int[] speed) {
        super(x, y, 40, 60, color);

        if (speed == null) {
            throw new IllegalArgumentException("Speed can not be null");
        }

        this.speed = speed;
    }

    /**
     * Creates a new BBall.
     *
     * @param x      x position of the ball
     * @param y      y position of the ball
     * @param color  color of the ball
     * @param speedX x speed of the ball
     * @param speedY y speed of the ball
     */
    BBall(int x, int y, Color color, int speedX, int speedY) {
        super(x, y, 40, 60, color);
        speed = new int[2];
        speed[0] = speedX;
        speed[1] = speedY;
    }

    /**
     * Returns the speed of the object {@code index 0} x-speed, {@code index 1}
     * y-speed.
     *
     * @return the speed of the object {@code index 0} x-speed, {@code index 1}
     * y-speed.
     */
    public int[] getSpeed() {
        return speed;
    }

    /**
     * sets the speed of the object {@code index 0} x-speed, {@code index 1}
     * y-speed.
     *
     * @param speed of the object {@code index 0} x-speed, {@code index 1} y-speed.
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
     * Returns the next x position of a ball. This does not set the new position.
     * It only predicts the future.
     *
     * @return next x position
     */
    public int nextX() {
        return this.getX() + this.speed[0];
    }

    /**
     * Returns the next y position of a ball. This does not set the new position.
     * It only predicts the future.
     *
     * @return next y position
     */
    public int nextY() {
        return this.getY() + this.speed[1];
    }
}
