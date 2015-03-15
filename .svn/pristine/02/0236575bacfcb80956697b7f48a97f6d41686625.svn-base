package com.Object;

import android.graphics.Canvas;

import com.GarbageKing.Sprite;

public class SpecialTree extends Tree {
	public SpecialTree(float x, float y, float speed, int updatePeriod, Sprite living, Sprite dying, int health) {
		// TODO Auto-generated constructor stub
		super(x, y, speed, updatePeriod, living, dying, health);
	}
	
	@Override
	public void Show(Canvas canvas) {
		// TODO Auto-generated method stub
		if(!_isDestroyed) {
    		if(!_isRotate)
    			_livingSprite.ShowEffected(canvas, (int)_x, (int)_y);
    		else
    			_livingSprite.Show(canvas, (int)_x, (int)_y, _angle);
    	}
    	else if(_dyingSprite != null) {
    		if(!_isRotate) {
    			_dyingSprite.ShowEffected(canvas, (int)_x, (int)_y);
    		}
    		else
    			_dyingSprite.Show(canvas, (int)_x, (int)_y, _angle);
    		if(_dyingSprite.index == _dyingSprite.numOfFrames - 1)
    			_isTerminated = true;
    	}
	}
}
