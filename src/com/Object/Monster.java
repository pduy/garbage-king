package com.Object;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.GarbageKing.G;
import com.GarbageKing.Sprite;
import com.InGame.GameObject;

public class Monster extends GameObject {
    public Sprite _upSprite;
    public Sprite _rightSprite;

    public int _health;
    public int _maxHealth;
    public int _strength;
    public int _rateOfAttack;
    public int _score;
    public int _moneyDrop;

    public int _attackElapseTime = 0;

    public boolean _isAttacking;
    public boolean _healthBarOn;

    public Point _desPos;
    public Point _nextPos;
    public int _curPos;
    public ArrayList<Point> _path;

    public float _centerX;
    public float _centerY;
    
    //Define if the monster is a walking or flying one
    public boolean _freeMovement;
    public int _isDodgingBullet = 0;
    
   
    Paint _paint;

    //Attack something
    public Sprite _attackingSprite;

    public Monster(float x, float y, float speed, int updatePeriod, Sprite living, Sprite right, Sprite dying, int health, int strength, int rateOfAttack, Sprite attack, ArrayList<Point> path, int score, int money, boolean isFreeMovement)
    {
    	super(x, y, speed, updatePeriod, living, dying);
        _rightSprite = right;
        _upSprite = null;
        _health = _maxHealth = health;
        _strength = strength;
        _rateOfAttack = rateOfAttack;
        _isAttacking = false;

        _attackingSprite = attack;
        _path = path;
        _healthBarOn = false;

        _centerX = _x + _livingSprite.desWidth / 2;
        _centerY = _y + _livingSprite.desHeight / 2;

        _score = score;
        _moneyDrop = money;
        _freeMovement = isFreeMovement;
        _paint = new Paint();
        
        if(_freeMovement)
        	_isRotate = true;
    }
    
    @Override
    public void Show(Canvas sb)
    {
        if (!_isDestroyed && !_isTerminated && _isAttacking && _attackingSprite != null)
            _attackingSprite.Show(sb, (int)_x, (int)_y);
        else if (_isDestroyed == false)
        {
        	if(!_isRotate) {
	            if (_velocityX * _velocityX < _velocityY * _velocityY && _velocityY > 0)
	            {
	                _curSprite = _livingSprite;
	                _livingSprite.Show(sb, (int)_x, (int)_y);
	                //_livingSprite.ShowEffected(sb, (int)_x, (int)_y, Color.BLUE);
	            }
	            else if (_velocityX * _velocityX < _velocityY * _velocityY && _velocityY < 0)
	            {
	                _curSprite = _upSprite;
	                _upSprite.Show(sb, (int)_x, (int)_y);
	            }
	            else if (_velocityX * _velocityX > _velocityY * _velocityY && _velocityX > 0)
	            {
	                _curSprite = _rightSprite;
	                _rightSprite.Show(sb, (int)_x, (int)_y);
	            }
	            else
	            {
	                _curSprite = _rightSprite;
	                _rightSprite.ShowFlip(sb, (int)_x, (int)_y);
	            }
        	}
        	else {
                _angle = (float) (-Math.atan(_velocityX / _velocityY) * 180 / Math.PI);
                _curSprite = _livingSprite;
                _livingSprite.Show(sb, (int)_x, (int)_y, _angle);
        	}
        }
        else if (_dyingSprite != null)
        {
            if (!_isRotate)
                _dyingSprite.Show(sb, (int)_x, (int)_y);
            else
                _dyingSprite.Show(sb, (int)_x, (int)_y, _angle);
            if (_dyingSprite.index == _dyingSprite.numOfFrames - 1)
                _isTerminated = true;
        }
        else
            _isTerminated = true;

        if (_healthBarOn)
        {
        	int col;
            if (_health >= 60)
                col = Color.GREEN;
            else if (_health > 25)
                col = Color.YELLOW;
            else
                col = Color.RED;
            
            _paint.setStrokeWidth(5);
            _paint.setColor(Color.GRAY);
            sb.drawLine(_x, _y - 10, _x + _livingSprite.desWidth, _y - 10, _paint);
            
            _paint.setColor(col);
            sb.drawLine(_x, _y - 10, _x + _livingSprite.desWidth * _health / _maxHealth, _y - 10, _paint);
        }
    }

    public void FindNextPos() 
    {
        float distanceSquared = (_desPos.x - _centerX) * (_desPos.x - _centerX) + (_desPos.y - _centerY) * (_desPos.y - _centerY);

        if (_speed * _speed >= distanceSquared)
        {
            _centerX = _desPos.x;
            _centerY = _desPos.y;

            //Recalculate velocity vector
            if (_curPos < _path.size() - 1)
            {
                _curPos++;
                _desPos = _path.get(_curPos);

                float desDistance = (float) Math.sqrt((double)(_desPos.x - _centerX) * (_desPos.x - _centerX) + (_desPos.y - _centerY) * (_desPos.y - _centerY)); 

                if (_desPos.x == _centerX)
                    _velocityX = 0;
                else if (_desPos.x > _centerX)
                    _velocityX = _speed * (_desPos.x - _centerX) / desDistance;
                else
                    _velocityX = -_speed * (_centerX - _desPos.x) / desDistance;

                if (_desPos.y == _centerY)
                    _velocityY = 0;
                else if (_desPos.y > _centerY)
                    _velocityY = _speed * (_desPos.y - _centerY) / desDistance;
                else
                    _velocityY = -_speed * (_centerY - _desPos.y) / desDistance;
            }
            else
            {
                _velocityX = _velocityY = 0;
            }
        }
        else
        {
            _x += _velocityX;
            _y += _velocityY;
            _centerX += _velocityX;
            _centerY += _velocityY;
        }

    }
    
    public void SetFlyVelocity() {
    	int desX = _path.get(_path.size() - 1).x;
    	int desY = _path.get(_path.size() - 1).y;
    	float distance = (float)Math.sqrt((desX - _x)*(desX - _x) + (desY - _y)*(desY - _y));
    	_velocityX = (desX - _x)*_speed / distance;
    	_velocityY = (desY - _y)*_speed / distance;
    	
    }
    
    public void RefineFlyVelocity() {
//    	int desX = _path.get(_path.size() - 1).x;
//    	int desY = _path.get(_path.size() - 1).y;
//    	
//    	float dx = desX - _x;
//    	float dy = desY - _y;
//    	
//    	float cosine = (float) ((dx * _velocityX + dy * _velocityY) / (Math.sqrt((dx*dx + dy*dy)*(_velocityX*_velocityX + _velocityY*_velocityY))));
//    	
//    	if(cosine < 0.95 && desX > _x) {
//    		FlyRight();
//    		FlyRight();
//    	}
//    	else if(cosine < 0.95 && desX < _x) {
//    		FlyLeft();
//    		FlyLeft();
//    	}
    }
    
    public void Fly() {  	
    	_x += _velocityX;
    	_y += _velocityY;
    	_centerX += _velocityX;
    	_centerY += _velocityX;
    	
    	SetFlyVelocity();
    	_isDodgingBullet = 0;
    }
    
    public void MoveLeft () {
    	_isDodgingBullet = -1;
    	float velocityX = -5 * _velocityY;
    	float velocityY = 5 * _velocityX;
    	
    	_x += velocityX;
    	_y += velocityY;
    	_centerX += velocityX;
    	_centerY += velocityY;
    }
    
    public void MoveRight () {
    	_isDodgingBullet = 1;
    	float velocityX = 5 * _velocityY;
    	float velocityY = -5 * _velocityX;
    	
    	_x += velocityX;
    	_y += velocityY;
    	_centerX += velocityX;
    	_centerY += velocityY;
    }
    
    public void RotateDirection(float alpha) {
//    	float alpha = (float)Math.PI / 180;
    	float velocityX = (float) (_velocityX * Math.cos(alpha) - _velocityY * Math.sin(alpha));
    	float velocityY = (float) (_velocityX * Math.sin(alpha) + _velocityY * Math.cos(alpha));
    	
    	_velocityX = velocityX;
    	_velocityY = velocityY;
    }
    
    
    @Override
    public void UpdateLocation()
    {
        if (!_isAttacking)
        {
        	if(_freeMovement)
        		Fly();
        	else
        		FindNextPos();
        }
    }

    @Override
    public void Update()
    {
    	_timeElapsed += G.timerTick;
    	
    	if(!_isAttacking) {
    		if(_timeElapsed > _updatePeriod) {
    			if (!_isDestroyed)
    	            _curSprite.NextFrame();
    	        else if (_dyingSprite != null)
    	            _dyingSprite.NextFrame();
    			
    			_timeElapsed -= _updatePeriod;
    			
    	        //change location
    			if(!_freeMovement)
    				UpdateLocation();
    		}
    	}
    	else {
    		if(_timeElapsed > 1000/_rateOfAttack/_attackingSprite.numOfFrames) {
    			_attackingSprite.NextFrame();
    			_timeElapsed -= 1000/_rateOfAttack/_attackingSprite.numOfFrames;
    			
    	        //change location
    			if(!_freeMovement)
    				UpdateLocation();
    		}
    	}
    	
    	if(_freeMovement)
    		UpdateLocation();
    	
        if (_isNew && IsInForm())
        {
            _isNew = false;
        }

        if (!_isNew && IsOutOfForm())
        {
            this._isTerminated = true;
        }
    }

    public void BeginAttack(GameObject enemy)
    {
        _isAttacking = true;
        enemy.BeingAttacked(_strength);
    }

    public void StopAttack()
    {
        _isAttacking = false;
    }

    @Override
    public void BeingAttacked(int damage) {
    	// TODO Auto-generated method stub
        _healthBarOn = true;
        this._health -= damage;
        if (_health <= 0)
            MonsterDie();
    }

    public void MonsterDie()
    {
        _isDestroyed = true;
    }

    public boolean MonsterEscape()
    {
        if (_y + _livingSprite.desHeight > G.screenHeight)
        {
            _isTerminated = true;
            return true;
        }
        return false;
    }

    public Monster Clone(float x, float y, ArrayList<Point> path)
    {
        Sprite living, left, dying, attack;

        if (_livingSprite != null)
            living = new Sprite(_livingSprite.texture, _livingSprite.numOfFrames, _livingSprite.desWidth, _livingSprite.desHeight);
        else living = null;

        if (_rightSprite != null)
            left = new Sprite(_rightSprite.texture, _rightSprite.numOfFrames, _rightSprite.desWidth, _rightSprite.desHeight);
        else left = null;

        if (_dyingSprite != null)
            dying = new Sprite(_dyingSprite.texture, _dyingSprite.numOfFrames, _dyingSprite.desWidth, _dyingSprite.desHeight);
        else dying = null;

        if (_attackingSprite != null)
            attack = new Sprite(_attackingSprite.texture, _attackingSprite.numOfFrames, _attackingSprite.desWidth, _attackingSprite.desHeight);
        else attack = null;

        Monster m = new Monster(x, y, _speed, _updatePeriod, living, left, dying, _health, _strength, _rateOfAttack, attack, path, _score, _moneyDrop, _freeMovement);

        m._curPos = 0;
        m._desPos = m._path.get(0);
        m._centerX = x + m._livingSprite.desWidth / 2;
        m._centerY = y + m._livingSprite.desHeight / 2;

        float desDistance = (float)Math.sqrt((double)((m._desPos.x - m._centerX) * (m._desPos.x - m._centerX) + (m._desPos.y - m._centerY) * (m._desPos.y - m._centerY)));

        if(_freeMovement)
        	m.SetFlyVelocity();
        else {
		    if (m._desPos.x == m._centerX)
		        m._velocityX = 0;
		    else if (m._desPos.x > m._centerX)
		        m._velocityX = m._speed * (m._desPos.x - m._centerX) / desDistance;
		    else
		        m._velocityX = - m._speed * (m._centerX - m._desPos.x)  / desDistance;
		
		    if (m._desPos.y == m._centerY)
		        m._velocityY = 0;
		    else if (m._desPos.y > m._centerY)
		        m._velocityY = m._speed * (m._desPos.y - m._centerY) / desDistance;
		    else
		        m._velocityY = -m._speed * (m._centerY - m._desPos.y) / desDistance;
        }

        return m;
    }

    public void OmitBullet(Bullet b)
    {        
    	float dx = _x - b._x;
    	float dy = _y - b._y;
    	float squaredDistance = dx*dx + dy*dy;
    	if(_y < 3 * G.screenHeight / 4 && !_isAttacking && squaredDistance < 10000 && dy < 0) {
			if(_x > G.screenWidth - 100)
				MoveLeft();
			else if(_x < 100)
				MoveRight();
			else if (dx < 0)
				MoveLeft();
			else
				MoveRight();
    	}
    }
    
    public void ReleaseResource() {
    	
    }
    
//    public int GetSquaredDistanceToEnemy() {
//    	if(_enemyBullet != null && _enemyBullet._y > _centerY) {
//			float dx = _x - _enemyBullet._x;
//			float dy = _y - _enemyBullet._y;
//			
//			return (int)(dx*dx + dy*dy);
//    	}
//    	
//    	return Integer.MAX_VALUE;
//    }
}
