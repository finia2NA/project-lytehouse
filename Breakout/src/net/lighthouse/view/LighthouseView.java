package net.lighthouse.view;

//JAVA
import java.awt.Color;
import java.util.ArrayList;
//OUR STUFF
import net.lighthouse.model.BBall;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.BPaddle;
import net.lighthouse.model.BlockList;
import net.lighthouse.model.MainModel;
import net.lighthouse.util.Converter;

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
		ArrayList<BPixel> ballLayer = ballPixels(model.getAllBalls());
		ArrayList<BPixel> blockLayer = blocksPixels(model.getBlocks());
		ArrayList<BPixel> paddleLayer = paddlePixels(model.getPaddle());

		handler.update(combine(blockLayer, ballLayer, paddleLayer));
	}

	/**
	 * baut eine bpixel version des paddles.
	 * 
	 * @param paddle
	 *            the paddle
	 * @return eine ArrayList von BPixeln die das paddle repraesentieren.
	 */
	private ArrayList<BPixel> paddlePixels(BPaddle paddle) {
		ArrayList<BPixel> re = new ArrayList<BPixel>();

		int posX = paddle.getX();
		int posY = paddle.getY();

		// Pixel haben eine opacity von ihrem anteil in dem lighthousepixel. für den
		// ganz
		double rightSideOpacity = (posX % 20) / 19.00;
		double leftSideOpacity = 1 - rightSideOpacity;
		int[] lh_pos = Converter.toLighthousePixel(posX, posY);

		// rightmost pixel has opacity
		re.add(new BPixel(lh_pos[0], lh_pos[1], paddle.getColor(), leftSideOpacity));

		// center pixels have opacity = 1
		for (int i = 1; i < 8; i++) {
			re.add(new BPixel(lh_pos[0] + i, lh_pos[1], paddle.getColor(), 1));
		}

		// leftmost pixel has opacity
		re.add(new BPixel(lh_pos[0] + 8, lh_pos[1], paddle.getColor(), rightSideOpacity));

		return re;
	}

	/**
	 * baut eine BPixel version der bloecke. NOTE: damit das richtig funktioniert
	 * muessen momentan die bloecke 1:1 mit lighthouse-pixeln korespondieren.
	 * 
	 * @param blocks
	 * @return
	 */
	private ArrayList<BPixel> blocksPixels(BlockList blocks) {
		ArrayList<BPixel> re = new ArrayList<BPixel>();

		for (BBlock block : blocks) {
			// gets the position of the leftmost lighthouse pixel of the block.
			int[] pos = Converter.toLighthousePixel(block.getX(), block.getY());
			// a block is 4 lx wide.
			for (int i = 0; i < 4; i++) {
				BPixel atm = new BPixel(pos[0] + i, pos[1], block.getColor(), 1);
				re.add(atm);
			}
		}
		return re;

	}

	/**
	 * 
	 * @param balls
	 * @return
	 */
	private ArrayList<BPixel> ballPixels(ArrayList<BBall> balls) {
		ArrayList<BPixel> re = new ArrayList<BPixel>();
		for (BBall ball : balls) {
			int[] lh_pos = Converter.toLighthousePixel(ball.getX(), ball.getY());
		}
		return re;
	}

	/**
	 * combines the Layers to one frame.
	 * 
	 * @param blockLayer
	 * @param ballLayer
	 * @param paddleLayer
	 * @return
	 */
	private Color[][] combine(ArrayList<BPixel> blockLayer, ArrayList<BPixel> ballLayer,
			ArrayList<BPixel> paddleLayer) {
		// TODO Auto-generated method stub
		return null;
	}

}
