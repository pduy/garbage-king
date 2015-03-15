package com.Object;

import android.graphics.Canvas;

import com.GarbageKing.G;
import com.GarbageKing.Sprite;
import com.InGame.*;
import com.Object.Element.ElementsEnum;

public class GainPower extends GameObject{

	public boolean _isActivated, _isEnable;
	public ElementsEnum _type;
	public GainPower(float x, float y, float speed, int updatePeriod,
			Sprite living, Sprite dying) {
		super(x, y, speed, updatePeriod, living, dying);
		// TODO Auto-generated constructor stub
		
		_x = x; _y = y;
		_livingSprite = living;
		_dyingSprite = dying;
		_isActivated = false;
		_isEnable = true;
		
		_speed = speed;
		_updatePeriod = updatePeriod;
		
	}

	public void SetType(ElementsEnum type)
	{
		this._type = type;
		if (_livingSprite != null)
			_livingSprite = new Sprite(G.gain_power, 6, 162, 150);
		
//		if (type == ElementsEnum.RED)
//			_livingSprite = new Sprite(G.gain_power_red, 22, 162, 150);
//		else if (type == ElementsEnum.BLUE)
//			_livingSprite = new Sprite(G.gain_power_blue, 22, 162, 150);
//		else if (type == ElementsEnum.GREEN)
//			_livingSprite = new Sprite(G.gain_power_green, 22, 162, 150);

	}
	
	public void Show(Canvas canvas)
	{
		super.Show(canvas);
	}
	
    public void OnHold(float x, float y, ElementsEnum type)
    {
        if (_isEnable)
        {
//        	G.gainPowerSound.start();
        	G.soundPlayer.play(G.gainPower, 1, 1, 1, 0, 1);
            _isActivated = true;
            _livingSprite.index = 0;
            _x = x - _livingSprite.desWidth/2;
            _y = y - _livingSprite.desHeight/2;
            
//            if (type == ElementsEnum.RED)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_red, 22, 162, 150), null));
//            else if (type == ElementsEnum.BLUE)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_blue, 22, 162, 150), null));
//            else if (type == ElementsEnum.GREEN)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_green, 22, 162, 150), null));
        }
    }

    public void OnHoldComplete()
    {
        _isActivated = false;
        _livingSprite.index = 0;
    }

}
