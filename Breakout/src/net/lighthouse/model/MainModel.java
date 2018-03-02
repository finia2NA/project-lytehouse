package net.lighthouse.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the main Model holding all blocks, balls the paddle and all effects.
 * 
 * @author finite
 *
 */

public class MainModel {
	private BlockList blocks;
	// We'll definitly one have one paddle so this is not an ArrayList.
	private BPaddle paddle;
	// IDK, maybe one day we'll have a gamemode/powerup where we'll have multiple
	// BBalls.
	private ArrayList<BBall> balls;
	private ArrayList<Multiframe> effects;

	/**
	 * Creates a Model with all custom data. but no starting explosions :(
	 * 
	 * @param paddle
	 *            the paddle.
	 * @param ball
	 *            the ball.
	 * @param blocks
	 *            the blocks.
	 */
	public MainModel(BPaddle paddle, BBall ball, BlockList blocks) {
		this.paddle = paddle;
		balls.add(ball);
		this.blocks = blocks;
	}

	/**
	 * Creates a MainModel with preconfigured Blocks, paddle and blocks.
	 */
	public MainModel() {
		balls = new ArrayList<BBall>();
		balls.add(new BBall(560 / 2, 840 / 2, Color.BLUE, 1, 1, 1));

		paddle = new BPaddle(560 / 2 - 80, 840 - 60, Color.CYAN, 1);

		blocks = new BlockList();
		blocks.defaultFill();
	}

	public void addExplosion(int x, int y, Color color) {
		effects.add(new BExplosion(x, y, color, 1));

	}

	/**
	 * adds an object to the model.
	 * 
	 * @param object
	 *            the object to add.
	 */
	public void addObject(BObject object) {
		if (object instanceof BBall) {
			balls.add((BBall) object);

		} else if (object instanceof BBlock) {
			blocks.add((BBlock) object);

		} else if (object instanceof Multiframe) {
			effects.add((Multiframe) object);

		} else if (object instanceof BPaddle) {
			paddle = (BPaddle) object;

			// Ich glaube nicht dass es einen usecase gibt wo man absichtlich das paddle
			// durch ein neues ersetzt, deshalb die Warnung.
			System.out.println("Warning on addObject: paddle has just been replaced.");
		}
	}

	public BlockList getBlocks() {
		return blocks;
	}

	public int getNumberOfBalls() {
		return balls.size();
	}

	public ArrayList<BBall> getAllBalls() {
		return balls;
	}

	public BBall getBall(int ballIndex) {
		return balls.get(ballIndex);
	}

	public BPaddle getPaddle() {
		return paddle;
	}
}
