package net.lighthouse.controller;

import net.lighthouse.collision.CollisionChecker;
import net.lighthouse.model.BLaser;
import net.lighthouse.model.MainModel;

import java.util.ArrayList;

/**
 * Controls the lasers.
 *
 * @author Christoph Fricke
 */
public final class BLaserController {
    /**
     * We do not want an object of this control since we always control all lasers at once.
     * Therefore we can use a static method which iterates over all lasers.
     */
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
        ArrayList<BLaser> laserToDelete = new ArrayList<>();

        for (BLaser laser : model.getLasers()) {
            CollisionChecker checker = new CollisionChecker(laser);

            if (!checker.handlePaddleCollision(model.getPaddle())) {
                if (!checker.handleBorderCollision(windowWidth, model.getPaddle().getY())) {
                    // Laser has passed the paddle so it is gone
                    laserToDelete.add(laser);
                }
                laser.move();
            } else {
                laserToDelete.add(laser);
                return true;
            }
        }

        // Remove lasers which are out of bounds
        for (BLaser killMePls : laserToDelete) {
            model.getLasers().remove(killMePls);
        }

        return false;
    }
}
