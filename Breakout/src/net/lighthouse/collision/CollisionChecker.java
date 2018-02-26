package net.lighthouse.collision;

import net.lighthouse.model.BBall;
import net.lighthouse.model.BObject;
import net.lighthouse.model.BPaddle;
import net.lighthouse.model.BlockList;
import net.lighthouse.model.MainModel;

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
     * @param width  Width of the view.
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

//    private BObject[] check(MainModel model) {
//        BlockList blocks = model.getBlocks();
//    }
}
