package net.lighthouse.model;

import java.awt.Color;
import java.util.ArrayList;

public class MainModel {
	ArrayList<BBlock> blocks;
	BPaddle paddle;
	BBall ball;

	/**
	 * Creates a Model with all custom data.
	 * 
	 * @param paddle
	 *            the paddle.
	 * @param ball
	 *            the ball.
	 * @param blocks
	 *            the blocks.
	 */
	public MainModel(BPaddle paddle, BBall ball, ArrayList<BBlock> blocks) {
		this.paddle = paddle;
		this.ball = ball;
		this.blocks = blocks;
	}

	/**
	 * Creates a MainModel with preconfigured Blocks, paddle and blocks.
	 */
	public MainModel() {
		int[] atm = {1, 1};
		ball = new BBall(7, 14, Color.BLUE, 1, atm);
		paddle = new BPaddle(7, 27, Color.WHITE, 1);
		blocks = new ArrayList<BBlock>();
		
	}
}
