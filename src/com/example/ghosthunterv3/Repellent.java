package com.example.ghosthunterv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.ghosthunterv3.gameWorld.OurView;

public class Repellent {
	int x,y;
	
	OurView ov;
	
	Context context;
	
	Rect repRect;
	
	Bitmap rep;
	
	public Repellent(OurView ourView, Bitmap repellent){
		rep = repellent;
		
		x = (int) (Math.random() * 1700);

		y = (int) (Math.random() * 1000);
		
		repRect = new Rect (x,y, x + 32, y + 32);
		
	}
	
	public int getRepX(){
		return x;
	}
	
	public int getRepY(){
		return y;
	}
	
	public void drawRep(Canvas canvas){
		canvas.drawBitmap(rep, x, y, null);
		repRect = new Rect(x,y, x + 32, y + 32);
	}
	
	public Rect getRepelRect(){
		return repRect;
	}
	
	
	
}
