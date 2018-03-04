package net.lighthouse.view.legacy;

import acm.program.GraphicsProgram;
import net.lighthouse.controller.MainController;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;
import net.lighthouse.view.View;

/**
 * Legacy Main View. Will be replaced by the new MainView shortly.
 * 
 * @author finite
 *
 */
public class legacyMainView implements View {

	private legacyDarkhouseView lighthouse;
	private legacyClientView client;
	private GraphicsProgram top;

	public legacyMainView(GraphicsProgram top) {
		this.top = top;
	}

	public void init() {
		if (Settings.getSetting("web-view").equals("true")) {
			lighthouse = new legacyDarkhouseView(Settings.getSetting("user-name"), Settings.getSetting("token"));
			lighthouse.init();

		}
		client = new legacyClientView(top);
		client.init();
	}

	/**
	 * refreshes the lighthouseview and redraws the clientView. if this takes too
	 * long, we'll have to come up with a better was to handle client refreshs than
	 * to just redraw the entire frame.
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