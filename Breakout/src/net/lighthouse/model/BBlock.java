package net.lighthouse.model;

import java.awt.Color;

/**
 * A Model representation for a block in lighthouse
 * 
 * @author finite
 *
 */
public class BBlock extends BObject {


	public BBlock(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}

	// (Diese Klasse existiert nur damit die View instanceOf BBlock sagen kann.)
}
