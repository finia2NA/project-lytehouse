package net.lighthouse.util;

import net.lighthouse.model.BBlock;

import java.awt.Color;
import java.util.ArrayList;

/**
 * BlockList is an ArrayList for Blocks with some Block-specific methods.
 */

public class BlockList extends ArrayList<BBlock> {
    /**
     * gets the block at a given position. note that that position does not have to
     * be the top left corner of the block.
     *
     * @param x to look at.
     * @param y to look at.
     */
    public BBlock getBlockAtXY(int x, int y) {
        for (BBlock block : this) {
            if ((x >= block.getX() && x <= block.getX() + block.getWith()) &&
                (y >= block.getY() && y <= block.getY() + block.getHeight())) {
                return block;
            }
        }
        return null;
    }
}
