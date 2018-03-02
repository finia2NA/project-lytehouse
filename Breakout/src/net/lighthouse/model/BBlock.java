package net.lighthouse.model;

import java.awt.Color;

public class BBlock extends BObject {

	public BBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public BBlock(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}

	// Diese Klasse existiert nur damit die View instanceOf BBlock sagen kann.
}
