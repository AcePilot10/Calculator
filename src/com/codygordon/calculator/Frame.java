package com.codygordon.calculator;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Frame() {
		super();
		setTitle(Settings.TITLE);
		setSize(Settings.WIDTH, Settings.HEIGHT);		
		getContentPane().setBackground(Settings.BACKGROUND_COLOR);
		setLayout(new GridLayout(1, 1));
	}
}