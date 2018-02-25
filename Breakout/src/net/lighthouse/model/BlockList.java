package net.lighthouse.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * BlockList is an ArrayList for Blocks with some Block-specific methods.
 * 
 * @author undif
 *
 */

public class BlockList extends ArrayList<BBlock> {

	/**
	 * fills the top part of the model with blocks.
	 */
	public void defaultFill() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 7; x++) {

				// makes it so that the blocks are distinguishable.
				Color color;
				if ((x + y) % 2 == 0) {
					color = Color.yellow;
				} else {
					color = Color.cyan;
				}

				BBlock atm = new BBlock(x * 80, y * 60, color, 1);

				// adds the Block to the List.
				add(atm);
			}
		}
	}
}
