package net.lighthouse.view;

import acm.program.GraphicsProgram;
import net.lighthouse.controller.MainController;
import net.lighthouse.model.MainModel;

/**
 * the main View. Controlls what goes on in the client and lighthouse views. The
 * windows for the client view gets provided by the Graphicsprogram that
 * constructs this view.
 * 
 * @author undif
 *
 */
public class MainView {
	private LighthouseView lighthouse;
	private ClientView client;
	private GraphicsProgram top;

	public MainView(GraphicsProgram top) {
		this.top = top;
	}

	// TODO: username and token from settings
	public void init() {
		lighthouse = new LighthouseView("username", "token");
		client = new ClientView(top);
		lighthouse.init();
		client.init();
	}

	/**
	 * refreshes the lighthouseview and redraws the clientView. if this takes too
	 * long, we'll have to come up with a better was to handle client refreshs than
	 * to just redraw the entire frame.
	 * 
	 * @param model
	 */
	public void refresh(MainModel model) {
		//TODO: lighthouse refresh
		client.reDraw(model);
	}

}
