package net.lighthouse.view;

import net.lighthouse.model.MainModel;

/**
 * I tried to do it properly. It was boring as heck.
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

	public void update(MainModel model) {
		// TODO Auto-generated method stub
		
	}
}
