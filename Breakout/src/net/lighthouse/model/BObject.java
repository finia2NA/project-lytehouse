package net.lighthouse.model;

import java.awt.Color;

/**
 * BObject provides the basic for every game object that can exits.
 * Objects of every class that extends BObject can be rendered
 * in the view.
 *
 * @author finite, Christoph Fricke
 */
public class BObject {
    // Instance variables
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    /**
     * Constructs a new BObject with a position and size and a color.
     *
     * @param x      X position of upper left corner
     * @param y      y position of upper left corner
     * @param width  Width of the object
     * @param height Height of the Object
     * @param color  Color of the new BObject
     *
     * @throws IllegalArgumentException if {@code color} is {@code null} or
     *                                  the object has a negative size.
     */
    public BObject(int x, int y, int width, int height, Color color) {
        if (color == null || width < 0 || height < 0) {
            throw new IllegalArgumentException("Color can not be null");
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Returns the color
     *
     * @return color of object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set a new color
     *
     * @param color the color to set
     *
     * @throws IllegalArgumentException if {@code color} is {@code null}
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can not be null");
        }

        this.color = color;
    }

    /**
     * Returns x position of the object.
     *
     * @return x position of the object
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y position of the object.
     *
     * @return y position of the object
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the position bundles as an array where
     * x is at index 0 and y at index 1.
     *
     * @return Array containing x and y position
     */
    public int[] getXY() {
        int[] xy = {x, y};
        return xy;
    }

    /**
     * Returns the with of the object.
     *
     * @return Width of object
     */
    public int getWith() {
        return this.width;
    }

    /**
     * Returns the height of the object.
     *
     * @return Height of object
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets x position.
     *
     * @param x new x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets y position.
     *
     * @param y new x position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets a new position.
     *
     * @param x x coordinate of the new position
     * @param y y coordinate of the new position
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
