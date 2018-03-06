package net.lighthouse.model;

import java.awt.Color;

/**
 * A Model representation for a block in lighthouse
 * 
 * @author finite
 *
 */
public class BBlock extends BObject {

	/**
	 * Constructs a new BBlock.
	 * @param x the xpos of the Block (leftmost).
	 * @param y the ypos of the Block (upper).
	 * @param width the width of the Block.
	 * @param height the height of the Block.
	 */
	public BBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public BBlock(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	// (Diese Klasse existiert nur damit die View instanceOf BBlock sagen kann.)
}
