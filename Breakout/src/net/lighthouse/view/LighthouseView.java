package net.lighthouse.view;

import java.awt.Color;
import java.util.ArrayList;

import net.lighthouse.model.BBall;
import net.lighthouse.model.BPaddle;
import net.lighthouse.model.BlockList;
import net.lighthouse.model.MainModel;

public class LighthouseView {
	LighthouseHandler handler;

	public LighthouseView(String username, String token) {
		handler = new LighthouseHandler(username, token);
	}

	public void init() {
		handler.init();
	}

	public void update(MainModel model) {
		// The components are drawn onto different layers and than combined
		ArrayList<BPixel> ballLayer = ballPixels(model);
		ArrayList<BPixel> blockLayer = blocksPixels(model);
		ArrayList<BPixel> paddleLayer = paddlePixels(model);

		handler.update(combine(blockLayer, ballLayer, paddleLayer));
	}

	private ArrayList<BPixel> paddlePixels(MainModel model) {
		BPaddle paddle = model.getPaddle();
		return null;

	}

	private ArrayList<BPixel> blocksPixels(MainModel model) {
		BlockList blocks = model.getBlocks();
		return null;

	}

	private ArrayList<BPixel> ballPixels(MainModel model) {
		ArrayList<BBall> balls = model.getAllBalls();
		return null;

	}

	private Color[][] combine(ArrayList<BPixel> blockLayer, ArrayList<BPixel> ballLayer,
			ArrayList<BPixel> paddleLayer) {
		// TODO Auto-generated method stub
		return null;
	}

}
