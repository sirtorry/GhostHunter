package com.example.ghosthunterv3;

import android.view.SurfaceHolder;

public class MainThread extends Thread {

	// flag to hold game state
	private boolean running;
	
	private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	
	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel){
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		while (running) {
			//update game state
			//render state to the screen
		}
	}
}
