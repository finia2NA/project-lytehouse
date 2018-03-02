package net.lighthouse.collision;

import net.lighthouse.model.*;

/**
 * Provide a various collision checks for a ball object.
 * Can check a collision withe the paddle, borders and blocks.
 * It will update the speed of the given ball accordingly and
 * might provide some other useful information for further
 * game behaviour.
 *
 * @author Christoph Fricke
 */
public class CollisionChecker {

    /**
     * Ball object the CollisionChecker works for.
     */
    private BBall ball;

    /**
     * Creates a new CollisionChecker.
     *
     * @param ball Object the Checker checks for.
     */
    public CollisionChecker(BBall ball) {
        this.ball = ball;
    }

    /**
     * Handles a collision with the paddle and changes the direction accordingly.
     * Depending on where the ball hits the paddle the X direction will grow or shrink too.
     *
     * @param paddle Paddle we might collide with.
     */
    public void handlePaddleCollision(BPaddle paddle) {
        boolean affectsPaddleY = ball.nextY() + ball.getHeight() >= paddle.getY();
        if (!affectsPaddleY) return;

        int ballX = ball.nextX();
        int ballWidth = ballX + ball.getWith();
        int paddleX = paddle.getX();
        int paddleWidth = paddleX + paddle.getWith();

        boolean affectsPaddleXMiddle = ballX >= paddleX && ballWidth <= paddleWidth;
        // Check of only some part of the ball will hit the paddle.
        boolean affectsPaddleXEdgeLeft = ballX < paddleX && ballWidth > paddleX;
        boolean affectsPaddleXEdgeRight = ballX < paddleWidth && ballWidth > paddleWidth;

        if (affectsPaddleXEdgeLeft) {
            int[] newBallSpeed = {ball.getSpeed()[0] - 2, ball.getSpeed()[1] * -1};
            ball.setSpeed(newBallSpeed);

        } else if (affectsPaddleXEdgeRight) {
            int[] newBallSpeed = {ball.getSpeed()[0] + 2, ball.getSpeed()[1] * -1};
            ball.setSpeed(newBallSpeed);

        } else if (affectsPaddleXMiddle) {
            int paddlePartLength = paddleWidth / 3;
            int ballCenter = ballWidth / 2;

            if (ballCenter <= paddlePartLength) {
                int[] newBallSpeed = {ball.getSpeed()[0] - 2, ball.getSpeed()[1] * -1};
                ball.setSpeed(newBallSpeed);

            } else if (ballCenter <= paddlePartLength * 2) {
                int[] newBallSpeed = {ball.getSpeed()[0], ball.getSpeed()[1] * -1};
                ball.setSpeed(newBallSpeed);

            } else if (ballCenter <= paddleWidth) {
                int[] newBallSpeed = {ball.getSpeed()[0] + 2, ball.getSpeed()[1] * -1};
                ball.setSpeed(newBallSpeed);
            }
        }
    }

    /**
     * Checks if the ball will leave the borders if the window and changes the direction accordingly.
     *
     * @param width   Width of the view.
     * @param paddleY Y position of the paddle. This is the line which decides between a lose or not.
     *
     * @return True if the game still can run. False if the ball hits the lower border.
     */
    public boolean handleBorderCollision(int width, int paddleY) {
        boolean switchX = ball.nextX() <= 0 || ball.nextX() + ball.getWith() >= width;
        boolean switchY = ball.nextY() <= 0;
        boolean endGame = ball.nextY() + ball.getHeight() >= paddleY;

        if (switchX) {
            int[] newBallSpeed = {ball.getSpeed()[0] * -1, ball.getSpeed()[1]};
            ball.setSpeed(newBallSpeed);
            return true;
        } else if (switchY) {
            int[] newBallSpeed = {ball.getSpeed()[0], ball.getSpeed()[1] * -1};
            ball.setSpeed(newBallSpeed);
            return true;
        } else if (endGame) {
            int[] newBallSpeed = {0, 0};
            ball.setSpeed(newBallSpeed);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if the ball collides with a block and changes the ball direction accordingly.
     *
     * @param blocks BlockList that contains all blocks that are currently in the game.
     *
     * @return Array of BBlocks that are hit by the ball.
     */
    public BBlock[] handleBlockCollision(BlockList blocks) {
        // Contains block that might have hit on a possible location.
        // Format: XUpperLeft, XUpperRight, XLowerLeft, XLowerRight, YUpperLeft, YLowerLeft, YUpperRight, YLowerRight
        BBlock[] blockList = new BBlock[8];

        // Extracted variables to optimize for performance
        int ballX = ball.nextX();
        int ballY = ball.nextY();
        int ballWidth = ballX + ball.getWith();
        int ballHeight = ballY + ball.getHeight();

        // Check all corner situations.
        blockList[0] = blocks.getBlockAtXY(ballX + 1, ballY);
        blockList[1] = blocks.getBlockAtXY(ballWidth - 1, ballY);
        blockList[2] = blocks.getBlockAtXY(ballX + 1, ballHeight);
        blockList[3] = blocks.getBlockAtXY(ballWidth - 1, ballHeight);

        blockList[4] = blocks.getBlockAtXY(ballX, ballY + 1);
        blockList[5] = blocks.getBlockAtXY(ballX, ballHeight - 1);
        blockList[6] = blocks.getBlockAtXY(ballWidth, ballY + 1);
        blockList[7] = blocks.getBlockAtXY(ballWidth, ballHeight - 1);

        // Remove possible duplicates
        if (blockList[0] == blockList[1]) blockList[1] = null;
        if (blockList[2] == blockList[3]) blockList[3] = null;
        if (blockList[4] == blockList[5]) blockList[5] = null;
        if (blockList[6] == blockList[7]) blockList[7] = null;

        // Count is used to know how big the array with affected blocks will be.
        int count = 0;
        // Change object speed depending on Collisions and count how many blocks are affected.
        for (int i = 0; i < blockList.length; i += 2) {
            if (blockList[i] != null || blockList[i + 1] != null) {
                count = blockList[i] != null ? count + 1 : count;
                count = blockList[i + 1] != null ? count + 1 : count;

                if (i < 4) {
                    // Collision X => Change Y
                    int[] newBallSpeed = {ball.getSpeed()[0], ball.getSpeed()[1] * -1};
                    ball.setSpeed(newBallSpeed);
                } else {
                    // Collision Y => Change X
                    int[] newBallSpeed = {ball.getSpeed()[0] * -1, ball.getSpeed()[1]};
                    ball.setSpeed(newBallSpeed);
                }
            }
        }

        BBlock[] affectedBlocks = new BBlock[count];
        // Reduce array to return
        for (BBlock block : blockList) {
            if (block != null) {
                affectedBlocks[--count] = block;
            }
        }

        return affectedBlocks;
    }
}
