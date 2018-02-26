package net.lighthouse.collision;

import net.lighthouse.model.*;

/**
 * @author Christoph Fricke
 */
public class CollisionChecker {

    BBall myObject;

    /**
     * Creates a new CollisionChecker.
     *
     * @param object Object the Checker checks for.
     */
    public CollisionChecker(BBall object) {
        myObject = object;
    }

    /**
     * Handles a collision with the paddle and changes the direction accordingly.
     *
     * @param paddle Paddle we might collide with.
     */
    public void handlePaddleCollision(BPaddle paddle) {
        boolean affectsPaddleY = myObject.nextY() + myObject.getHeight() >= paddle.getY();
        if (!affectsPaddleY) return;

        int objectX = myObject.nextX();
        int objectWidth = objectX + myObject.getWith();
        int paddleX = paddle.getX();
        int paddleWidth = paddleX + paddle.getWith();

        boolean affectsPaddleXMiddle = objectX >= paddleX && objectWidth <= paddleWidth;
        // Check of only some part of the ball will hit the paddle.
        boolean affectsPaddleXEdges = (objectX < paddleX && objectWidth > paddleX) || (objectX < paddleWidth && objectWidth > paddleWidth);

        boolean affectsPaddle = affectsPaddleXMiddle || affectsPaddleXEdges;

        if (affectsPaddle) {
            int[] newBallSpeed = {myObject.getSpeed()[0], myObject.getSpeed()[1] * -1};
            myObject.setSpeed(newBallSpeed);
        }
    }

    /**
     * Checks if the ball will leave the borders if the window and changes the direction accordingly.
     *
     * @param width   Width of the view.
     * @param paddleY Y position of the paddle.
     *
     * @return True if the game still can run. False if the ball hits the lower border.
     */
    public boolean handleBorderCollision(int width, int paddleY) {
        boolean switchX = myObject.nextX() <= 0 || myObject.nextX() + myObject.getWith() >= width;
        boolean switchY = myObject.nextY() <= 0;
        boolean endGame = myObject.nextY() + myObject.getHeight() >= paddleY;

        if (switchX) {
            int[] newBallSpeed = {myObject.getSpeed()[0] * -1, myObject.getSpeed()[1]};
            myObject.setSpeed(newBallSpeed);
            return true;
        } else if (switchY) {
            int[] newBallSpeed = {myObject.getSpeed()[0], myObject.getSpeed()[1] * -1};
            myObject.setSpeed(newBallSpeed);
            return true;
        } else if (endGame) {
            int[] newBallSpeed = {0, 0};
            myObject.setSpeed(newBallSpeed);
            return false;
        } else {
            return true;
        }
    }

    public BBlock[] handleBlockCollision(BlockList blocks) {
        // Contains block that might have hit on a possible location.
        // Format: XUpperLeft, XUpperRight, XLowerLeft, XLowerRight, YUpperLeft, YUpperRight, YLowerLeft, YLowerRight
        BBlock[] blockList = new BBlock[8];

        blockList[0] = blocks.getBlockAtXY(myObject.nextX() + 1, myObject.nextY());
        blockList[1] = blocks.getBlockAtXY(myObject.nextX() + myObject.getWith() - 1, myObject.nextY());
        blockList[2] = blocks.getBlockAtXY(myObject.nextX() + 1, myObject.nextY() + myObject.getHeight());
        blockList[3] = blocks.getBlockAtXY(myObject.nextX() + myObject.getWith() - 1, myObject.nextY() + myObject.getHeight());

        blockList[4] = blocks.getBlockAtXY(myObject.nextX(), myObject.nextY() + 1);
        blockList[5] = blocks.getBlockAtXY(myObject.nextX() + myObject.getWith(), myObject.nextY() + 1);
        blockList[6] = blocks.getBlockAtXY(myObject.nextX(), myObject.nextY() + myObject.getHeight() - 1);
        blockList[7] = blocks.getBlockAtXY(myObject.nextX() + myObject.getWith(), myObject.nextY() + myObject.getHeight() - 1);

        // Remove possible duplicates
        if (blockList[0] == blockList[1]) blockList[1] = null;
        if (blockList[2] == blockList[3]) blockList[3] = null;
        if (blockList[4] == blockList[6]) blockList[6] = null;
        if (blockList[5] == blockList[7]) blockList[7] = null;

        int count = 0;
        // Change object speed depending on Collisions and count how many blocks are affected.
        for (int i = 0; i < blockList.length; i++) {
            if (blockList[i] != null) {
                count++;
                if (i < 4) {
                    // Collision X => Change Y
                    int[] newBallSpeed = {myObject.getSpeed()[0], myObject.getSpeed()[1] * -1};
                    myObject.setSpeed(newBallSpeed);
                } else {
                    // Collision Y => Change X
                    int[] newBallSpeed = {myObject.getSpeed()[0] * -1, myObject.getSpeed()[1]};
                    myObject.setSpeed(newBallSpeed);
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
