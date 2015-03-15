package com.Object;

import com.GarbageKing.*;
import com.InGame.*;

public class PathGuideObject extends GameObject{
    public int _desx, _desy;
    public float _dx, _dy;
    public int _zoomRate;
    public int _predict_step, _step;
    public float _acceleration; 
    public PathGuideObject(int x, int y, int desx, int desy, int speed, int updatePeriod, Sprite living, Sprite dying, int zoomRate, float acceleration) {
        super(x, y, speed, updatePeriod, living, dying);

        Sprite _living, _dying;

        if (living != null)
        {
            _living = new Sprite(living.texture, living.numOfFrames, living.desWidth, living.desHeight);
        }
        else _living = null;

        if (dying != null)
            _dying = new Sprite(dying.texture, dying.numOfFrames, dying.desWidth, dying.desHeight);
        else _dying = null;

        _livingSprite = _living;
        _dyingSprite = _dying;

        _updatePeriod = updatePeriod;
        _speed = speed;

        int k = _living.desWidth /2; 
        _x = x - k;         _desx = desx - k;
        k = _living.desHeight /2;
        _y = y - k;        _desy = desy - k;
        _zoomRate = zoomRate;
        float deltax = _desx - _x;
        float deltay = _desy - _y;

        float length = deltax * deltax + deltay * deltay;
        double moverate = (float)speed / Math.sqrt(length);

        _dx = (float)(deltax * Math.abs(moverate));
        _dy = (float)(deltay * Math.abs(moverate));

        _predict_step = _step = Math.abs((int)(Math.sqrt(length)/(float)speed));
        _acceleration = acceleration;
    }

    @Override
    public void Update()
    {
        UpdateLocation();
        if (_x < _desx || _y > _desy || _step <= 0 )
        {
        	if (!_isDestroyed){
                _x = _x + _livingSprite.desWidth/2 - _dyingSprite.desWidth/2;
                _y = _y + _livingSprite.desHeight/2 - _dyingSprite.desHeight/2;
        	}
            _isDestroyed = true;
            _dx = 0;
            _dy = 0;
        }
        super.Update();
    }

    @Override
    public void UpdateLocation()
    {
        _x += _dx;
        _y += _dy;
        if (_zoomRate == 0)
        {
        	_dx *= _acceleration;
        	_dy *= _acceleration;
        	return;
        }
    
        if (_step > _predict_step / 2)
        {
            _x -= _zoomRate;
            _y -= 2*_zoomRate;
            if (!_isDestroyed)
            {
                _livingSprite.desWidth += _zoomRate;
                _livingSprite.desHeight += _zoomRate;
            }
        }
        else if (_step > 0)
        {
            _x += _zoomRate;
            _y += _zoomRate*2;
            if (!_isDestroyed)
            {
                _livingSprite.desWidth -= _zoomRate;
                _livingSprite.desHeight -= _zoomRate;
            }
        }
        _step--;
    }
}
