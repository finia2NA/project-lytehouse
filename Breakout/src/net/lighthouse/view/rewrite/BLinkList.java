package net.lighthouse.view.rewrite;

import java.util.ArrayList;

import acm.graphics.GObject;
import net.lighthouse.model.BObject;

/**
 * An ArrayList for BLinks with additional BLink specific methods. Despite the
 * confusing name this class extends ArrayList and not linkedList.
 * 
 * @author finite
 *
 */
public class BLinkList extends ArrayList<BLink> {
	public boolean containsBObject(BObject o) {
		for (BLink b : this) {
			// this is actually supposed to be == and not equals.
			if (b.getO() == o) {
				return true;
			}
		}
		return false;
	}

	public GObject getGraphics(BObject o) {
		for (BLink b : this) {
			// this is actually supposed to be == and not equals.
			if (b.getO() == o) {
				return b.getG();
			}
		}
		return null;
	}
}
