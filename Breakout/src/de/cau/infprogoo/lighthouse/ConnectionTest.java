package de.cau.infprogoo.lighthouse;

import java.io.IOException;
/**
 * Tests the connection.
 * @author undif
 *
 */
public class ConnectionTest {
	
	/**
	 * yo
	 * @param args index 0 -> username, index 1 -> token
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
			byte[] data = new byte[14 * 28 * 3];

			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 28; j++) {
					for (int k = 0; k < 3; k++) {
						data[i * j * k] = 50;
					}
				}
			}

			display.send(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
