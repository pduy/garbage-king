package com.Object;

import com.GarbageKing.*;
import com.InGame.*;

public class Tree extends GameObject{
    int _health;

    public Tree(float x, float y, float speed, int updatePeriod, Sprite living, Sprite dying, int health)
    {
    	super(x, y, speed, updatePeriod, living, dying);
        _health = health;
    }

    @Override
    public void BeingAttacked(int strength)
    {
        _health -= strength;
        if (_health <= 0)
            _isDestroyed = true;
    }
}
