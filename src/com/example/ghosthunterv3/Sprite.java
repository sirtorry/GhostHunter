package com.example.ghosthunterv3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.ghosthunterv3.gameWorld.OurView;

public class Sprite {

	int a;

	int b;

	int aSpeed, bSpeed;

	int height, width;

	Bitmap g;

	OurView ov;

	int currentFrame = 0;

	int direction = 0;

	Rect targetRect;
	
	Rect spriteRect;

	Context context;

	public Sprite(OurView ourView, Bitmap ghost, Rect target) {

		// a=(int) (Math.random()*ov.getWidth());

		// b=(int)(Math.random()*ov.getHeight());

		a = (int) (Math.random() * 1000);

		b = (int) (Math.random() * 800);

		aSpeed = 3;

		bSpeed = 0;

		g = ghost;

		ov = ourView;

		height = g.getHeight() / 4;

		width = g.getWidth() / 3;

		targetRect = target;

	}

	public  int getSpriteXLocation() {
		return a;
	}

	public  int getSpriteYLocation() {
		return b;
	}

	public void repelGhost(float followX, float followY) {
		if (a < followX && b < followY){
			followX = 0;
			followY = 0;
		}
		
		if (a > followX && b < followY){
			followX = 1900;
			followY = 0;
		}
		
		if (a < followX && b > followY){
			followX = 0;
			followY = 1000;
		}
		
		if (a > followX && b > followY){
			followX = 1900;
			followY = 1000;
		}
	}

	public void drawGhost(Canvas canvas, float followX, float followY) {

		update(followX, followY);

		int srcB = direction * height;

		int srcA = currentFrame * width;

		Rect src = new Rect(srcA, srcB, srcA + width, srcB + height);

		Rect dst = new Rect(a, b, a + width, b + height);
		
		spriteRect = new Rect(a, b, a + 32, b + 32);

		canvas.drawBitmap(g, src, dst, null);

	}

	private void update(float followX, float followY) {
		
		if (a < followX) {
			a += 5;
			direction = 2;
		}

		if (a > followX) {
			a -= 5;
			direction = 1;
		}

		if (b < followY) {
			b += 5;
			direction = 0;
		}

		if (b > followY) {
			b -= 5;
			direction = 3;
		}
		
		try {

			Thread.sleep(50);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		currentFrame = ++currentFrame % 3;

	}
	
	public Rect ghostRectangle(){
		return spriteRect;
		
	}
	
	public boolean isCollision(Rect targetRect){
		if (targetRect.intersects(a,b, a + 32, b + 32)){
			return true;
		}
		else
		return false;
	}
}