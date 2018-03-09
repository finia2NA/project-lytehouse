package net.lighthouse.model;

import java.awt.Color;

/**
 * A BBoss os a super Block that is bigger and has more health than a normal block.
 *
 * @author Christoph Fricke
 */
public class BBoss extends BObject {

    /**
     * Health of the boss.
     */
    private int health;

    /**
     * OldHealth. This can be used to determine if the boss toke damage.
     */
    private int oldHealth;

    /**
     * Creates a new boss with a size of 240x180 at position 160, 180.
     *
     * @param health Health of the boss
     * @param color  Color of the boss
     */
    public BBoss(int health, Color color) {
        super(160, 180, 240, 180, color);
        this.health = health;
        this.oldHealth = health;
    }

    /**
     * Reduces the health by amount of damage dealt.
     *
     * @param damage Amount of damage dealt to the boss
     *
     * @throws IllegalArgumentException if {@code damage} is negative
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
     *
     * @throws IllegalArgumentException if {@code healingPoints} are negative
     */
    public void heal(int healingPoints) {
        if (healingPoints < 0) {
            throw new IllegalArgumentException("You can not heal by a negative amount");
        }
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
