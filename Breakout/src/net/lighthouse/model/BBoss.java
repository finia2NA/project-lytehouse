package net.lighthouse.model;

import java.awt.*;

/**
 * @author Christoph Fricke
 */
public class BBoss extends BBlock {

    private int health;
    private int oldHealth;

    public BBoss(int health, Color color) {
        super(160, 180, 240, 180, color);
        this.health = health;
        this.oldHealth = health;
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
        oldHealth = health;
        health -= damage;
    }

    /**
     * Heal the boss by a certain amount.
     *
     * @param healingPoints Amount of heal the boss receives.
     */
    public void heal(int healingPoints) {
        oldHealth = health;
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

    /**
     * Returns the old boss health.
     *
     * @return Health of the boss
     */
    public int getOldHealth() {
        return oldHealth;
    }

    /**
     * Updates the oldHealth to allow it to be the same as the current health.
     * This is allows you to remove the white blinking of the boss after he has taken damage.
     */
    public void evenOldHealth() {
        oldHealth = health;
    }
}
