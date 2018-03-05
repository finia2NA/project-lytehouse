package net.lighthouse.model;

import java.awt.*;

/**
 * @author Christoph Fricke
 */
public class BBoss extends BBlock {

    private int health;

    public BBoss(int health, Color color) {
        super(80, 60, 400, 240, color);
        this.health = health;
    }

    /**
     * Reduces the health by amount of damage dealt.
     *
     * @param damage Amount of damage dealt to the boss
     */
    public void reduceHealth(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("You can not deal negative damage!");
        }
        health -= damage;
    }

    /**
     * Heal the boss by a certain amount.
     *
     * @param healingPoints Amount of heal the boss recieves.
     */
    public void heal(int healingPoints) {
        health += healingPoints;
    }

    /**
     * Returns the current boss health.
     *
     * @return Health of the boss
     */
    public int getHealth() {
        return health;
    }
}
