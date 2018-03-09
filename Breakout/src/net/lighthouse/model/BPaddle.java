package net.lighthouse.model;

import java.awt.Color;

/**
 * A Model representation for the paddle.
 *
 * @author undif
 */
public class BPaddle extends BObject {

    /**
     * Paddle Constructor.
     *
     * @param x       of the paddle.
     * @param y       of the paddle.
     * @param color   of the paddle.
     */
    BPaddle(int x, int y, Color color) {
        super(x, y, 160, 60, color);
    }

    /**
     * Moves the paddle to a new supplied X position.
     *
     * @param nextX New X position of the paddle.
     */
    public void move(int nextX) {
        this.setX(nextX);
    }
}
