package net.lighthouse.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import javafx.scene.shape.Rectangle;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;

/**
 * I tried to do it properly. It was boring as h*ck.
 * 
 * @author undif
 *
 */
public class DarkhouseScaler {
	LighthouseHandler handler;
	// indicates wether scaled render outputs should be saved
	private boolean save_Framebuffer = false;
	// the number of the frame to save, starts at 0, iterates so we don't overwrite
	// stuff.
	private static int imgNumber = 0;

	public DarkhouseScaler(String username, String token) {
		handler = new LighthouseHandler(username, token);
	}

	public void init() {
		handler.init();
		if (Settings.getSetting("Save_Framebuffer").equals("true")) {
			save_Framebuffer = true;
		}
	}

	/**
	 * Updates the lighhouse by taking a screenshot of the GCanvas, scalling that
	 * down and sending the data.
	 * 
	 * @param top
	 *            the GCanvas to screenshot.
	 */
	public void update(GCanvas top) {

		BufferedImage captureImage = new BufferedImage(560, 840, BufferedImage.TYPE_4BYTE_ABGR);
		top.paint(captureImage.getGraphics());

		Image downsample = captureImage.getScaledInstance(28, 14, Image.SCALE_SMOOTH);
		GImage gDownsample = new GImage(downsample);

		// if save_Framebuffer == true speichern wir jeden gerenderten Frame als png in
		// den bin ordner.
		if (save_Framebuffer)
			try {
				BufferedImage iDownsample = new BufferedImage(28, 14, BufferedImage.TYPE_4BYTE_ABGR);
				gDownsample.paint(iDownsample.getGraphics());
				ImageIO.write(iDownsample, "png", new File("img" + imgNumber++ + ".png"));
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}

		handler.update(gDownsample);
	}
}
