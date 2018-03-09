package net.lighthouse.model;

import java.awt.Color;

/**
 * A BLaser acts like a BBall but is not rendered with a image and is controlled
 * by its own controller.
 *
 * @author Christoph Fricke
 */
public class BLaser extends BBall {
    /**
     * Creates a new BLaser.
     *
     * @param x     x position of the laser
     * @param y     y position of the laser
     * @param speed speed the laser moves with. See {@link BBall} for an instruction
     *              on how to construct the speed object.
     */
    public BLaser(int x, int y, int[] speed) {
        super(x, y, Color.RED, speed);
    }
}
