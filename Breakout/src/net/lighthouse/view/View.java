package net.lighthouse.view;

import net.lighthouse.model.MainModel;

/**
 * The View Interface defines which methods must be implemented by a MainView
 * (legacy or rewrite).
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
	 * initializes lighthouse connection (if setting says so) and client size.
	 */
	public void init();

}