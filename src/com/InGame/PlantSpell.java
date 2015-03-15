package com.InGame;

import android.view.MotionEvent;

import com.GarbageKing.G;
import com.GarbageKing.Sprite;
import com.Object.Tree;

public class PlantSpell {
	public static int MONEY_COST = 5;
	public GameEngine _gameEngine;
	private int _count = 1000;
	
	public PlantSpell(GameEngine gameEngine) {
		// TODO Auto-generated constructor stub
		_gameEngine = gameEngine;
	}
	
	public void AddTree(float x, float y) {
		_gameEngine._treeList.add(new Tree(x - 50, y - 30 , 0, 60, new Sprite(G.treeLiving, 1, 130, 130), new Sprite(G.treeDying, 5, 130, 130), 100));
	}
	
	public boolean Update () {
		_count -= G.timerTick;
		if(_count < 0)
			return false;
		
		return true;
	}
	
	public boolean PlantSpellTap(MotionEvent e) {
		AddTree(e.getX(), e.getY());
		return true;
	}
	
	public void Refresh() {
		_count = 1000;
	}
}
