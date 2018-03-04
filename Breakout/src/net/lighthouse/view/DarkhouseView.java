package net.lighthouse.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;
import javafx.scene.shape.Rectangle;
import net.lighthouse.model.MainModel;
import net.lighthouse.view.LighthouseHandler;

/**
 * Updates the lighhouse by taking a screenshot of the Graphicsprogram, scaling
 * that down and sending the data. It can save every frame produces as a png for
 * debug purposes.
 * 
 * @author finite
 *
 */
public class DarkhouseView {
	LighthouseHandler handler;
	// indicates wether scaled render outputs should be saved
	// TODO: maybe this should be a setting?
	private boolean saveAllTheStuff = false;
	// the number of the frame to save, starts at 0, iterates so we don't overwrite
	// stuff.
	private static int imgNumber = 0;

	public DarkhouseView() {
		handler = new LighthouseHandler();
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
		// System.out.println("Darkhouse update called");
		BufferedImage captureImage = new BufferedImage(560, 840, BufferedImage.TYPE_4BYTE_ABGR);
		top.getGCanvas().paint(captureImage.getGraphics());
		// the last int is a flag for what algorithm to use. We want bicubic. I have no
		// idea what stands for what, so we'll have to experiment.
		Image downsample = captureImage.getScaledInstance(28, 14, Image.SCALE_SMOOTH);
		GImage gDownsample = new GImage(downsample);

		// start of dbug stuff
		if (saveAllTheStuff)
			try {
				BufferedImage iDownsample = new BufferedImage(28, 14, BufferedImage.TYPE_4BYTE_ABGR);
				gDownsample.paint(iDownsample.getGraphics());
				ImageIO.write(iDownsample, "png", new File("img" + imgNumber++ + ".png"));
			} catch (IOException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
		// end of dbug stuff

		handler.update(gDownsample);
	}
}