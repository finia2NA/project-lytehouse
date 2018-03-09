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


    public BBlock(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }
}
