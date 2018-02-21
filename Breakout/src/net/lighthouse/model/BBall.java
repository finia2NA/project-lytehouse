package net.lighthouse.model;

import java.awt.Color;

//Extended BBlock weil ich die beiden Klassen geschrieben habe und dann bemerkt habe dass ein BBall alle Methoden von BBlock braucht + die speedmethoden.
public class BBall extends BBlock {
	private double speed;

	BBall(int X, int Y, Color color, boolean visible, double speed) {
		super(X, Y, color, visible);
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
