package com.example.ghosthunterv3;

import com.example.ghosthunterv3.gameWorld.OurView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Coin {

	int s, h;

	int sSpeed, hSpeed;

	int height, width;

	Bitmap coin;

	OurView ov;

	int currentFrame = 0;

	int direction = 0;
	
	Rect coinRect;

	public Coin(OurView ourView, Bitmap money, int spawnX, int spawnY) {

		s = spawnX;

		h = spawnY;

		//sSpeed = 0;

		//hSpeed = 3;

		coin = money;

		ov = ourView;

		//height = money.getHeight() / 8;

		//width = money.getWidth() / 8;
		
		coinRect = new Rect(s, h, s + 32, h + 32);

	}

	public void drawCoin(Canvas canvas) {

//		update();
//
//		int srcA = currentFrame * height;
//
//		Rect src = new Rect(0, srcA, width, srcA + height);
//
//		Rect dst = new Rect(s, h, s + width, h + height);
		
		canvas.drawBitmap(coin, s, h, null);
		
		coinRect = new Rect(s, h, s + 32, h + 32);

		//canvas.drawBitmap(coin, src, dst, null);

	}
	
	public Rect getCoinRect(){
		return coinRect;
	}

//	private void update() {
//
//		try {
//
//			Thread.sleep(30);
//
//		} catch (InterruptedException e) {
//
//			e.printStackTrace();
//
//		}
//
//		currentFrame = ++currentFrame % 8;
//
//		h += hSpeed;
//	}
}