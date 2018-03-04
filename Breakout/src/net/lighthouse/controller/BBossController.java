package net.lighthouse.controller;

import net.lighthouse.model.BBoss;

/**
 * @author Christoph Fricke
 */
public class BBossController {

    private BBoss boss;

    public BBossController(BBoss boss) {
        this.boss = boss;
    }

    public void playBossMove() {
        if(boss.getHealth() == 0) {

        }
    }
}
