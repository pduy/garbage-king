//package com.Object;
//
//import java.util.*;
//import android.graphics.*;
//import com.GarbageKing.*;
//import com.InGame.*;
//import com.Object.Element.ElementsEnum;

/*public class Skill extends GameObject {
	public static int _DEFAULT_HIT_DX = -2;
    public static int _DEFAULT_HIT_DY = 2;

    public static enum SkillsEnum {THUNDER, EXPLOSION, BROOM, EARTHQUAKE, GAIN_POWER};
    public Element.ElementsEnum _elementType;
    public ArrayList<Bullet> _hitList;
    public boolean _isEnable = true;
    public int thunderCount, _cast_Time = 0;
    public int _damage;
    public boolean _isActivated = false;
    public String _element;
    public int _moneyCost;
    public SkillsEnum _name;
    public int earthQuakeDx, earthQuakeCount;
    public ArrayList<GameObject> _objsList;
    public ArrayList<Point> _desList;

    public Skill(float x, float y, SkillsEnum name, float speed, int updatePeriod, Sprite living, Sprite dying, int damage) 
    {
    	
    	super(x, y, speed, updatePeriod, living, dying);
        _x = x; _y = y;

        _updatePeriod = updatePeriod;

        _hitList = new ArrayList<Bullet>();
        _damage = damage;
        _name = name;
        _isEnable = false;
        _isActivated = false;

        _objsList = new ArrayList<GameObject>();
        switch (_name)
        {
            case THUNDER:
                _element = "blue";
                _moneyCost = 12;
                _elementType = Element.ElementsEnum.BLUE;
                _objsList.add(new GameObject(50, 50, 0, 30, new Sprite(G.explosion_red, 11, 70, 210), null));
                _objsList.add(new GameObject(200, 100, 0, 40, new Sprite(G.explosion_red, 11, 55, 350), null));
                _objsList.add(new GameObject(300, 15, 0, 80, new Sprite(G.explosion_red, 11, 60, 280), null));
                
//                _objsList.add(new GameObject(50, 50, 0, 30, new Sprite(G.thunder_spell, 11, 70, 210), null));
//                _objsList.add(new GameObject(200, 100, 0, 40, new Sprite(G.thunder_spell, 11, 55, 350), null));
//                _objsList.add(new GameObject(300, 15, 0, 80, new Sprite(G.thunder_spell, 11, 60, 280), null));
                break;
            case EXPLOSION:
                _element = "red";
                _moneyCost = 20;
                _elementType = Element.ElementsEnum.RED;
                _objsList.add(new GameObject(0, (G.screenHeight - G.screenWidth) / 2, 0, 100, 
                		new Sprite(G.explosion_red, 10, G.screenWidth, 
                				G.screenHeight), null));
                break;
            case BROOM:
                _element = "green";
                _moneyCost = 15;
                _elementType = Element.ElementsEnum.GREEN;
                //_objsList.add(new GameObject(-400, 30, 0, 120, new Sprite(G.broom_spell, 5, 400, 360), null));
                _objsList.add(new GameObject(-400, 30, 0, 120, new Sprite(G.explosion_red, 5, 400, 360), null));
                break;
            case EARTHQUAKE:
                _moneyCost = 12;
                _elementType = Element.ElementsEnum.GREEN;
                EarthQuakeInit();
                break;
            case GAIN_POWER:
                break;
        }
    }

    public void Update(ArrayList<Monster> _monsterList)
    {
        switch (_name)
        {
            case THUNDER:
                for (GameObject obj : _objsList)
                {
                    obj.Update();
                }
                Sprite s1 = _objsList.get(0)._livingSprite;
                Sprite s2 = _objsList.get(1)._livingSprite;
                Sprite s3 = _objsList.get(2)._livingSprite;

                int i1 = s1.index;
                int i2 = s2.index;
                int i3 = s3.index;

                if (i1 == 0)
                {
                    _objsList.get(0)._x = G._random.nextInt(G.screenWidth - s1.desWidth);
                    _objsList.get(0)._y = G._random.nextInt((int)(630 - s1.desHeight- (-s1.desHeight / 2 ))) + (-s1.desHeight / 2);
                    if (++thunderCount == 12)
                    {
                        thunderCount = 0;
                        _isActivated = false;
                    }
                }

                if (i1 == 5)
                    OnLightningHit(_objsList.get(0)._x, _objsList.get(0)._y + (2 / 3) * s1.desHeight, s1.desWidth, (1 / 3) * s1.desHeight, _monsterList);

                if (i2 == 0)
                {
                    _objsList.get(1)._x = G._random.nextInt(G.screenWidth - s2.desWidth);
                    _objsList.get(1)._y = G._random.nextInt((int)(630 - s2.desHeight-(-s2.desHeight / 2))) + (-s2.desHeight / 2);
                }
                if (i2 == 5)
                {
//                    if (thunderCount % 3 == 0)
//                        G.lightning.Play();
                    OnLightningHit(_objsList.get(1)._x, _objsList.get(1)._y, s2.desWidth, s2.desHeight, _monsterList);
                }
                if (i3 == 0)
                {
                    _objsList.get(2)._x = G._random.nextInt(G.screenWidth - s3.desWidth);
                    _objsList.get(2)._y = G._random.nextInt((int)(630 - s3.desHeight- (-s3.desHeight / 2))) + (-s3.desHeight / 2);
                }
                if (i3 == 5)
                    OnLightningHit(_objsList.get(2)._x, _objsList.get(2)._y, s3.desWidth, s3.desHeight, _monsterList);
                break;
            case EXPLOSION:
                Sprite exp = _objsList.get(0)._livingSprite;
                if (exp.index == 9)
                {
                    OnFireExplosionHit(_objsList.get(0)._x, _objsList.get(0)._y, exp.desWidth, exp.desHeight, _monsterList);
                    //exp.Texture = null;
                    _objsList.remove(0);
                    _objsList.add(new GameObject(0, (G.screenHeight - G.screenWidth) / 2, 0, 100, new Sprite(G.explosion_red, 10, G.screenWidth, G.screenWidth), null));
                    _isActivated = false;
                }
                else
                {
                    _objsList.get(0).Update();
                }
                break;
            case BROOM:
                _objsList.get(0).Update();
                _objsList.get(0)._x += 5;
                if (_objsList.get(0)._x >= G.screenWidth)
                {
                    _isActivated = false;
                    _objsList.get(0)._x = -_objsList.get(0)._livingSprite.desWidth;
                }
                OnSweep((int)_objsList.get(0)._x, (int)_objsList.get(0)._y, _objsList.get(0)._livingSprite.desWidth, _objsList.get(0)._livingSprite.desHeight, _monsterList);
                break;

            case EARTHQUAKE:
                earthQuakeDx *= -1;
                PathGuideObject obj;
                for (GameObject _tempObj : _objsList)
                {
                	obj = (PathGuideObject) _tempObj;
                    obj.Update();
                    if (obj._isDestroyed && obj._dyingSprite.index == 0)
                    {
                        OnThrowStone((int)obj._x, (int)obj._y, obj._dyingSprite.desWidth, obj._dyingSprite.desHeight, _monsterList);
                    }
                }
                for (int i = _objsList.size() - 1; i >= 0; i--)
                {
                    if (_objsList.get(i)._isTerminated)
                    {
                        _objsList.remove(i);
                    }
                }
                if (++earthQuakeCount > 200)
                {
                    _isActivated = false;
                    _objsList.clear();
                    earthQuakeCount = 0;
                }
                break;
            case GAIN_POWER:
                if (_objsList.size() > 0)
                    _objsList.get(0).Update();
                break;
        }
    }


    private void OnLightningHit(float x, float y, int width, int height, ArrayList<Monster> _monsterList)
    {
        Rect liningRect = new Rect((int)x, (int)y, width, height);

        for (Monster m : _monsterList)
        {
            if (liningRect.intersect(m.GetBoundingRectangle()))
            {
                m.GetHit(_damage);
            }
        }
    }

    private void OnFireExplosionHit(float x, float y, int width, int height, ArrayList<Monster> _monsterList)
    {
        Rect ExplosionRect = new Rect((int)x, (int)y, width, height);

        for (Monster m : _monsterList)
        {
            if (ExplosionRect.intersect(m.GetBoundingRectangle()))
            {
                m.GetHit(_damage);
            }
        }
    }



    private void OnSweep(int x, int y, int width, int height, ArrayList<Monster> _monsterList)
    {
        Rect sweepRect = new Rect(x, y + height - 180, width, 180);

        for (Monster item : _monsterList)
        {
            if (sweepRect.intersect(item.GetBoundingRectangle()))
            {
                item.GetHit(_damage);
                item._x = x + width;
            }
        }

    }

    private void OnThrowStone(int x, int y, int width, int height, ArrayList<Monster> _monsterList)
    {
        Rect ExplosionRect = new Rect((int)x, (int)y, width, height);

        for (Monster m : _monsterList)
        {
            if (ExplosionRect.intersect(m.GetBoundingRectangle()))
            {
                m.GetHit(_damage);
            }
        }
    }
    
    @Override
    public void Show(Canvas spriteBatch)
    {
        switch (_name)
        {
            case THUNDER:
                for (GameObject obj : _objsList)
                { 
                    obj.Show(spriteBatch); 
                }
                break;

            case EXPLOSION:
                _objsList.get(0).Show(spriteBatch);
                break;

            case BROOM:
                _objsList.get(0).Show(spriteBatch);
                break;
            case EARTHQUAKE:
            	PathGuideObject obj;
                for (GameObject _tempObj : _objsList)
                {
                	obj = (PathGuideObject)_tempObj;
                    obj.Show(spriteBatch);
                }
                break;
            case GAIN_POWER:

                if (_objsList.size() > 0)
                    _objsList.get(0).Show(spriteBatch);
                break;
        }
    }

    public void EarthQuakeInit()
    {
        earthQuakeDx = 4;
        earthQuakeCount = 0;
        _objsList = new ArrayList<GameObject>();
    }

    public void OnTouch(int x, int y)
    {
        _objsList.add(new PathGuideObject(FirePanel.CenterX(), FirePanel.CenterY(), x, y, 10, 0, 
            new Sprite(G.explosion_red, 1, 30, 30), new Sprite(G.explosion_red, 10, 55, 55), 4, 1.1f));
    }

    public void OnHold(float x, float y, ElementsEnum type)
    {
        if (_name == SkillsEnum.GAIN_POWER && !_isActivated && _isEnable)
        {
            _isActivated = true;
            _objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power, 6, 162, 150), null));
//            if (type == ElementsEnum.RED)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_red, 22, 162, 150), null));
//            else if (type == ElementsEnum.BLUE)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_blue, 22, 162, 150), null));
//            else if (type == ElementsEnum.GREEN)
//            	_objsList.add(new GameObject(x, y, 0, 0, new Sprite(G.gain_power_green, 22, 162, 150), null));
            _objsList.get(0)._x -= _objsList.get(0)._livingSprite.desWidth/2;
            _objsList.get(0)._y -= _objsList.get(0)._livingSprite.desHeight/2;
        }
    }

    public void OnHoldComplete()
    {
        if (_name == SkillsEnum.GAIN_POWER)
            _objsList.clear();
        _isActivated = false;
    }
}

*/