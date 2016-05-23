package com.example.ghosthunterv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class myMainScreen extends Activity {

	MediaPlayer menuMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		menuMusic = MediaPlayer.create(myMainScreen.this, R.raw.menumusic);
		menuMusic.start();
		menuMusic.setLooping(true);	
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		menuMusic.pause();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		menuMusic.start();
	}
	
	public void playButton(View v){
		menuMusic.stop();
		Intent playIntent = new Intent(myMainScreen.this, gameWorld.class);
		myMainScreen.this.startActivity(playIntent);
		myMainScreen.this.finish();
		GameState.setNewGame(true);
	}
	
	public void loadButton(View v){
		menuMusic.stop();
		Intent playIntent = new Intent(myMainScreen.this,gameWorld.class);
		myMainScreen.this.startActivity(playIntent);
		myMainScreen.this.finish();
		GameState.setNewGame(false);
	}
	
	public void aboutButton(View v){
		setContentView(R.layout.about);
	}
	
	public void instructionButton(View v){
		setContentView(R.layout.instru);
	}
	
	public void exitButton(View v){
		finish();
	}
	
	public void backButton(View v){
		setContentView(R.layout.activity_main);	
	}
	
}
