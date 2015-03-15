package com.InGame;

import com.GarbageKing.*;
import android.graphics.*;

public class GameObject {
	static float _DEFAULT_ANGLE = 0;

    //Graphics
    public Sprite _livingSprite;
    public Sprite _dyingSprite;
    public Sprite _curSprite;

    //location
    public float _x;
    public float _y;

    //rotate angle
    public float _angle;
    public boolean _isRotate;

    //stuffs to handle animation
    public double _timeElapsed;

    //Speed of the object
    public float _velocityX;
    public float _velocityY;
    public float _speed;
    public int _updatePeriod;

    //flag variables
    public boolean _isTerminated;
    public boolean _isDestroyed;
    public boolean _isNew;

    //user or enemy
    public int _side;
    
    //Rectangle
    protected Rect _renderRectangle;
    
    public GameObject(float x, float y, float speed, int updatePeriod, Sprite living, Sprite dying) {
    	//location
        _x = x;
        _y = y;

        //speed & animation time
        _speed = speed;

        //flag variables
        _isTerminated = false;
        _isDestroyed = false;
        _isNew = true;

        //sprite
        _livingSprite = living;
        _dyingSprite = dying;
        _curSprite = _livingSprite;

        //reset elapsed time counter
        _timeElapsed = 0;
        _updatePeriod = updatePeriod;

        _angle = _DEFAULT_ANGLE;
        
        _renderRectangle = new Rect();
    }
    
    public void Show(Canvas canvas) {
    	if(!_isDestroyed) {
    		if(!_isRotate)
    			_livingSprite.Show(canvas, (int)_x, (int)_y);
    		else
    			_livingSprite.Show(canvas, (int)_x, (int)_y, _angle);
    	}
    	else if(_dyingSprite != null) {
    		if(!_isRotate) {
    			_dyingSprite.Show(canvas, (int)_x, (int)_y);
    		}
    		else
    			_dyingSprite.Show(canvas, (int)_x, (int)_y, _angle);
    		if(_dyingSprite.index == _dyingSprite.numOfFrames - 1)
    			_isTerminated = true;
    	}
    }
    
    public void UpdateLocation() {
    	
    }
    
    public void CheckIfTerminated() {
    	if(_isTerminated)
    		return;
    }
    
    public void Update() {
    	_timeElapsed += G.timerTick;
    	if(_timeElapsed > _updatePeriod) {
			if(!_isDestroyed) {
				_curSprite.NextFrame();
			}
			else if(_dyingSprite != null)
				_dyingSprite.NextFrame();
			
			_timeElapsed -= _updatePeriod;
    	}
    	
    	if(IsInForm())
    		_isNew = false;
    	
    	if(!_isNew && IsOutOfForm())
    		_isTerminated = true;
    	
    	UpdateLocation();
    }
    
    public Rect GetBoundingRectangle()
    {
    	_renderRectangle.set((int)_x + _livingSprite.desWidth / 5, (int)_y + _livingSprite.desHeight / 5, (int)_x + 4 * _livingSprite.desWidth / 5, (int)_y + 4 * _livingSprite.desHeight / 5);
    	return _renderRectangle;
    }
    
    public Rect GetRenderRectangle() {
    	return _livingSprite.desRect;
    }
    
    public void BeingAttacked(int damage)
    {
    
    }
    
    public boolean IsInForm()
    {
        if (!_isDestroyed)
        {
        	if (_x > 0 - this._livingSprite.desWidth && _x < G.screenWidth + this._livingSprite.desWidth &&
                _y > 0 - this._livingSprite.desHeight && _y < G.screenHeight + this._livingSprite.desHeight)
        		return true;
        }
        else if (_dyingSprite != null)
        {
            if (_x > 0 - this._dyingSprite.desWidth && _x < G.screenWidth + this._dyingSprite.desWidth &&
                _y > 0 - this._dyingSprite.desHeight && _y < G.screenHeight + this._dyingSprite.desHeight)
                return true;
        }
        return false;

    }

    public boolean IsOutOfForm()
    {
        if (!_isDestroyed)
        {
            if (_x < 0 - this._livingSprite.desWidth - 100 || _x > G.screenWidth + this._livingSprite.desWidth + 100||
                _y < 0 - this._livingSprite.desHeight - 100 || _y > G.screenHeight + this._livingSprite.desHeight + 100)
                return true;
        }
        else if (_dyingSprite != null)
        {
        	if (_x < 0 - this._dyingSprite.desWidth - 100|| _x > G.screenWidth + this._dyingSprite.desWidth + 100||
                _y < 0 - this._dyingSprite.desHeight -100 || _y > G.screenHeight + this._dyingSprite.desHeight + 100)
        		return true;
        }
        return false;
    }
}
