package net.lighthouse.view;

import net.lighthouse.model.MainModel;

/**
 * Wether you're using the legacy view or the new view, you can count on being
 * abled to use these methods!
 * 
 * @author finite
 *
 */
public interface View {
	/**
	 * updates Client and lighthouse (if setting says so) to reflect a model.
	 * 
	 * @param model
	 *            the bas model.
	 */
	public void update(MainModel model);

	/**
	 * initializes lighthouse connection and client size.
	 */
	public void init();

}
