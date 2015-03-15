package com.InGame;

import android.graphics.*;

import com.GarbageKing.G;
import com.GarbageKing.PSprite;

public class ThunderSpell {
	private static int LOWEST_Y = 7*G.screenHeight/8 - 20;
	public static int MONEY_COST = 25;
	private static int MAX_HITS = 140;
	private static float RECTANGLE_HEIGHT_RATE = 5f/6f;
	
	private static int[] COUNTER_RATE = {3, 1, 4, 6};
	Rect _hitRect;
	private int _damage;
	private PSprite[] _thundersSprite;
	private int [] _counter; //c0, c1, c2, c3;
	private boolean [] _flag;//f0, f1, f2, f3;
	private int _counterSum;
	private boolean [] _isFinished; //_isFinished0, _isFinished1, _isFinished2, _isFinished3;
	private GameEngine _gameEngine;
	
	public ThunderSpell(GameEngine gameEngine) {
		this._gameEngine = gameEngine;
		_thundersSprite = new PSprite[4];
		_thundersSprite[0] = new PSprite(G.thunder, 11, 0, 0, 61, 200);
		_thundersSprite[1] = new PSprite(G.thunder, 11, 0, 0, 55, 364);
		_thundersSprite[2] = new PSprite(G.thunder, 11, 0, 0, 53, 283);
		_thundersSprite[3] = new PSprite(G.thunder, 11, 0, 0, 66, 260);
		
		_hitRect = new Rect();
		for (int i = 0; i < 4; ++i) {
			UpdatePosition(i);
		}
		_counter = new int[4];
		_counter[0] = _counter[1] = _counter[2] = _counter[3] = 0;
		
		_flag = new boolean[4];
		_flag[0] = _flag[1] = _flag[2] = _flag[3] = true;
		
		_isFinished = new boolean[4];
		_isFinished[0] = _isFinished[1] = _isFinished[2] = _isFinished[3] = false;
		
		_counterSum = 0;
		_damage = 50;
	}
	
	private void UpdatePosition(int index) {
		int tmp = _thundersSprite[index].desHeight / 4;
		_thundersSprite[index].x = G._random.nextInt(G.screenWidth - _thundersSprite[index].desWidth);
		_thundersSprite[index].y = G._random.nextInt(LOWEST_Y - _thundersSprite[index].desHeight + tmp) - tmp;
	}
	
	public boolean Show(Canvas canvas) {
		_counterSum++;
		for (int i = 0; i < 4; i++)
		{
			if (_flag[i])
			{
				_counter[i] ++;
				if (_counter[i] % COUNTER_RATE[i] == 0 )
				{
					if (_counterSum < MAX_HITS)
					{
						_counter[i] = 0;
						_flag[i] = false;
						
					}
					else 
					{
						_isFinished[i] = true;
					}
				}
			}
			
			if (!_flag[i])
			{
//				G.soundIndex = (G.soundIndex + 1) % G.thunderSound.length;
//				G.thunderSound[G.soundIndex].start();
				_counter[i]++;
				_thundersSprite[i].Show(canvas);
				_thundersSprite[i].NextFrame();
				if (_thundersSprite[i].index == 0) {
					_flag[i] = true;
					_counter[i] = 0;
					UpdatePosition(i);
				}
				else if (_thundersSprite[i].index ==  4)//_thundersSprite[i].numOfFrames - 1)
				{
					_hitRect.set(_thundersSprite[i].x + 8, 
							_thundersSprite[i].y + (int)(_thundersSprite[i].desHeight*RECTANGLE_HEIGHT_RATE),
							_thundersSprite[i].x + _thundersSprite[i].desWidth - 8, 
							_thundersSprite[i].y + _thundersSprite[i].desHeight); 
					_gameEngine.SkillHit(_hitRect, _damage);
					G.soundPlayer.play(G.lightning, 1, 1, 1, 0, 1);
				}
			}
		}

		for (int i = 0; i < 4; i++)
		{
			if (!_isFinished[i])
				return false;
		}
		
		_isFinished[0] = _isFinished[1] = _isFinished[2] = _isFinished[3] = false;
		_counterSum = 0;
		return true;
	}
}