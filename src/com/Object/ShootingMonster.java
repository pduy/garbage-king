package com.Object;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Point;

import com.GarbageKing.G;
import com.GarbageKing.Sprite;
import com.Object.Bullet.BulletEnum;

public class ShootingMonster extends Monster {
//	private static int SHOOT_RATE = 5;
	private static int DAMAGE = 5;
	private static int BULLET_SPEED = 5;
	
	private Tower _destination;
	private Bullet _bulletSample;
	private ArrayList<Bullet> _monsterBulletList;
//	private int _elapsedTime;
	
	public ShootingMonster(float x, float y, float speed, int updatePeriod, Sprite living, Sprite right, Sprite dying, int health, int strength, int rateOfAttack, Sprite attack, ArrayList<Point> path, int score, int money, boolean isFreeMovement, Tower destination) {
		// TODO Auto-generated constructor stub
		super(x, y, speed, updatePeriod, living, right, dying, health, strength, rateOfAttack, attack, path, score, money, isFreeMovement);
		_destination = destination;
		_monsterBulletList = new ArrayList<Bullet>();
		_bulletSample = new Bullet(0, 0, 0, 0, 1, 40, new Sprite(G.red_bullet_1, 5, 30, 30), 
        		new Sprite(G.explosion_red, 11, 30, 30), DAMAGE, 0, 0, BulletEnum.RED_Normal);
	}

	public void GenerateBullet() {
//		G.soundIndex = (G.soundIndex + 1) % G.fireShootingSound.length;
//		G.fireShootingSound[G.soundIndex].start();
		G.soundPlayer.play(G.small_fire, 1, 1, 1, 0, 1);
		float distance = (float)Math.sqrt((_destination._x - _x)*(_destination._x - _x) + (_destination._y - _y)*(_destination._y - _y));
		float dx = (_destination._x + _destination._livingSprite.desWidth / 2 - 20 - _x) * BULLET_SPEED / distance;
		float dy = (_destination._y + _destination._livingSprite.desHeight / 2 - _y) * BULLET_SPEED / distance;
		
		Bullet b = _bulletSample.Clone(_x + _livingSprite.desWidth / 2, _y + _livingSprite.desHeight / 2, dx, dy);
		b._isDestroyed = b._isTerminated = false;
		_monsterBulletList.add(b);
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub
		super.Update();
		int i = G._random.nextInt(300);
		if(i == 200) {
			_isAttacking = true;
		}
		else if(_attackingSprite.index == _attackingSprite.numOfFrames - 1) {
			_isAttacking = false;
			_attackingSprite.FirstFrame();
			GenerateBullet();
		}
		
		for(Bullet b : _monsterBulletList) {
			b.Update();
			if(b.GetBoundingRectangle().intersect(_destination.GetBoudingRectangle())) {
				b.Hit(_destination);
			}
		}
	}
	
	@Override
	public void Show(Canvas sb) {
		// TODO Auto-generated method stub
		super.Show(sb);
		
		for(Bullet b : _monsterBulletList) {
			b.Show(sb);
		}
	}
	
	@Override
	public ShootingMonster Clone(float x, float y, ArrayList<Point> path) {
		// TODO Auto-generated method stub
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

        ShootingMonster m = new ShootingMonster(x, y, _speed, _updatePeriod, living, left, dying, _health, _strength, _rateOfAttack, attack, path, _score, _moneyDrop, _freeMovement, _destination);

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
	
	@Override
	public void ReleaseResource() {
		for(int i = _monsterBulletList.size() - 1; i >= 0; --i)
			if(_monsterBulletList.get(i)._isTerminated)
				_monsterBulletList.remove(i);
	}
}
