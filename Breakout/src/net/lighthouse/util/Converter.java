package net.lighthouse.util;

import java.awt.Color;

public final class Converter {
	private Converter() {

	}

	/**
	 * Converts a xy Color array to the byte array format the lighthouse API
	 * requires.
	 * 
	 * @param colorFrame
	 *            the xy Color array that represents the current frame.
	 * @return a byte array representing the input in lighthouse-format.
	 */
	public static byte[] dataConverter(Color[][] colorFrame) {
		byte[] data = new byte[1176];

		for (int y = 0; y < 14; y++) {
			for (int x = 0; x < 28; x++) {
				// The position of the first subpixel of the current pixel in the data array.
				// For reason for this see Farbmapping.xlsx .
				int pos = 84 * y + 3 * x;

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
}
