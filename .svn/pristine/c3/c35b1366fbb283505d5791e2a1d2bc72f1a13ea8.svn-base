package com.InGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.GarbageKing.G;
import com.GarbageKing.PSprite;

class Vertex {
	
	private Bitmap texture;
	private Rect srcRect, desRect;
	private Paint _paint, paint_active;
	public int x, y;
	public boolean isActive;
	
	public Vertex(Bitmap texture, int x, int y, int size) {
		this.texture = texture;		
		
		srcRect = new Rect(0, 0, texture.getWidth(), texture.getHeight());
		desRect = new Rect(x, y, x + size, y + size);
		_paint = new Paint();
		_paint.setAlpha(123);
		paint_active = new Paint();
		this.x = x + size/2;
		this.y = y + size/2;
		this.isActive = false;
	}
	
	public void show(Canvas canvas) {
		if (isActive)
			canvas.drawBitmap(texture, srcRect, desRect, paint_active);
		else
			canvas.drawBitmap(texture, srcRect, desRect, _paint);
	}
	
	public boolean contains(int x, int y) {
		return desRect.contains(x, y);
	}
}

public class Spelling {
	private static int FIRE_MONEY_COST = 20;
	
	private boolean _isActive;
	private Vertex[] vertices;
	private int[] verticesChosen;
	private boolean[] _flags;
	private int index;
	private Paint _paint;
	private int movX, movY;
	private PSprite fireSpell;
	private ThunderSpell _thunderSpell;
	private EarthquakeSpell _earthquakeSpell;
	private GunSpell _gunSpell;
	private PlantSpell _plantSpell;
	private GameEngine gameEngine;
	
	private int counter;
	public boolean bFire, bThunder, bEarthquake, bGun, bPlant;
	
	public Spelling(GameEngine gameEngine) {
		_isActive = false;
		int size =  130;
		vertices = new Vertex[5];
		vertices[0] = new Vertex(G.eFire, (G.screenWidth - size)/2, G.screenHeight/4 - size/2, size);
		vertices[1] = new Vertex(G.eWood, G.screenWidth/6 - size/2, G.screenHeight/4 + size/2, size);
		vertices[2] = new Vertex(G.eEarth, 5 * G.screenWidth/6 - size/2, G.screenHeight/4 + size/2, size);
		vertices[3] = new Vertex(G.eWater, 9 * G.screenWidth/30 - size/2, G.screenHeight/4 + 2*size, size);
		vertices[4] = new Vertex(G.eMetal, 21 * G.screenWidth/30 - size/2, G.screenHeight/4 + 2*size, size);
		verticesChosen = new int[6];
		_flags = new boolean[5];
		for (int i = 0; i < 5; ++i) {
			verticesChosen[i] = -1;
			_flags[i] = false;
		}
		verticesChosen[5] = -1;
		index = 0;
		_paint = new Paint();
		_paint.setStyle(Paint.Style.STROKE);
		_paint.setColor(0xffffff);
		_paint.setStrokeWidth(8);
		_paint.setStrokeCap(Paint.Cap.ROUND);
		_paint.setAlpha(150);
		fireSpell = new PSprite(G.fireExplosion, 10, 0, G.screenHeight/4, G.screenWidth, G.screenHeight/2);
		_thunderSpell = new ThunderSpell(gameEngine);
		_earthquakeSpell = new EarthquakeSpell(gameEngine);
		_gunSpell = new GunSpell(gameEngine);
		_plantSpell = new PlantSpell(gameEngine);
		this.gameEngine = gameEngine;
		bFire = bThunder = bEarthquake = bGun = bPlant = false;
	}
	
	public void Update()
	{
		if (bEarthquake)
		{
			if (!_earthquakeSpell.Update())
			{
				bEarthquake = false;
				_earthquakeSpell.RefreshSkill();
			}
		}
		if (bPlant) {
			if(!_plantSpell.Update()) {
				bPlant = false;
				_plantSpell.Refresh();
			}
		}
	}
	
	public void Show(Canvas canvas) {	
		Update();
		if (bThunder) {
			if (_thunderSpell.Show(canvas)) {
				bThunder = false;
			}
		}
		
		if (bEarthquake)
		{
			_earthquakeSpell.Show(canvas);
			//If Earthquake is finished then bEarthquake = false and reset the earthquake;
		}
		
		if(bGun) {
			if(_gunSpell.Show(canvas)) {
				bGun = false;
				_gunSpell.Refresh();
			}
		}
		
		if (bFire) {
			counter++;
			fireSpell.Show(canvas);
			if (counter % 2 == 0) {
				fireSpell.NextFrame();		
				if(fireSpell.index == fireSpell.numOfFrames / 2) {
					gameEngine.SkillHit(fireSpell.desRect, 100);
				}
				if (fireSpell.index == 0) {
					bFire = false;
				}	
			}
		}	
		
		if (_isActive) {			
			for (Vertex vertex : vertices) {
				vertex.show(canvas);
			}
			drawLines(canvas);
		}
	}
	
	private void drawLines(Canvas canvas) {
		if (index == 0)
			return;
		for (int i = 1; i < index; ++i) {
			canvas.drawLine(vertices[verticesChosen[i-1]].x, vertices[verticesChosen[i-1]].y, 
					vertices[verticesChosen[i]].x, vertices[verticesChosen[i]].y, _paint);					
		}
		if (index < 6)
			canvas.drawLine(vertices[verticesChosen[index-1]].x, vertices[verticesChosen[index-1]].y, movX, movY, _paint);
	}

	public void check(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:		
			if (y < 3 * G.screenHeight / 4 && !bEarthquake && !bPlant) {
				_isActive = true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (_isActive) {
				for (int i = 0; i < 5; ++i) {
					if (vertices[i].contains(x, y) && index < 6) {
						if (index == 1 && verticesChosen[0] == i)
							continue;
						else if (index > 1) {
							if (verticesChosen[index-1] == i || verticesChosen[index-2] == i)
								continue;
						}
						_flags[i] = true;
						verticesChosen[index] = i;
						index++;
						vertices[i].isActive = true;
					}
				}
				movX = x;
				movY = y;
			}
			break;
		case MotionEvent.ACTION_UP:
			if (_isActive) {
				_isActive = false;
				checkSpelling();
				for (int i = 0; i < 5; ++i) {
					verticesChosen[i] = -1;
					_flags[i] = false;
					vertices[i].isActive = false;
				}
				verticesChosen[5] = -1;
				index = 0;
			}
			break;
		}
	}

	private void checkSpelling() {
		if (index == 6 && verticesChosen[0] == 1 && verticesChosen[1] == 3 && verticesChosen[2] == 0
				&& verticesChosen[3] == 4 && verticesChosen[4] == 2 && verticesChosen[5] == 1) {
			invokeThunder();
		} else if (index == 6 && verticesChosen[0] == 0 && verticesChosen[1] == 1 && verticesChosen[2] == 3
				&& verticesChosen[3] == 4 && verticesChosen[4] == 2 && verticesChosen[5] == 0) {
			invokeFire();
		} else if (index == 6 && verticesChosen[0] == 3 && verticesChosen[1] == 0 && verticesChosen[2] == 4
				&& verticesChosen[3] == 1 && verticesChosen[4] == 2 && verticesChosen[5] == 3) {
			//Toast.makeText(G.context, "Earthquake activated!", Toast.LENGTH_SHORT).show();
			invokeEarthquake();
		} else if (index == 5 && verticesChosen[0] == 0 && verticesChosen[1] == 1 && verticesChosen[2] == 2
				&& verticesChosen[3] == 3 && verticesChosen[4] == 4) {
//			Toast.makeText(G.context, "Broom activated!", Toast.LENGTH_SHORT).show();
			invokeGun();
		} else if (index == 4 && verticesChosen[0] == 0 && verticesChosen[1] == 1 && verticesChosen[2] == 2 
				&& verticesChosen[3] == 0) {
//			Toast.makeText(G.context, "Broom activated!", Toast.LENGTH_SHORT).show();
			invokePlant();
		}
		
	}

	private void invokeEarthquake() {
		if(G.player._money >= EarthquakeSpell.MONEY_COST) {
			bEarthquake = true;
			G.player.UseMoney(EarthquakeSpell.MONEY_COST);
		}
	}
	
	private void invokeThunder() {
		if(G.player._money > ThunderSpell.MONEY_COST) {
			bThunder = true;
			G.player.UseMoney(ThunderSpell.MONEY_COST);
		}
	}

	private void invokeFire() {
//		G.soundIndex = (G.soundIndex + 1) % G.explosionSound.length;
//		G.explosionSound[G.soundIndex].start();
		if(G.player._money > FIRE_MONEY_COST) {
			G.soundPlayer.play(G.explosion, 1, 1, 1, 0, 1);
			counter = 0;
			bFire = true;
			G.player.UseMoney(FIRE_MONEY_COST);
		}
	}
	
	private void invokeGun() {
		if(G.player._money > GunSpell.MONEY_COST) {
			bGun = true;
			_gunSpell.Invoke();
			G.player.UseMoney(GunSpell.MONEY_COST);
		}
	}
	
	private void invokePlant() {
		if(G.player._money > PlantSpell.MONEY_COST) {
			bPlant = true;
			G.player.UseMoney(PlantSpell.MONEY_COST);
		}
	}
	
	public boolean TapOnScreen(MotionEvent event)
	{
		if (bEarthquake)
			return EarthQuakeTap(event);
		else if(bPlant) {
			return _plantSpell.PlantSpellTap(event);
		}
		else 
			return false;
	}
	
	public boolean EarthQuakeTap(MotionEvent event)
	{
		return _earthquakeSpell.AddStone(event.getX(), event.getY());
	}
}
