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

				BBlock atm = new BBlock(x * 80, y * 60, color, 1, 80, 60);

				// adds the Block to the List.
				add(atm);
			}
		}

	}

	// Da ich glaube dass getBlockAtXY oft hintereinander mit den selben XY-werten
	// ausgefuehrt werden wird, gibt es einen cache der den letzten block mit tags
	// speichert. (Direct Mapped mit einer Menge)
	int tagXlower = -1;
	int tagYlower = -1;
	BBlock cache;

	/**
	 * gets the block at a given position. note that that position does not have to
	 * be the top left corner of the block.
	 * 
	 * @param x
	 *            to look at.
	 * @param y
	 *            to look at.
	 */
	public BBlock getBlockAtXY(int x, int y) {
		if (x >= tagXlower && x < tagXlower + 80 && y < tagYlower + 60 && y >= tagYlower) {
			// hit!
			return cache;
		} else
			// wenn der cache ein miss war muessen wir wohl in der Liste gucken.
			for (BBlock block : this) {
				// riesige ueberpruefung ob die position zwischen x und y liegt.
				if ((block.getX() < (x + 1) * 80 && block.getX() >= x * 80) && (block.getY() < (y + 1) * 60)
						&& block.getY() >= y * 60) {
					cache = block;
					tagXlower = (x / 80) * 80; // integers ftw
					tagYlower = (y / 60) * 60;
					return block;
				}
			}
		return null;
	}
}
