package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.io.IOException;

/**
 * Tests the connection.
 * 
 * @author undif
 *
 */
public class ConnectionTest {

	/**
	 * yo
	 * 
	 * @param args
	 *            index 0 -> username, index 1 -> token
	 */
	public static void main(String[] args) {
		String username = args[0];
		String token = args[1];

		System.out.println(username);
		System.out.println(token);

		LighthouseDisplay display = new LighthouseDisplay(username, token);

		// Try connecting to the display
		try {
			display.connect();
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}

		// Send data to the display
		try {
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.
			Color[][] frame = new Color[28][14];

			for (int y = 0; y < 14; y++) {
				for (int x = 0; x < 28; x++) {
					frame[x][y] = Color.green;
				}
			}

			byte[] data = LighthouseDisplay.dataConverter(frame);

			display.send(data);

		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
