package com.InGame;

import android.graphics.Canvas;

import com.GarbageKing.G;
import com.GarbageKing.Sprite;
import com.Object.Bullet;
import com.Object.Bullet.BulletEnum;

public class GunSpell {
	public static int MONEY_COST = 20;
	public static int BULLET_SPEED = 10;
	public Sprite _friendlyMonster;
	public GameEngine _gameEngine;
	private int _nTrees;
	private Bullet _bulletSample;
	private int[] _treePosX;
	private int[] _treePosY;
	private int[] _treeShootingRates;
	private int _count;
	private int _elapsedTime;
	
	public GunSpell(GameEngine gameEngine) {
		_friendlyMonster = new Sprite(G.treeLiving, 1, 140, 140);
		_gameEngine = gameEngine;
		_count = 0;
		_elapsedTime = 0;
		_bulletSample = new Bullet(0, 0, 0, 0, 1, 40, new Sprite(G.stone, 1, 40, 40), 
        		new Sprite(G.explosion_red, 11, 40, 40), 20, 0, 0, BulletEnum.RED_Normal);
	}
	
	public void Invoke() {
		_nTrees = G._random.nextInt(2) + 2;
		_treePosX = new int[_nTrees];
		_treePosY = new int[_nTrees];
		_treeShootingRates = new int[_nTrees];
		_count = 5000;
		
		for(int i = 0; i < _nTrees; ++i) {
			_treePosX[i] = G._random.nextInt(3 * G.screenWidth / 5) + G.screenWidth / 5;
			_treePosY[i] = G._random.nextInt(G.screenHeight / 2) + G.screenHeight / 4;
			_treeShootingRates[i] = G._random.nextInt(10) + 2;
		}
	}
	
	public void Update() {
		_elapsedTime += G.timerTick;
		for(int i = 0; i < _nTrees; ++i) {
			if(_elapsedTime % 1000 == 0) {
				int monsterListSize = _gameEngine._monsterList.size();
				
				if(monsterListSize > 0) {
					int monsterIndex = G._random.nextInt(monsterListSize);
					float monsterX = _gameEngine._monsterList.get(monsterIndex)._x;
					float monsterY = _gameEngine._monsterList.get(monsterIndex)._y;
					float distance = (float)Math.sqrt((monsterX - _treePosX[i]) * (monsterX - _treePosX[i]) + (monsterY - _treePosY[i]) * (monsterY - _treePosY[i]));
					float dx = (monsterX - _treePosX[i]) * BULLET_SPEED / distance;
					float dy = (monsterY - _treePosY[i]) * BULLET_SPEED / distance;
					Bullet b = _bulletSample.Clone(_treePosX[i] + _friendlyMonster.desWidth / 2, _treePosY[i] + _friendlyMonster.desHeight / 2, dx, dy);
					b._isDestroyed = b._isTerminated = false;
					_gameEngine.AddBullet(b);
//					G.fireShootingSound.start();
				}
				
			}
		}
	}
	
	public boolean Show(Canvas canvas) {
		_count -= G.timerTick;
		for(int i = 0; i < _nTrees; ++i) {
			_friendlyMonster.ShowEffected(canvas, _treePosX[i], _treePosY[i]);
		}
		
		Update();
		
		if(_count < 0)
			return true;
		
		return false;
	}
	
	public void Refresh() {
		_count = 0;
		_elapsedTime = 0;
		_treePosX = null;
		_treePosY = null;
		_treeShootingRates = null;
	}
	
}
