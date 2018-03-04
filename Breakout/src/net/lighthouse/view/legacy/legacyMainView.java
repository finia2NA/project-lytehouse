package net.lighthouse.view.legacy;

import acm.program.GraphicsProgram;
import net.lighthouse.controller.MainController;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.View;
import net.lighthouse.view.DarkhouseView;

/**
 * @deprecated updates the client and lighthouse view in an extremely ineficient
 *             way. Has been replaced by the rewrite.
 * 
 * @author finite
 *
 */
public class legacyMainView implements View {

	private DarkhouseView lighthouse;
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
		if (Settings.getSetting("web-view").equals("true")) {
			lighthouse = new DarkhouseView();
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
			lighthouse.update(top);
		}
		client.reDraw(model);
	}

}