package net.lighthouse.view.legacy;

import acm.program.GraphicsProgram;
import net.lighthouse.controller.MainController;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.View;
import net.lighthouse.view.DarkhouseScaler;

/**
 * @deprecated updates the client and lighthouse view in an extremely simple
 *             way. Has been replaced by the rewrite. To use the old View, set
 *             use_new_Viewport to false in the Settings.
 * 
 * @author finite
 *
 */
public class legacyMainView implements View {

	private DarkhouseScaler lighthouse;
	private legacyClientView client;
	private GraphicsProgram top;

	/**
	 * constructs a new legacyMainView.
	 * 
	 * @param top
	 *            the GraphicsProgram to render the ClientView to.
	 */
	public legacyMainView(GraphicsProgram top) {
		this.top = top;
	}

	/**
	 * Initializes the views.
	 */
	public void init() {
		System.out.println("Viewport info : Using legacy Viewport");
		if (Settings.getSetting("web-view").equals("true")) {
			lighthouse = new DarkhouseScaler();
			lighthouse.init();

		}
		client = new legacyClientView(top);
		client.init();
	}

	/**
	 * Refreshes the lighthouseview and redraws the clientView.
	 * 
	 * @param model
	 */
	public void update(MainModel model) {
		// TODO: lighthouse refresh
		if (Settings.getSetting("web-view").equals("true")) {
			lighthouse.update(top.getGCanvas());
		}
		client.reDraw(model);
	}

}