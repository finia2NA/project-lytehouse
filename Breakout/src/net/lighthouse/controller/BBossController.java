package net.lighthouse.controller;

import acm.util.RandomGenerator;
import net.lighthouse.model.BBoss;
import net.lighthouse.model.BLaser;

import java.awt.*;

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

        // Changes the color of the boss for one frame if damage was dealt.
        if (boss.getHealth() != boss.getOldHealth()) {
            boss.setColor(Color.WHITE);
            boss.evenOldHealth();

            RandomGenerator rnd = RandomGenerator.getInstance();
            int[] speed = {rnd.nextInt(-4, 4), rnd.nextInt(4, 6)};

            // Spawns a new laser at the bottom center of the boss
            laser = new BLaser(boss.getX() + boss.getWith() / 2, boss.getY() + boss.getHeight(), speed);
        } else {
            boss.setColor(Color.GREEN);
        }

        // Indicates that the boss is nearly dead.
        if (boss.getHealth() == 1) {
            boss.setColor(Color.RED);
        }

        return laser;
    }
}
