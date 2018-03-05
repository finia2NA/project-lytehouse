package net.lighthouse.view;

import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import acm.graphics.GImage;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import net.lighthouse.util.Converter;

/**
 * this class is responsible for sending data to the lighthouse.
 * 
 * @author finite
 *
 */
public class LighthouseHandler {

	// Bleeding Edge security going on in this class.
	// #TheyCan'tHackYourEncryptionWhenYouDon'tHaveAny
	private String username;
	private String token;

	// Brightness scales from 0 to 1. Right now it's just stuck on 1, i'll do some
	// stuff with it later.
	private double brightness;

	LighthouseDisplay display;

	/**
	 * constructs a lighthouse view with brightness = 1 and tries to connect to the
	 * web display.
	 * 
	 * @param username
	 * @param token
	 */
	public LighthouseHandler(String username, String token) {
		this.username = username;
		this.token = token;
		this.brightness = 1;

	}

	/**
	 * initializes the lighthouse display and tries to connect.
	 */
	public void init() {
		assert !username.isEmpty() : "assert on LighthouseView: username is empty.";
		assert !token.isEmpty() : "assert on LighthouseView: token is empty.";

		display = new LighthouseDisplay(username, token);

		connect();
	}

	/**
	 * updates to display with pixel data.
	 * 
	 * @param pixels
	 *            the 28x14 2d Color array containing the values of each pixel.
	 */
	public void update(Color[][] pixels) {
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
	 * updates the display with pixel data
	 * 
	 * @param pixelArray
	 *            the 28x14 2d int array containing the values of each pixel.
	 */

	public void update(GImage image) {
		byte[] data = Converter.dataConverter(image);
		try {
			display.send(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * tries to connect to the web display.
	 */
	private void connect() {
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
