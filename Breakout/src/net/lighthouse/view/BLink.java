package net.lighthouse.view;

import acm.graphics.GObject;
import net.lighthouse.model.BObject;

public class BLink {
	private BObject modelLink;
	private GObject clientLink;
	private GObject darkLink; //

	public BLink(BObject modelLink, GObject clientLink, GObject darkLink) {
		this.modelLink = modelLink;
		this.clientLink = clientLink;
		this.darkLink = darkLink;
	}

	/**
	 * @return the modelLink
	 */
	public BObject getModelLink() {
		return modelLink;
	}

	/**
	 * @param modelLink
	 *            the modelLink to set
	 */
	public void setModelLink(BObject modelLink) {
		this.modelLink = modelLink;
	}

	/**
	 * @return the clientLink
	 */
	public GObject getClientLink() {
		return clientLink;
	}

	/**
	 * @param clientLink
	 *            the clientLink to set
	 */
	public void setClientLink(GObject clientLink) {
		this.clientLink = clientLink;
	}

	/**
	 * @return the darkLink
	 */
	public GObject getDarkLink() {
		return darkLink;
	}

	/**
	 * @param darkLink
	 *            the darkLink to set
	 */
	public void setDarkLink(GObject darkLink) {
		this.darkLink = darkLink;
	}
}