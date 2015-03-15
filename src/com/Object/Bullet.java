package com.Object;

import android.graphics.*;
import com.GarbageKing.*;
import com.InGame.*;

public class Bullet extends GameObject {
	static int _DEFAULT_MAX_VELOCITY_X = 35;
    static int _DEFAULT_MIN_VELOCITY_Y = -1;
    static int _DEFAULT_MAX_VELOCITY_Y = -35;

    public int _piercing, _piercing_damage;
    public float _dx, _dy; //velocity
    public int _damage;
    public int _price;
    public BulletEnum _name;
    
    public enum BulletEnum { RED_Normal, BLUE_Normal, GREEN_Normal, RED_Special, BLUE_Special, GREEN_Special,
    	GREEN_Small};

    public Bullet(float x, float y, float dx, float dy, float speed, int updatePeriod, Sprite living, 
    		Sprite dying, int damage, int piercing, int price, BulletEnum name)
    {
        super(x, y, speed, updatePeriod, living, dying);
        this._name = name;
        this._dx = dx;
        this._dy = dy;
        if (_dx > _DEFAULT_MAX_VELOCITY_X)
            _dx =  _DEFAULT_MAX_VELOCITY_X;
        else if (_dx < -_DEFAULT_MAX_VELOCITY_X)
            _dx = -_DEFAULT_MAX_VELOCITY_X;

        if (_dy > _DEFAULT_MIN_VELOCITY_Y)
        {
            _isTerminated = _isDestroyed = true;
        }
        else if (_dy < _DEFAULT_MAX_VELOCITY_Y)
            _dy = _DEFAULT_MAX_VELOCITY_Y;

        _piercing = piercing;
        _piercing_damage = _damage / (_piercing + 1);
        _damage = damage;
        _price = price;
        _angle = (float)Rotate();
        _isRotate = true;
    }

    public double Rotate()
    {
        double tang = _dx / _dy;
        return -Math.atan(tang) * 180 / Math.PI;
    }

    @Override
    public void UpdateLocation()
    {
        this._x += _dx;
        this._y += _dy;
    }

    public void Hit(GameObject enemy)
    {
    	if(_name == BulletEnum.BLUE_Normal) {
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageWater.length;
//    		G.monsterDamageWater[G.soundIndex].start();
    		G.soundPlayer.play(G.water_damage, 1, 1, 1, 0, 1);
    	}
    	else if (_name == BulletEnum.RED_Normal) {
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageFire.length;
//    		G.monsterDamageFire[G.soundIndex].start();
    		G.soundPlayer.play(G.fire_damage, 1, 1, 1, 0, 1);
    	}
    	else if (_name == BulletEnum.GREEN_Normal){
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageGreen.length;
//    		G.monsterDamageGreen[G.soundIndex].start();
    		G.soundPlayer.play(G.green_damage, 1, 1, 1, 0, 1);
    	}
    	else if (_name == BulletEnum.BLUE_Special){
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageBigWater.length;
//    		G.monsterDamageBigWater[G.soundIndex].start();
    		G.soundPlayer.play(G.water_big_damage, 1, 1, 1, 0, 1);
    	}
    	else if (_name == BulletEnum.RED_Special){
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageBigFire.length;
//    		G.monsterDamageBigFire[G.soundIndex].start();
    		G.soundPlayer.play(G.fire_big_damage, 1, 1, 1, 0, 1);
    	}
    	else if (_name == BulletEnum.GREEN_Special){
//    		G.soundIndex = (G.soundIndex + 1) % G.monsterDamageBigGreen.length;
//    		G.monsterDamageBigGreen[G.soundIndex].start();
    		G.soundPlayer.play(G.green_big_damage, 1, 1, 1, 0, 1);
    	}
    	
        _piercing--;
        _damage -= _piercing_damage;
        if (_piercing < 0)
        {        	
            this._isDestroyed = true;
            if (this._dyingSprite != null)
            {
                _dyingSprite.FirstFrame();
            }
 //           _updatePeriod = 40;
            _x = enemy._x + enemy._livingSprite.desWidth / 2 - this._livingSprite.desWidth / 2 - 20;
            _y = enemy._y + enemy._livingSprite.desHeight / 2 - this._livingSprite.desHeight / 2;;
            _dx = 0;
            _dy = 0;
            enemy.BeingAttacked(_damage);
        }
    }

    public void Draw(Canvas spriteBatch)
    {
        super.Show(spriteBatch);
    }

    public Bullet Clone(float x, float y, float dx, float dy)
    {
        Sprite living, dying;
        float truex, truey;
        truex = x - _livingSprite.desWidth / 2;
        truey = y - _livingSprite.desHeight / 2;

        if (_livingSprite != null)
        {
            living = new Sprite(_livingSprite.texture, _livingSprite.numOfFrames, _livingSprite.desWidth, _livingSprite.desHeight);
            truex = x - _livingSprite.desWidth / 2;
            truey = y - _livingSprite.desHeight / 2;
        }
        else living = null;

        if (_dyingSprite != null)
        {
            dying = new Sprite(_dyingSprite.texture, _dyingSprite.numOfFrames, _dyingSprite.desWidth, _dyingSprite.desHeight);
        }
        else dying = null;

        return new Bullet(truex, truey, dx, dy, _speed, _updatePeriod, living, dying, _damage,_piercing, _price, _name);
    }
    
    public void SetEffect(GameEngine _gameEngine)
    {
//    	if (_name == BulletEnum.GREEN_Normal)
//    	{
//    		Element _greenElement = null;
//    		for (Element _element : _gameEngine._elementList)
//    		{
//    			if (_element._type == ElementsEnum.GREEN)
//    			{
//    				_greenElement = _element;
//    				break;
//    			}
//    		}
//    		float dx1 =_dx + _dx/2;
//    		float dy1 =_dy - _dy/2;
//    		float dx2 =_dx - _dx/2;
//    		float dy2 =_dy + _dy/2;
//    		_gameEngine._bulletList.add(_greenElement._bulletFactory[2].Clone(_x + dx1*10, _y + dy1*10, dx1, dy1));
//    		_gameEngine._bulletList.add(_greenElement._bulletFactory[2].Clone(_x + dx2*10, _y + dy2*10, dx2, dy2));
//    		_greenElement = null;
//    	}
//    	
    	
    }
}
