import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Configures the settings file from user text input.
 * 
 * @author finite
 *
 */
public class Configurator {
	// much like a poodle being rubbed against a balloon, there's alot of static
	// here.
	static Scanner scanner = new Scanner(System.in);
	static boolean webView;
	static String userName = "";
	static String token = "";

	/**
	 * Asks questions regarding lighthouse/Breakout settings and saves the answers
	 * to a settings.txt .
	 */
	public static void main(String[] args) {
		// Intro
		println("Welcome to the Breakout Configurator!");

		// Questions
		askWebView();
		if (webView) {
			askWebSettings();
		}
		saveSettings();
		println("Settings saved! You can now play Breakout :)");
	}

	/**
	 * asks wether Web View is to be used.
	 */
	private static void askWebView() {
		while (true) {
			print("Do you want to display the content in the Web-View? (Answer y/n). ");
			String s = scanner.next();
			if (s.equals("y")) {
				webView = true;
				return;
			} else if (s.equals("n")) {
				webView = false;
				return;
			}
		}
	}

	/**
	 * asks the user for a username and token.
	 */
	private static void askWebSettings() {
		// username
		while (true) {
			print("what username do you want to use for the Web-View? ");
			String s = scanner.next();
			if (!s.isEmpty()) {
				userName = s;
				break;
			}
		}
		// token
		while (true) {
			print("what token do you want to use for the Web-View? ");
			String s = scanner.next();
			if (!s.isEmpty()) {
				token = s;
				break;
			}
		}
	}

	/**
	 * saves the settings to the txt.
	 */
	private static void saveSettings() {
		String[] settings = new String[3];

		// web view to settings
		settings[0] = "web-view = " + webView;

		// username to settings
		settings[1] = "user-name = " + userName;

		// token to settings
		settings[2] = "token = " + token;

		// store in txt
		try {
			PrintWriter writer = new PrintWriter("settings.txt");
			for (String setting : settings) {
				writer.println(setting);
			}
			writer.close();
		} catch (IOException e) {
			println("could not write settings..");
			System.exit(1);
		}
	}

	/**
	 * println without writing System.out
	 */
	private static void println(String s) {
		System.out.println(s);
	}

	/**
	 * print without writing System.out
	 */
	private static void print(String s) {
		System.out.print(s);
	}
}
