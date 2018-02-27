package net.lighthouse.model;

import java.awt.Color;

import net.lighthouse.view.BPixel;

public class BPaddle extends BObject implements renderable {
    private Color color;
    private double opacity;

    /**
     * Paddle Constructor.
     *
     * @param x       of the paddle.
     * @param y       of the paddle.
     * @param color   of the paddle.
     * @param opacity of the paddle.
     */
    BPaddle(int x, int y, Color color, double opacity) {
        super(x, y, 160, 60);
        this.color = color;
        this.opacity = opacity;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setOpacity(double opacity) {
        assert opacity <= 1 && opacity >= 0;
        this.opacity = opacity;
    }

    @Override
    public double getOpacity() {
        return opacity;
    }

    /**
     * Moves the paddle to a new supplied X position.
     *
     * @param nextX New X position of the paddle.
     */
    public void move(int nextX) {
        this.setX(nextX);
    }
}
