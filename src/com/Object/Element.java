package com.Object;

import java.util.ArrayList;
import com.GarbageKing.*;
import com.Object.Bullet.BulletEnum;

import android.graphics.*;

public class Element {
	String _name;
    public ElementsEnum _type;

    public Bitmap[] _skillTextureList;

    public enum ElementsEnum { RED, BLUE, GREEN};
    //Game Engine

    public Bullet[] _bulletFactory;
    public FirePanel _firePanel;
    //public Skill[] _skillFactory;
    public GainPower _gainPower;
    
    public Element(ElementsEnum type)
    {
    	InitType(type);
    }
    
    public Element(String name)
    {
        _name = name;
        if (_name == "Red")
        {
        	_type = ElementsEnum.RED;
        }
        else if (_name == "Blue")
        {
        	_type = ElementsEnum.BLUE;
        }
        else if (_name == "Green")
        {
        	_type = ElementsEnum.GREEN;
        }
        InitType(_type);
     }

    public void InitType(ElementsEnum type)
    {
        _type = type;
        
        if (_type == ElementsEnum.RED)
        {
            _name = "Red";

            _bulletFactory = new Bullet[2];
            _bulletFactory[0] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.red_bullet_1, 5, 30, 65), 
            		new Sprite(G.explosion_red, 11, 75, 75), 30, 0, 1, BulletEnum.RED_Normal);
            _bulletFactory[1] = new Bullet(0, 0, 0, 0, 0, 30, new Sprite(G.red_bullet_2, 5, 50, 90), 
            		new Sprite(G.explosion_red, 11, 120, 120), 100, 0, 3, BulletEnum.RED_Special);
            

            _firePanel = new FirePanel(FirePanel._DEFAULT_LOCATION_X, FirePanel._DEFAULT_LOCATION_Y, 0, 60,
                new Sprite(G.red_bar, 4, G.screenWidth, G.screenHeight / 8), null, 0);
        }

        if (_type == ElementsEnum.BLUE)
        {
            _name = "Blue";

            _bulletFactory = new Bullet[2];
            _bulletFactory[0] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.blue_bullet_1, 4, 30, 65), 
            		new Sprite(G.explosion_blue, 8, 75, 75), 20, 0, 1, BulletEnum.BLUE_Normal);
            _bulletFactory[1] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.blue_bullet_2, 4, 50, 90), 
            		new Sprite(G.explosion_blue, 8, 120, 120), 70, 0, 3, BulletEnum.BLUE_Special);

            _firePanel = new FirePanel(FirePanel._DEFAULT_LOCATION_X, FirePanel._DEFAULT_LOCATION_Y, 0, 60,
                new Sprite(G.blue_bar, 4, G.screenWidth, G.screenHeight / 8), null, 0);
        }

        if (_type == ElementsEnum.GREEN)
        {
            _name = "Green";

//            _bulletFactory = new Bullet[2];
//            _bulletFactory[0] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.green_bullet_1, 4, 30, 65), new Sprite(G.explosion_green, 8, 75, 75), 30, 0, 1);
//            _bulletFactory[1] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.green_bullet_2, 3, 50, 90), new Sprite(G.explosion_green, 8, 75, 75), 30, 0, 3);
            
            //Test new bullet
            _bulletFactory = new Bullet[3];
            _bulletFactory[0] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.green_bullet_1, 4, 30, 65), 
            		new Sprite(G.explosion_green, 8, 75, 75), 25, 0, 1, BulletEnum.GREEN_Normal);
            _bulletFactory[1] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.green_bullet_2, 3, 50, 90), 
            		new Sprite(G.explosion_green, 8, 120, 120), 80, 0, 3, BulletEnum.GREEN_Special);
//            _bulletFactory[2] = new Bullet(0, 0, 0, 0, 0, 40, new Sprite(G.green_bullet_1, 4, 28, 30), 
//            		new Sprite(G.explosion_green, 8, 60, 60), 7, 0, 1, BulletEnum.GREEN_Small);
            
            _firePanel = new FirePanel(FirePanel._DEFAULT_LOCATION_X, FirePanel._DEFAULT_LOCATION_Y, 0, 60,
                new Sprite(G.green_bar, 4, G.screenWidth, G.screenHeight / 8), null, 0);
        }
        SetGainPower();
    }
    
    public void SetGainPower()
    {
    	_gainPower = new GainPower(0, 0, 0, 0, new Sprite(G.gain_power, 6, 150, 150), null);
    	
//    	_gainPower = new GainPower(0, 0, 0, 0, null, null);
//    	_gainPower.SetType(_type);
    }
    
    public static ArrayList<Element> ChangeElement(ArrayList<Element> _list, int dx)
    {
        int n = _list.size();
        if (dx > 0)
        {
            Element _element = _list.get(0);
            _list.remove(0);
            _list.add(_element);
        }
        else
        {
            _list.add(0, _list.get(n - 1));
            _list.remove(n);
        }
        return _list;

    }
    
    public void Update()
    {
    	_firePanel.Update();
    	if (_gainPower._isActivated)
    	{
    		_gainPower.Update();
    	}
    }
    
    public void Show(Canvas canvas)
    {
    	_firePanel.Show(canvas);
    	if (_gainPower._isActivated)
    		_gainPower.Show(canvas);
    }
}
