package net.lighthouse.model;

import java.awt.Color;

/**
 * Represents a block in the game.
 * This class does not add any new functionality that does not
 * exits in BObject but it needed to determine blocks when rendering the game.
 *
 * @author finite, Christoph Fricke
 */
public class BBlock extends BObject {

    /**
     * Creates a new BBlock.
     *
     * @param x      x position of the object
     * @param y      y position of the object
     * @param width  Width of the object
     * @param height Height of the object
     * @param color  Color of the object
     */
    public BBlock(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
}
