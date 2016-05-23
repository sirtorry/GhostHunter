package com.example.ghosthunterv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.ghosthunterv3.gameWorld.OurView;

public class Bomb {

	int x,y;
	
	OurView ov;
	
	Context context;
	
	Rect bombRect;
	
	Bitmap b;
	
	public Bomb(OurView ourView, Bitmap bomb) {
		
		x = (int) (Math.random() * 1800);

		y = (int) (Math.random() * 1000);
	
		bombRect = new Rect(x, y, x + 32, y + 32);
		
		b = bomb;
		
	}
	
	public int getBombX(){
		return x;
	}
	
	public int getBombY(){
		return y;
	}
	
	public void drawBomb(Canvas canvas){
		canvas.drawBitmap(b, x, y, null);
		bombRect = new Rect(x,y, x + 32, y + 32);
	}
	
	public Rect getBombRect(){
		return bombRect;
	}
}

