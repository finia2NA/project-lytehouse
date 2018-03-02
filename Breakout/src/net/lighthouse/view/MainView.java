package net.lighthouse.view;

import acm.program.GraphicsProgram;
import net.lighthouse.controller.MainController;
import net.lighthouse.model.MainModel;
import net.lighthouse.settings.Settings;

/**
 * the main View. Controlls what goes on in the client and lighthouse views. The
 * windows for the client view gets provided by the Graphicsprogram that
 * constructs this view.
 * 
 * @author undif
 *
 */
public class MainView {

	private DarkhouseScaler darkhouse;
	private GraphicsProgram top;
	private MainModel 

	public MainView(GraphicsProgram top) {
		this.top = top;
	}

}
