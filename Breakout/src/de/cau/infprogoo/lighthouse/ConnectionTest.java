package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.io.IOException;

import net.lighthouse.util.Converter;

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

		while (true) {
			byte[] data = new byte[1176];
			for (int i = 0; i < data.length;) {
				data[i++] = (byte) 0;
				data[i++] = (byte) 0;
				data[i++] = (byte) 255;
			}
			try {
				display.send(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(display.isConnected());
		}
	}
}
