package net.lighthouse.controller;

import net.lighthouse.collision.CollisionChecker;
import net.lighthouse.model.BLaser;
import net.lighthouse.model.MainModel;

import java.util.ArrayList;

/**
 * @author Christoph Fricke
 */
public final class BLaserController {
    private BLaserController() {

    }

    /**
     * Updates the position of all BLasers. And checks if a laser hit the paddle.
     *
     * @param model Main model which stores all needed information.
     *
     * @return True if a laser hit the paddle.
     */
    public static boolean updateLasers(MainModel model, int windowWidth) {
        if (model == null) {
           throw new IllegalArgumentException("Model can not be null!");
        }
        for (BLaser laser : model.getLasers()) {
            CollisionChecker checker = new CollisionChecker(laser);

            if (!checker.handlePaddleCollision(model.getPaddle())) {
                if (!checker.handleBorderCollision(windowWidth, model.getPaddle().getY())) {
                    // Laser has passed the paddle so it is gone
                    model.getLasers().remove(laser);
                }
                laser.move();
            } else {
                model.getLasers().remove(laser);
                return true;
            }
        }

        return false;
    }
}
