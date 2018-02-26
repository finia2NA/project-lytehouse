package net.lighthouse.view;

import java.awt.Image;
import java.awt.image.BufferedImage;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import javafx.scene.shape.Rectangle;
import net.lighthouse.model.MainModel;

/**
 * I tried to do it properly. It was boring as h*ck.
 * 
 * @author undif
 *
 */
public class DarkhouseView {
	LighthouseHandler handler;

	public DarkhouseView(String username, String token) {
		handler = new LighthouseHandler(username, token);
	}

	public void init() {
		handler.init();
	}

	/**
	 * updates the lighhouse by taking a screenshot of the Graphicsprogram, scalling
	 * that down and sending the data.
	 * 
	 * @param top
	 *            the GraphicsProgram.
	 */
	public void update(GraphicsProgram top) {
		BufferedImage captureImage = new BufferedImage(560, 840, BufferedImage.TYPE_4BYTE_ABGR);
		top.getGCanvas().paint(captureImage.getGraphics());
		// the last int is a flag for what algorithm to use. We want bicubic. I have no
		// idea what stands for what, so we'll have to experiment.
		GImage downsample = new GImage(captureImage.getScaledInstance(28, 14, Image.SCALE_AREA_AVERAGING));
		handler.update(downsample);
	}
}
