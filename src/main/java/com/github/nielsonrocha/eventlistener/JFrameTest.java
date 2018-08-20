package com.github.nielsonrocha.eventlistener;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class JFrameTest implements Runnable {
	
	
	public void run() {
		JFrame frame = new JFrame("JFrame exemplo");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 200));
		frame.pack();
		frame.setVisible(true);
	}
}
