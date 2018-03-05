package net.lighthouse.controller;

import acm.util.RandomGenerator;
import net.lighthouse.model.BBoss;
import net.lighthouse.model.BLaser;

/**
 * @author Christoph Fricke
 */
public class BBossController {

    private BBoss boss;

    public BBossController(BBoss boss) {
        this.boss = boss;
    }

    /**
     * Handles action of the BBoss. A boss spawn a new laser when his health is odd.
     *
     * @return A new laser if one is spawned. {@code null} otherwise.
     */
    public BLaser playBossMove() {
        if (boss.getHealth() <= 0) {
            throw new IllegalStateException("Boss should be death by now!");
        }

        BLaser laser = null;

        // We want to spawn a new laser if boss health is not even
        if (boss.getHealth() % 2 != 0) {
            RandomGenerator rnd = RandomGenerator.getInstance();
            int[] speed = {rnd.nextInt(-4, 4), rnd.nextInt(4, 6)};

            // Spawns a new laser at the bottom center of the boss
            laser = new BLaser(boss.getX() + boss.getWith() / 2, boss.getY() + boss.getHeight(), speed);
        }

        return laser;
    }
}
