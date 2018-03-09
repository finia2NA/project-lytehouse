package net.lighthouse.model;

import net.lighthouse.levels.LevelManager;
import net.lighthouse.model.BlockList;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main Model holding all blocks, balls, the paddle,texts and all effects.
 * 
 * @author finite
 */

public class MainModel implements Iterable<BObject> {
	private BlockList blocks;
	// We'll definitly one have one paddle so this is not an ArrayList.
	private BPaddle paddle;

	// IDK, maybe one day we'll have a gamemode/powerup where we'll have multiple
	// BBalls.
	private ArrayList<BBall> balls = new ArrayList<>();
	private ArrayList<BExplosion> effects = new ArrayList<>();
	private ArrayList<BText> texts = new ArrayList<>();
	private ArrayList<BLaser> lasers = new ArrayList<>();
	private BBoss boss;

	/**
	 * Keeps track of the current userScore.
	 */
	public double userScore;

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

		this.userScore = 0;
	}

	/**
	 * Creates a MainModel with preconfigured Blocks, paddle and blocks.
	 */
	public MainModel() {
		balls = new ArrayList<BBall>();
		balls.add(new BBall(560 / 2, 840 / 2, Color.BLUE, 1, 1));

		paddle = new BPaddle(560 / 2 - 80, 840 - 60, Color.CYAN, 1);

		blocks = LevelManager.getRandomLevel();
	}

	/**
	 * creates a new Mainmodel with just a text.
	 * 
	 * @param text
	 */
	public MainModel(ArrayList<BText> text) {
		this.texts = text;
		this.blocks = new BlockList();
	}

	public void addExplosion(int x, int y, Color color) {
		effects.add(new BExplosion(x, y, color));

	}

	/**
	 * adds an object to the model.
	 *
	 * @param object
	 *            the object to add.
	 */
	public void addObject(BObject object) {
		if (object instanceof BLaser) {
			lasers.add((BLaser) object);

		} else if (object instanceof BBall) {
			balls.add((BBall) object);

		} else if (object instanceof BBoss) {
			boss = (BBoss) object;

		} else if (object instanceof BBlock) {
			blocks.add((BBlock) object);

		} else if (object instanceof BExplosion) {
			effects.add((BExplosion) object);

		} else if (object instanceof BPaddle) {
			paddle = (BPaddle) object;
			// Ich glaube nicht dass es einen usecase gibt wo man absichtlich das paddle
			// durch ein neues ersetzt, deshalb die Warnung.
			System.out.println("Warning on addObject: paddle has just been replaced.");
		} else if (object instanceof BText) {
			texts.add((BText) object);
		}
	}

	/**
	 * Gets all Blocks currently in the model.
	 *
	 * @return all Blocks currently in the model.
	 */
	public BlockList getBlocks() {
		return blocks;
	}

	/**
	 * returns how many balls there are in the model right now.
	 *
	 * @return how many balls there are in the model right now.
	 */
	public int getNumberOfBalls() {
		return balls.size();
	}

	/**
	 * returns an ArrayList containing all balls in the model rightn now.
	 *
	 * @returnan ArrayList containing all balls in the model rightn now.
	 */
	public ArrayList<BBall> getAllBalls() {
		return balls;
	}

	/**
	 * gets a specific ball.
	 *
	 * @param ballIndex
	 *            the ball to get.
	 * @return the ball.
	 */
	public BBall getBall(int ballIndex) {
		assert ballIndex < balls.size();
		return balls.get(ballIndex);
	}

	/**
	 * gets the first ball in the model.
	 *
	 * @return the first ball in the model.
	 */
	public BBall getBall() {
		return balls.get(0);
	}

	/**
	 * gets the paddle.
	 *
	 * @return the paddle.
	 */
	public BPaddle getPaddle() {
		return paddle;
	}

	public BBoss getBoss() {
		return boss;
	}

	public void removeBoss() {
		boss = null;
	}

	public ArrayList<BLaser> getLasers() {
		return lasers;
	}

	/**
	 * returns all text objects in the model.
	 *
	 * @return the sacred texts!
	 */
	public ArrayList<BText> getTexts() {
		return texts;
	}

	/**
	 * sets the list of texts in the model.
	 *
	 * @param texts
	 *            the texts to set.
	 */
	public void setTexts(ArrayList<BText> texts) {
		this.texts = texts;
	}

	/**
	 * returns an ArrayList of all BObjects in the model.
	 *
	 * @returnan ArrayList of all BObjects in the model.
	 */
	public ArrayList<BObject> toArrayList() {
		// Maybe you schould cache the current arraylist in an object variable so all of
		// this stuff doesn't have to be called for so often in main view.
		ArrayList<BObject> objectList = new ArrayList<BObject>();
		for (BBall ball : balls) {
			objectList.add(ball);
		}
		for (BLaser laser : lasers) {
			objectList.add(laser);
		}
		if (boss != null) {
			objectList.add(boss);
		}
		objectList.add(paddle);
		for (BBlock block : blocks) {
			objectList.add(block);
		}
		for (BExplosion explosion : effects) {
			objectList.add(explosion);
		}
		for (BText text : texts) {
			objectList.add(text);
		}

		return objectList;
	}

	/**
	 * Checks if a BObject is in the model.
	 *
	 * @param o
	 *            the Object to look for.
	 * @return {@code true} the object is in the model, {@code false} the object is
	 *         not in the model.
	 */
	public boolean contains(BObject o) {
		if (toArrayList().contains(o)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * returns an Iterator that iterates though all BObjects in the Model.
	 */
	@Override
	public Iterator<BObject> iterator() {
		return toArrayList().iterator();
	}
}
