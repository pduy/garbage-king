package com.Object;

import android.graphics.*;

import com.GarbageKing.*;
import com.InGame.*;

public class Tower extends GameObject {
    public int _health;
    public int _maxHealth;
    public boolean _healthBarOn;
    private Paint _paint;
    private Rect _boundingRectangle;

    public Tower(float x, float y, float speed, int updatePeriod, Sprite living, Sprite dying, int health)
    {
    	super(x, y, speed, updatePeriod,living, dying);
        _health = _maxHealth = health;
        _healthBarOn = false;
        _paint = new Paint();
        _boundingRectangle = new Rect();
    }

    @Override
    public void BeingAttacked(int strength)
    {
        _health -= strength;
        _healthBarOn = true;
        if (_health < 0)
            _isDestroyed = true;
    }

    @Override
    public void Update()
    {
        super.Update();
    }

    @Override
    public void Show(Canvas canvas)
    {
        super.Show(canvas);

        if(_healthBarOn)
        {
            int col;
            if (_health >= 60)
                col = Color.GREEN;
            else if (_health > 25)
                col = Color.YELLOW;
            else
                col = Color.RED;
            
            _paint.setStrokeWidth(6);
            _paint.setColor(Color.GRAY);
            canvas.drawLine(_x, _y - 10, _x + 1.2f * _livingSprite.desWidth, _y - 10, _paint);
            _paint.setColor(col);
            canvas.drawLine(_x, _y - 10, _x + 1.2f * _livingSprite.desWidth * _health / _maxHealth, _y - 10, _paint);
        }
    }

    public Rect GetBoudingRectangle()
    {
    	_boundingRectangle.set((int)_x + 30, (int)_y + 2 * _livingSprite.desHeight / 3, (int)_x + _livingSprite.desWidth -30, (int)_y + _livingSprite.desHeight);
    	return _boundingRectangle;
    }
}
