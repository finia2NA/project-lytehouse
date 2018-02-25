package net.lighthouse.view;

//JAVA
import java.awt.Color;
import java.util.ArrayList;
//ACM
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
//OUR STUFF
import net.lighthouse.model.BBall;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.BPaddle;
import net.lighthouse.model.BlockList;
import net.lighthouse.model.MainModel;

public class ClientView {
	GraphicsProgram top;

	public ClientView(GraphicsProgram top) {
		this.top = top;
	}

	public void init() {
		top.setSize(560, 840);
		// TODO: this doesn't work. it's not essential, but it would be kinda cool.
		top.setTitle("Breakout pre-release");
	}

	/**
	 * completely redraws a Panel from a model. Maybe this is not the way to do it
	 * since we target a refresh rate of 50hz. We'll see.
	 * 
	 * @param model
	 *            the model to draw.
	 */
	public void reDraw(MainModel model) {
		// TODO: this may not be the right remove function. No way to be shure till I
		// try it out.
		top.removeAll();
		drawBlocks(model.getBlocks());
		drawBalls(model.getAllBalls());
		drawPaddle(model.getPaddle());
	}

	private void drawPaddle(BPaddle paddle) {
		// gets all the info we need from the paddle.
		int x = paddle.getX();
		int y = paddle.getY();
		Color color = paddle.getColor();

		GRect paddleRect = new GRect(x, y, 160, 60);
		paddleRect.setFilled(true);
		paddleRect.setFillColor(color);
		top.add(paddleRect);
	}

	private void drawBalls(ArrayList<BBall> balls) {
		for (BBall ball : balls) {
			// gets all the info we need from the ball.
			int x = ball.getX();
			int y = ball.getY();
			Color color = ball.getColor();

			// The ball is not a perfect circle because the lighthouse pixels are not round.
			GOval ballOval = new GOval(x, y, 40, 60);
			ballOval.setFilled(true);
			ballOval.setFillColor(color);
			top.add(ballOval);
		}

	}

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
