package net.lighthouse.model;

import java.awt.Color;

/**
 * Stores information about text that can be displayed in the client view.
 *
 * @author Christoph Fricke, finite
 */
public class BText implements Renderable {
    private int x;
    private int y;
    private double opacity;
    private String text;
    private Color color;
    //Indicates wether the lighthouse will render the text.
	private boolean clientOnly = true;;



	/**
     * Creates a new text object which can be rendered on the screen.
     * The default color is white and opacity 1.
     *
     * @param x    X coordinate of the lower left corner.
     * @param y    Y coordinate of the lower left corner.
     * @param text Text to display.
     *
     * @throws IllegalArgumentException if the position is less than 0
     *                                  or the text is null or empty.
     */
    public BText(int x, int y, String text) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Text can not be positioned on negative coordinates!" +
                " Was: x: " + x + " y: " + y);
        } else if (text == null || text.equals("")) {
            throw new IllegalArgumentException("Text can not be null or empty!");
        }

        this.x = x;
        this.y = y;
        this.text = text;
        this.color = Color.WHITE;
        this.opacity = 1.00d;
    }

    /**
     * Set the text color to a new color.
     *
     * @param color New color to apply.
     *
     * @throws IllegalArgumentException if {@code color} is {@code null}.
     */
    @Override
    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("Color can not be null!");
        }
        this.color = color;
    }

    /**
     * Get the current color of the text.
     *
     * @return The current color.
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Sets the opacity of the text. Opacity must be between 0 and 1 both included.
     *
     * @param opacity New opacity of the text.
     *
     * @throws IllegalArgumentException if the opacity is less than 0 or greater than 1.
     */
    @Override
    public void setOpacity(double opacity) {
        if (opacity < 0.00d || opacity > 1.00d) {
            throw new IllegalArgumentException("Opacity must be >= 0 && <= 1! Was: " + opacity);
        }
        this.opacity = opacity;
    }

    /**
     * Get the current opacity of the text.
     *
     * @return The current opacity.
     */
    @Override
    public double getOpacity() {
        return opacity;
    }

    /**
     * Get the x coordinate of the text.
     *
     * @return The x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the x coordinate of the text.
     *
     * @return The x coordinate.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the x coordinate of the text.
     *
     * @param x New x coordinate
     */
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Text can not be positioned on negative coordinates!" +
                " Was: y: " + x);
        }


        this.x = x;
    }

    /**
     * Sets the y coordinate of the text.
     *
     * @param y New y coordinate
     */
    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException("Text can not be positioned on negative coordinates!" +
                " Was: y: " + y);
        }

        this.y = y;
    }
    
    /**
	 * @return the clientOnly
	 */
	public boolean isClientOnly() {
		return clientOnly;
	}

	/**
	 * @param clientOnly the clientOnly to set
	 */
	public void setClientOnly(boolean clientOnly) {
		this.clientOnly = clientOnly;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
