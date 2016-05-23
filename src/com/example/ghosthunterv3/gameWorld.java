package com.example.ghosthunterv3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class gameWorld extends Activity implements OnTouchListener {

	MediaPlayer gameMusic;

	Bitmap ghost;

	OurView v;

	Bitmap blob;

	float x;

	float y;

	float a, b;

	float aSpeed, bSpeed;
	
	int ghostsKilled = 0;

	Sprite sprite;

	Rect mainCharacterBox;
	
	int ghostKilled;
	int health;
	int money;
	
	private SharedPreferences mPrefs;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		gameMusic = MediaPlayer.create(gameWorld.this, R.raw.gamemusic);

		gameMusic.start();

		gameMusic.setLooping(true);

		ghost = BitmapFactory.decodeResource(getResources(),R.drawable.ghostenemy);

		v = new OurView(this);

		v.setOnTouchListener(this);

		x = y = 0;

		setContentView(v);

		blob = BitmapFactory.decodeResource(getResources(),R.drawable.femalechara1);

		mainCharacterBox = new Rect((int) x, (int) y, (int) x + 32,(int) y + 32); // rectangle to represent character hit box
		
		if(GameState.newGame){

			ghostsKilled = 0;

			health = 100;
			
			money = 0;

			}

			else

			{

			SharedPreferences mPrefs = getSharedPreferences("save", 0);

			money = mPrefs.getInt("rich", 0);

			ghostsKilled = mPrefs.getInt("kills", 0);

			health = mPrefs.getInt("life", 100);

			}
	}

	@Override
	protected void onPause() {

		super.onPause();

		gameMusic.pause();

		v.pause();
		
		  SharedPreferences.Editor ed = getSharedPreferences("save", 0).edit();
	        ed.putInt("kills", ghostsKilled);
	        ed.putInt("cash", money);
	        ed.putInt("life", health);
	        ed.commit();

	}

	@Override
	protected void onResume() {

		super.onResume();

		gameMusic.start();

		v.resume();

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public class OurView extends SurfaceView implements Runnable {

		Thread t = null;

		SurfaceHolder holder;

		boolean isItOk = false;

		Bitmap w;

		Bitmap r;
		
		Bitmap b;
		
		Bitmap coinPic;
		
		Bitmap repelPic;
		
		 String moneyString = "Money: " + money;
		// Rect repellentBox;

		int spawnTimer;

		Rect proximityBox;
		
		Rect playerHitBox;

		Paint paint = new Paint();
		
		Bomb bomb;
		
		Coin coin;
		
		Repellent repel;
		
		int timer = 0;
		
		//int gameOverTimer = 0;

		private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		
		private ArrayList<Bomb> bombList = new ArrayList<Bomb>();
		
		private ArrayList<Coin> coinList = new ArrayList<Coin>();
		
		private ArrayList<Repellent> repelList = new ArrayList<Repellent>();

		public OurView(Context context) {

			super(context);
			holder = getHolder();
			// + money;

			sprites = new ArrayList<Sprite>();
			
			bombList = new ArrayList<Bomb>();
			
			coinList = new ArrayList<Coin>();
			
			repelList = new ArrayList<Repellent>();

			// Coin coin=new Coin(this,money);

			w = BitmapFactory.decodeResource(getResources(), R.drawable.warning);

			r = BitmapFactory.decodeResource(getResources(),R.drawable.repellent);
			
			b = BitmapFactory.decodeResource(getResources(),R.drawable.bomb);
			
			coinPic = BitmapFactory.decodeResource(getResources(),R.drawable.coin);
			
			repelPic = BitmapFactory.decodeResource(getResources(),R.drawable.repellent);
					
		}

		@Override
		public void run() {

			while (isItOk == true) {

				if (!holder.getSurface().isValid()) {

					continue;

				}

					sprite = new Sprite(OurView.this, ghost, mainCharacterBox);		
					
					bomb = new Bomb (OurView.this, b);
					
					//coin = new Coin (OurView.this,coinPic);
					
				if (money > 9) {

				}

				proximityBox = new Rect((int) x - 150, (int) y - 150,(int) x + 150, (int) y + 150);
				
				playerHitBox = new Rect ((int) x, (int) y, (int) x + 32, (int) y + 32);

				Canvas c = holder.lockCanvas();

				onDraw(c);

				holder.unlockCanvasAndPost(c);

				if (ghostsKilled < 10){
					if (spawnTimer % 500 == 0){
						sprites.add(createSprite(R.drawable.ghostenemy));

					}
				}
				
				if (ghostsKilled >=10){
					if (spawnTimer % 50 == 0) {
						sprites.add(createSprite(R.drawable.ghostenemy));
					}
				}
				
				if(money<10 && money>0){
					if (spawnTimer % 500 == 0) {
						bombList.add(createBomb(R.drawable.bomb));
						money -= 1;
					}
				}
				if(money >=10 && money <20){
					if (spawnTimer % 100 ==0){
						bombList.add(createBomb(R.drawable.bomb));
						money -=1;
					}
				}
				if (money>=20){
					if (spawnTimer % 50 ==0){
						bombList.add(createBomb(R.drawable.bomb));
						money -=1;
					}
				}
				
				if (spawnTimer % 500 == 0){
					repelList.add(createRepel(R.drawable.repellent));
				}
				
				spawnTimer += 2;
				
//				if (gameOverTimer == 300){
//					setContentView(R.layout.activity_main);
//				}
				
			}

		}

		private Sprite createSprite(int resource) {
			Bitmap ghostGroup = BitmapFactory.decodeResource(getResources(),resource);
			return new Sprite(this, ghostGroup, mainCharacterBox);
		}
		
		private Bomb createBomb(int resource){
			Bitmap bombGroup = BitmapFactory.decodeResource(getResources(),resource);
			return new Bomb(this, bombGroup);
		}
		
		private Coin createCoin(int resource){
			Bitmap coinGroup = BitmapFactory.decodeResource(getResources(),resource);
			return new Coin(this, coinGroup, sprite.getSpriteXLocation(), sprite.getSpriteYLocation());
		}
		
		private Repellent createRepel(int resource){
			Bitmap repelGroup = BitmapFactory.decodeResource(getResources(),resource);
			return new Repellent(this, repelGroup);
		}

		protected void onDraw(Canvas c) {

			paint.setColor(Color.WHITE);
			paint.setTextSize(50);

			c.drawARGB(255, 255, 255, 255);

			c.drawColor(Color.BLACK);

			c.drawBitmap(blob, x, y, null);

			c.drawText("Money: " + money, 500, 50, paint);

			c.drawText("Kills: " + ghostsKilled, 750, 50, paint);
			
			c.drawText("HP: " + health, 1000, 50, paint);
			
			if (ghostsKilled < 10){
				c.drawText("Level 1", 20, 40, paint);
			}
			
			if (ghostsKilled >= 10){
				c.drawText("Level 2", 20, 40, paint);
			}
			
			for (Bomb bomb : bombList){
				bomb.drawBomb(c);
			}

			for (Sprite sprite : sprites) {
				sprite.drawGhost(c, x, y);
			}

			for (Coin coin : coinList){
				coin.drawCoin(c);
			}
			
			for (Repellent repellent : repelList){
				repellent.drawRep(c);
			}

			for (Sprite sprite : sprites){
			if (proximityBox.contains(sprite.getSpriteXLocation(),sprite.getSpriteYLocation())) {
				c.drawBitmap(w, sprite.getSpriteXLocation() + 60,sprite.getSpriteYLocation(), null);
			}
			}
			
			for (int i = 0; i< sprites.size(); i++) {
				if (playerHitBox.intersect(sprites.get(i).spriteRect)) {
					//coin.drawCoin(c,sprites.get(i).getSpriteXLocation(),sprites.get(i).getSpriteYLocation());
					sprites.remove(i);
					coinList.add(createCoin(R.drawable.coin));
					ghostsKilled++;
					health -=5;
				}
			}
			
			for (int f = 0; f< bombList.size(); f++){
			if (playerHitBox.intersect(bombList.get(f).getBombRect())){
				ghostsKilled += sprites.size();
				sprites.clear();
				bombList.remove(f);
				}
			}
			
			for (int k = 0; k<coinList.size();k++){
				if (playerHitBox.intersect(coinList.get(k).getCoinRect())){
					money++;
					coinList.remove(k);
				}	
			}
			
			for (int z = 0; z<repelList.size(); z++){
				if (playerHitBox.intersect(repelList.get(z).getRepelRect())){
					health += 3;				
					repelList.remove(z);
					}	
			}
			
//			while (sprites.size()>4){
//				paint.setTextSize(200);
//				c.drawText("GAME OVER",500, 300, paint);
//				gameOverTimer +=100;
//			}
			
		}

		public void pause() {

			isItOk = false;

			while (true) {

				try {

					t.join();

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

				break;

			}

			t = null;

		}

		public void resume() {

			isItOk = true;

			t = new Thread(this);

			t.start();

		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent me) {

		try {

			Thread.sleep(50);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		switch (me.getAction()) {

		case MotionEvent.ACTION_MOVE:

			x = me.getX();

			y = me.getY();

			break;

		}

		return true;

	}

}