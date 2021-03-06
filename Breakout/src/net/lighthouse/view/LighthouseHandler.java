package net.lighthouse.view;

import java.awt.Color;
import java.io.IOException;

import acm.graphics.GImage;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.Converter;

/**
 * This class is responsible for sending data to the lighthouse.
 * 
 * @author finite
 *
 */
class LighthouseHandler {
	// Object vars because accessing settings each frame would be really bad for
	// performance.
	private String username;
	private String token;

	LighthouseDisplay display;

	/**
	 * Constructs a lighthouse view and determines its username and password.
	 * 
	 */
	LighthouseHandler() {
		this.username = Settings.getSetting("user-name");
		this.token = Settings.getSetting("token");

	}

	/**
	 * Initializes the lighthouse display and tries to connect.
	 */
	void init() {
		if (username.isEmpty())
			throw new IllegalArgumentException("username can't be empty! (Did you configure settings.txt right?)");
		if (token.isEmpty())
			throw new IllegalArgumentException("token can't be empty! (Did you configure settings.txt right?)");

		display = new LighthouseDisplay(username, token);

		connect();
	}

	/**
	 * Updates to display with pixel data.
	 * 
	 * @param pixels
	 *            the 28x14 2d Color array containing the values of each pixel.
	 */
	void update(Color[][] pixels) {
		// converts to lighthouse data standard.
		byte[] data = Converter.dataConverter(pixels);

		// send.
		try {
			display.send(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Updates the display with pixel data
	 * 
	 * @param image
	 *            the 28x14 GImage containing the current frame.
	 */

	void update(GImage image) {
		assert image != null;
		byte[] data = Converter.dataConverter(image);
		try {
			display.send(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Tries to connect to the web display.
	 */
	void connect() {
		System.out.println(
				"LighthouseView: Trying to connect to web with username " + username + ", token " + token + ".");
		// Try connecting to the display
		try {
			display.connect();
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
