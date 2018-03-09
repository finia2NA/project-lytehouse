package net.lighthouse.view;

import java.awt.Color;

import acm.graphics.GImage;

/**
 * Provides Converters between different View Formats.
 * 
 * @author finite
 *
 */
final class Converter {
	private Converter() {

	}

	/**
	 * when given a lighthouse-pixel, this method returns the corresponding client
	 * pixel (top left corner).
	 * 
	 * @param x
	 *            of the lighthouse pixel.
	 * @param y
	 *            of the lighthouse pixel.
	 * @return the corresponding client pixel (top left corner).
	 */
	static int[] toClientPixel(int x, int y) {
		int[] re = new int[2];
		re[0] = x * 20;
		re[1] = y * 60;
		return re;
	}

	/**
	 * when given a client-pixel, this method returns the corresponding lighthouse
	 * pixel
	 * 
	 * @param x
	 *            of the client pixel.
	 * @param y
	 *            of the client pixel.
	 * @return the corresponding lighthouse pixel.
	 */
	static int[] toLighthousePixel(int x, int y) {
		int[] re = new int[2];
		re[0] = x / 20;
		re[1] = y / 60;
		return re;
	}

	/**
	 * Converts a xy Color array to the byte array format the lighthouse API
	 * requires.
	 * 
	 * @param colorFrame
	 *            the xy Color array that represents the current frame.
	 * @return a byte array representing the input in lighthouse-format.
	 */
	static byte[] dataConverter(Color[][] colorFrame) {
		byte[] data = new byte[1176];
		for (int y = 0; y < 14; y++) {
			for (int x = 0; x < 28; x++) {
				// The position of the first subpixel of the current pixel in the data array.
				// For reason for this see Farbmapping.xlsx .
				int pos = (28 * y + x) * 3;

				// Gets the subpixels of the current pixel. (byte) casts ints from 0 to 255 to
				// unsigned bytes. which I think is the format we need.
				byte red = (byte) colorFrame[x][y].getRed();
				byte green = (byte) colorFrame[x][y].getGreen();
				byte blue = (byte) colorFrame[x][y].getBlue();

				data[pos] = red;
				data[pos + 1] = green;
				data[pos + 2] = blue;
			}
		}
		return data;
	}

	/**
	 * Converts a GImage to the byte array format the lighthosue API requires.
	 * 
	 * @param image
	 *            the GImage.
	 * @return a byte array representing the input in lighthouse-format.
	 */
	static byte[] dataConverter(GImage image) {
		byte[] data = new byte[1176];
		int[][] pixelArray = image.getPixelArray();
		for (int y = 0; y < 14; y++) {
			for (int x = 0; x < 28; x++) {
				int pixel = pixelArray[y][x];
				int pos = (28 * y + x) * 3;

				data[pos] = (byte) GImage.getRed(pixel);
				data[pos + 1] = (byte) GImage.getGreen(pixel);
				data[pos + 2] = (byte) GImage.getBlue(pixel);
			}
		}
		return data;
	}
}
