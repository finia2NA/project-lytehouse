package net.lighthouse.view;

import java.awt.Panel;

import acm.program.GraphicsProgram;

public class ClientView {
	GraphicsProgram top;

	public ClientView(GraphicsProgram top) {
		this.top = top;
	}
	
	public void init() {
		top.setSize(560, 840);
	}
}
