package net.lighthouse.view.legacy;

//JAVA
import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
//ACM
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//OUR STUFF
import net.lighthouse.model.BBall;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.BPaddle;
import net.lighthouse.util.BlockList;
import net.lighthouse.model.MainModel;

/**
 * @deprecated Extremely inefficient implementation of client view refreshs. no
 *             support for explosions or text or endblock.
 * 
 * @author finite
 *
 */
public class legacyClientView {
	GraphicsProgram top;

	public legacyClientView(GraphicsProgram top) {
		this.top = top;
	}

	/**
	 * initializes the Client View.
	 */
	public void init() {
		top.setSize(560, 840);
		// TODO: this doesn't work. it's not essential, but it would be kinda cool.
		top.setTitle("Breakout pre-release");
		top.getGCanvas().setAutoRepaintFlag(false);
		top.setBackground(Color.BLACK);
	}

	/**
	 * completely redraws a Panel from a model. This is not the way to do it since
	 * we target a refresh rate of 50hz and the image will flicker at that
	 * framerate.
	 * 
	 * @param model
	 *            the model to draw.
	 */
	public void reDraw(MainModel model) {
		top.removeAll();
		drawBlocks(model.getBlocks());
		drawBalls(model.getAllBalls());
		drawPaddle(model.getPaddle());
		// manually repainting after everything is set and done should drastically
		// enhance performance.
		top.repaint();
	}

	/**
	 * draws the paddle.
	 * 
	 * @param paddle
	 *            to draw.
	 */
	private void drawPaddle(BPaddle paddle) {
		// gets all the info we need from the paddle.
		int x = paddle.getX();
		int y = paddle.getY();
		Color color = paddle.getColor();

		GRect paddleRect = new GRect(x, y, paddle.getWith(), paddle.getHeight());
		paddleRect.setFilled(true);
		paddleRect.setFillColor(color);
		top.add(paddleRect);
	}

	/**
	 * draws the balls
	 * 
	 * @param balls
	 *            to draw.
	 */
	private void drawBalls(ArrayList<BBall> balls) {
		for (BBall ball : balls) {
			// gets all the info we need from the ball.
			int x = ball.getX();
			int y = ball.getY();
			Color color = ball.getColor();

			// The ball is not a perfect circle because the lighthouse pixels are not round.

			GImage football = new GImage("FootballLQ.png", x, y);
			top.add(football);
		}
	}

	/**
	 * draws the blocks.
	 * 
	 * @param blocks
	 *            to draw.
	 */
	private void drawBlocks(BlockList blocks) {
		for (BBlock block : blocks) {
			// gets all the info we need from the block.
			int x = block.getX();
			int y = block.getY();
			Color color = block.getColor();

			GRect blockRect = new GRect(x, y, 80, 60);
			blockRect.setFilled(true);
			blockRect.setFillColor(color);
			top.add(blockRect);
		}
	}
}