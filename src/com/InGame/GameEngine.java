package com.InGame;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.GarbageKing.G;
import com.GarbageKing.MainActivity;
import com.GarbageKing.Sprite;
import com.Object.Bullet;
import com.Object.Element;
import com.Object.Element.ElementsEnum;
import com.Object.FirePanel;
import com.Object.Monster;
import com.Object.Player;
import com.Object.ShootingMonster;
import com.Object.Tower;
import com.Object.Tree;
import com.Object.Wave;

public class GameEngine {	
	public MyInGame _inGame;
	
	public ArrayList<Element> _elementList;
	
	//Graphics
	private Rect _bkgSrcRect;
	private Rect _bkgDesRect;
	private Rect _shadow;
	
	//Temp
    ArrayList<ArrayList<Point>> _path = new ArrayList<ArrayList<Point>>();
    public int _elapseTime;
    //Game Script
    public GameScript _gameScript;

    //Game Engine
    public Tower _tower;
    public Monster[] _monsterFactory;
    public ArrayList<Tree> _treeList;
    public ArrayList<Monster> _monsterList;
    public ArrayList<Monster> _flyingMonsterList;
    public ArrayList<Bullet> _bulletList;
//    public ArrayList<Skill> _skillList;
    public Spelling _spelling;
    
    boolean _isHold = false;
    PointF _saveCurrentPress, _saveCurrentHold;

    //Player
    Player _player;
    
    public Paint _paint;
    public static Context _context;
    public int bkgShake = 10;
	
    public GameEngine(Context context, MyInGame inGame) {
    	_context = context;
    	_paint = new Paint();
    	_inGame = inGame;
    	_player = new Player(0, 25, 5);
    	
		_bkgSrcRect = new Rect();
		_bkgDesRect = new Rect(0, 0, G.screenWidth, G.screenHeight);
		_shadow = new Rect(0, 0, G.blackImg.getWidth(), G.blackImg.getHeight());
		onStart(1);
    } 
    
    private void onStart(int level) {
    	_elapseTime = 0;
    	_bkgSrcRect.set(0, 0, G.stage1.getWidth(), G.stage1.getHeight());
    	
    	if(_monsterList == null)
    		_monsterList = new ArrayList<Monster>();
    	
    	if(_flyingMonsterList == null)
    		_flyingMonsterList = new ArrayList<Monster>();
    	
    	if(_bulletList == null)
    		_bulletList = new ArrayList<Bullet>();
    	
    	if(_elementList == null)
    		_elementList = new ArrayList<Element>();
    	
    	if(_treeList == null)
    		_treeList = new ArrayList<Tree>();
    	
    	//Tower
        _tower = new Tower(160, 615, 0, 120, new Sprite(G.towerTexture, 5, 120, 170), null, 20000);
    	
        //Monster factory
    	if(_monsterFactory == null)
    		_monsterFactory = new Monster[4];
    	
        _monsterFactory[0] = new Monster(0, 0, 1, 70, new Sprite(G.cocacolaGoDown, 8, 60, 60), new Sprite(G.cocacolaGoRight, 4, 60, 60), null, 150, 50, 4, new Sprite(G.cocacolaAttack, 4, 60, 60), null, 15, 10, false);
        _monsterFactory[1] = new Monster(0, 0, 2.5f, 150, new Sprite(G.cigarGoDown, 4, 60, 60), new Sprite(G.cigarGoRight, 4, 60, 60), null, 130, 50, 5, new Sprite(G.cigarAttack, 4, 60, 60), null, 10, 10, false);
        _monsterFactory[2] = new Monster(0, 0, 1.5f, 100, new Sprite(G.snackGoDown, 4, 90, 90), null, null, 150, 50, 5, new Sprite(G.snackAttack, 4, 90, 90), null, 50, 40, true);
        _monsterFactory[3] = new ShootingMonster(0, 0, 2, 100, new Sprite(G.bossGoDown, 8, 100, 100), new Sprite(G.bossGoRight, 8, 100, 100), null, 800, 150, 3, new Sprite(G.bossAttack, 17, 100, 100), null, 100, 70, false, _tower);
        
        //Initialize tree
        _treeList.add(new Tree(90, 480, 0, 60, new Sprite(G.treeLiving, 1, 92, 67), new Sprite(G.treeDying, 5, 92, 67), 100));
        _treeList.add(new Tree(310, 460, 0, 60, new Sprite(G.treeLiving, 1, 92, 67), new Sprite(G.treeDying, 5, 92, 67), 100));
        
        //Path for monster
        ArrayList<Point> path = new ArrayList<Point>();
        path.add(new Point(138, -80));
        path.add(new Point(144, 60));
        path.add(new Point(139, 136));
        path.add(new Point(149, 188));
        path.add(new Point(139, 264));
        path.add(new Point(145, 289));
        path.add(new Point(166, 310));
        path.add(new Point(224, 330));
        path.add(new Point(246, 332));
        path.add(new Point(273, 305));
        path.add(new Point(330, 304));
        path.add(new Point(361, 335));
        path.add(new Point(363, 380));
        path.add(new Point(352, 430));
        path.add(new Point(294, 440));
        path.add(new Point(247, 432));
        path.add(new Point(225, 483));
        path.add(new Point(165, 502));
        path.add(new Point(138, 533));
        path.add(new Point(129, 600));
        //path.add(new Point(151, 653));

        _path.add(path);

        path = new ArrayList<Point>();
        path.add(new Point(530, 210));
        path.add(new Point(397, 200));
        path.add(new Point(362, 240));
        path.add(new Point(344, 300));
        path.add(new Point(363, 360));
        path.add(new Point(367, 562));
        path.add(new Point(335, 625));
        path.add(new Point(335, 651));
        //path.add(new Point(255, 670));

        _path.add(path);
        
        path = new ArrayList<Point>();
        path.add(new Point(530, 430));
        path.add(new Point(430, 430));
        path.add(new Point(394, 426));
        path.add(new Point(368, 461));
        path.add(new Point(362, 565));
        path.add(new Point(309, 650));
        
        _path.add(path);
        
        path = new ArrayList<Point>();
        path.add(new Point(-80, 307));
        path.add(new Point(84, 306));
        path.add(new Point(149, 279));
        path.add(new Point(244, 309));
        path.add(new Point(205, 436));
        path.add(new Point(153, 494));
        path.add(new Point(136, 650));        
        
        _path.add(path);
        
        // Final point is the position of the tower
        for(ArrayList<Point> p : _path)
        	p.add(new Point((int)_tower._x + _tower._livingSprite.desWidth / 2, (int)_tower._y + 2 * _tower._livingSprite.desHeight / 3));
        
        //Time shedule for monster to come
        Random rand = new Random();
        Wave[] waveList = new Wave[10];
        for (int i = 0; i < 10; i++)
        {
            int[] monster = new int[10];
            int[] pathnum = new int[10];
            for (int j = 0; j < 10; j++)
            {
            	
                monster[j] = rand.nextInt(4);
//            	monster[j] = 2;
                pathnum[j] = rand.nextInt(4);
            }

            int[] time = { 0, 500, 1000, 3000, 1000, 1000, 500, 500, 2000, 3000 };

            waveList[i] = new Wave(monster, time, pathnum);
        }
        _gameScript = new GameScript(waveList);
        _spelling = new Spelling(this);
//        _skillList = new ArrayList<Skill>();
//        _skillList.add(new Skill(0, 0, Skill.SkillsEnum.THUNDER, 0, 0,null, null, 50));
//        _skillList.add(new Skill(0, 0, Skill.SkillsEnum.EXPLOSION, 0, 0, null, null, 70));
//        _skillList.add(new Skill(0, 0, Skill.SkillsEnum.BROOM, 0, 0, null, null, 70));
//        _skillList.add(new Skill(0, 0, Skill.SkillsEnum.EARTHQUAKE, 0, 0, null, null, 70));
//        _skillList.add(new Skill(0, 0, Skill.SkillsEnum.GAIN_POWER, 0, 0, null, null, 0));
        NewGame(level);
    }
    
    private void NewGame(int level) {
        _elementList.add(new Element("Red"));
        _elementList.add(new Element("Blue"));
        _elementList.add(new Element("Green"));
        
        _player = G.player;
//        if (_player._allSkillActive)
//        {
//            for (Skill _skill : _skillList)
//            {
//                _skill._isEnable = true;
//            }
//        }
//        else
//        {
//            for (Skill _skill : _skillList)
//            {
//                _skill._isEnable = false;
//            }
//
//        }
    }
    
    public boolean CheckHit(Bullet bullet) {
    	for (Monster monster : _monsterList) {
			if(!monster._isDestroyed && CheckIntersection(bullet, monster)) {
				bullet.Hit(monster);

				if(bullet._isDestroyed)
					return true;
			}
		}
    	
    	for (Monster monster : _flyingMonsterList) {
			if(!monster._isDestroyed && CheckIntersection(bullet, monster)) {
				bullet.Hit(monster);
				
				if(bullet._isDestroyed)
					return true;
			}
		}
    	
		if (!bullet._isDestroyed)
    		return false;
		
		return true;    
	}
    
//    public void SetBulletEffect(Bullet _bullet)
//    {
//    	if (_bullet._name == Bullet.BulletEnum.GREEN_Normal)
//    	{
//    		Element _greenElement = null;
//    		for (Element _element : _elementList)
//    		{
//    			if (_element._type == Element.ElementsEnum.GREEN)
//    			{
//    				_greenElement = _element;
//    				break;
//    			}
//    		}
//    		float dx1 =_bullet._dx + _bullet._dx/2;
//    		float dy1 =_bullet._dy - _bullet._dy/2;
//    		float dx2 =_bullet._dx - _bullet._dx/2;
//    		float dy2 =_bullet._dy + _bullet._dy/2;
//    		_bulletList.add(_greenElement._bulletFactory[2].Clone(_bullet._x + dx1*10, _bullet._y + dy1*10, dx1, dy1));
//    		_bulletList.add(_greenElement._bulletFactory[2].Clone(_bullet._x + dx2*10, _bullet._y + dy2*10, dx2, dy2));
//    		_greenElement = null;
//    	}
//    	
//    }
    
    public boolean CheckIntersection(GameObject object1, GameObject object2) {
    	if(object1.GetBoundingRectangle().intersect(object2.GetBoundingRectangle()))
    		return true;
    	
    	return false;
    }
    
    public boolean CheckIntersection(GameObject object, Rect rect) {
    	if(object.GetBoundingRectangle().intersect(rect))
    		return true;
    	return false;
    }
    
    public void SkillHit(Rect rect, int damage)
    {
    	for (Monster _monster : _monsterList)
    	{
    		if (CheckIntersection(_monster, rect))
    		{
    			_monster.BeingAttacked(damage);
    		}
    	}
    	
    	for (Monster _monster : _flyingMonsterList)
    	{
    		if (CheckIntersection(_monster, rect))
    		{
    			_monster.BeingAttacked(damage);
    		}
    	}
    }
    
    public void Update() {    	    	
    	
    	//Generate monsters based on script
    	if (!_gameScript.IsEndOfWaveList())
        {
    		_elapseTime += G.timerTick;
            if (!_gameScript._waveList[_gameScript._curWave].IsEndOfWave())
            {
                int curWave = _gameScript._curWave;
                int curMons = _gameScript._waveList[curWave]._curMonster;
                if (_elapseTime >= _gameScript._waveList[curWave]._time[curMons])
                {
                	Monster tempMonster;
                	int monsterIndex = _gameScript._waveList[curWave]._monster[curMons];
                	int pathIndex = _gameScript._waveList[curWave]._path[curMons];
                	int monsterX = _path.get(pathIndex).get(0).x;
                	int monsterY = _path.get(pathIndex).get(0).y;
                	tempMonster = _monsterFactory[monsterIndex].Clone(monsterX, monsterY, _path.get(pathIndex));
                	
                	if(tempMonster._freeMovement) 
                		_flyingMonsterList.add(tempMonster);
                	else
                		_monsterList.add(tempMonster);
                	
                    _gameScript._waveList[curWave]._curMonster++;
                    _elapseTime = 0;
                }
            }
            else if (_monsterList.size() == 0)
                _gameScript._curWave++;
        }
    	else  {
        	GameOver(true);
        	return;
        }

    	//Update all bullets
		for (Bullet _bullet : _bulletList)
        {
            if (!_bullet._isDestroyed)
                if (CheckHit(_bullet));
                	//SetBulletEffect(_bullet);
            _bullet.Update();
        }
        
        
        //Update all trees
        for(Tree tree : _treeList)
        	tree.Update();
        
        //Update all flying monsters
        for(Monster monster : _flyingMonsterList) {
        	monster._attackElapseTime += G.timerTick;
        	monster.Update();
        	
            for (Bullet bullet : _bulletList)
            {
                if (!bullet._isDestroyed)
                	monster.OmitBullet(bullet);
            }

            if (monster.GetBoundingRectangle().intersect(_tower.GetBoudingRectangle()) && !_tower._isDestroyed && !monster._isDestroyed)
            {
                if (monster._attackElapseTime >= 1000 / monster._rateOfAttack)
                {
                    monster.BeginAttack(_tower);
                    monster._attackElapseTime = 0;
                }
            }
             
            if (_tower._isDestroyed) {
                monster.StopAttack();
                GameOver(false);
                return;
            }
        }

        //Update all walking monsters
        for (Monster monster : _monsterList)
        {
        	monster._attackElapseTime += G.timerTick;
            monster.Update();
            
            for (Tree tree : _treeList)
            {
                if (CheckIntersection(monster, tree) == true && (tree._y - monster._y) < 5)
                {
                    if (monster._attackElapseTime >= 1000 / monster._rateOfAttack)
                    {
                        monster.BeginAttack(tree);
                        monster._attackElapseTime = 0;

                    }
                }
                if (tree._isDestroyed)
                    monster.StopAttack();
            }

            if (monster.GetBoundingRectangle().intersect(_tower.GetBoudingRectangle()) && !_tower._isDestroyed && !monster._isDestroyed)
            {
                if (monster._attackElapseTime >= 1000 / monster._rateOfAttack)
                {
                    monster.BeginAttack(_tower);
                    monster._attackElapseTime = 0;
                }

            }
             
            if (_tower._isDestroyed) {
                monster.StopAttack();
                GameOver(false);
                return;
            }
        }
        
        _tower.Update();
        _elementList.get(0).Update();
//        for (Skill _skill : _skillList)
//        {
//            if (_skill._isActivated)
//                _skill.Update(_monsterList);
//        }
        G.player.Update();
    }
    
    public void RemoveTerminatedObjects()
    {
        for (int i = _bulletList.size() - 1; i >= 0; --i)
        {
            if (_bulletList.get(i)._isTerminated)
                _bulletList.remove(i);
        }
        
        for (int i = _monsterList.size() - 1; i >= 0; --i)
        {
        	_monsterList.get(i).ReleaseResource();
            if (_monsterList.get(i)._isTerminated)
            {
                _player.KillMonster(_monsterList.get(i));
                _monsterList.remove(i);
                
            }
        }
        
        for (int i = _flyingMonsterList.size() - 1; i >= 0; --i)
        {
            if (_flyingMonsterList.get(i)._isTerminated)
            {
                _player.KillMonster(_flyingMonsterList.get(i));
                _flyingMonsterList.remove(i);
                
            }
        }
        
        for (int i = _treeList.size() - 1; i >= 0; --i)
        {
            if (_treeList.get(i)._isTerminated)
                _treeList.remove(i);
        }
    }
    
    public void Show(Canvas _gameCanvas) 
    {
		_gameCanvas.drawBitmap(G.stage1, _bkgSrcRect, _bkgDesRect, _paint);
		if (_spelling.bEarthquake || _spelling.bThunder || _spelling.bFire)
		{
			_paint.setAlpha(100);
			if (_spelling.bThunder )
			{
				_paint.setAlpha(180);
				int l = G._random.nextInt();
				if (l % 10 == 0)
					_paint.setAlpha(0);
			}
			_gameCanvas.drawBitmap(G.blackImg, _shadow, _bkgDesRect, _paint);
			_paint.setAlpha(255);
		}
		for(Monster monster : _monsterList)
			monster.Show(_gameCanvas);
		
		for(Tree tree : _treeList)
			tree.Show(_gameCanvas);
		
		for(Monster monster : _flyingMonsterList)
			monster.Show(_gameCanvas);
		
		for (Bullet _bullet : _bulletList)
            _bullet.Draw(_gameCanvas);
        
        _elementList.get(0).Show(_gameCanvas);
		
		_gameScript.Show(_gameCanvas);
		
		_tower.Show(_gameCanvas);
		
		_spelling.Show(_gameCanvas);
//        for (Skill _skill : _skillList)
//        {
//            if (_skill._isActivated)
//                _skill.Show(_gameCanvas);
//        }
        
        _player.Show(_gameCanvas);
		LateUpdate(_gameCanvas);           
    }
    
    public void LateUpdate(Canvas _gameCanvas)
    {
    	RemoveTerminatedObjects();
    	if (_spelling.bEarthquake && _elapseTime % 40 == 0)
    	{
    		_bkgDesRect.left += bkgShake;  _bkgDesRect.right += bkgShake;
    		if (bkgShake == 1)
    			bkgShake = 10;
    		bkgShake *= -1;
    	}
    	else if(!_spelling.bEarthquake)
    	{
    		if (_bkgDesRect.left != 0)
    		{
    			int i = _bkgDesRect.left;
    			_bkgDesRect.left = 0;
    			_bkgDesRect.right -= i;
    		}
    		bkgShake = 1;
    	}
    }
    
    public void SpellCheck(MotionEvent event)
    {
    	_spelling.check(event);
    }
    
    public boolean SingleTapUp(MotionEvent event)
    {
    	return _spelling.TapOnScreen(event);
    }
    
    public void GainPower(float x, float y)
    {
    	if (y > FirePanel._DEFAULT_LOCATION_Y)
    	{
	    	_saveCurrentHold = new PointF(x, y);
	    	_isHold = true;
	        if (_elementList.get(0)._gainPower._isEnable)
	        	_elementList.get(0)._gainPower.OnHold(_saveCurrentHold.x,_saveCurrentHold.y, _elementList.get(0)._type);
    	}
    }
    
    public void StopGainPower()
    {
    	_isHold = false;
    	if (_elementList.get(0)._gainPower._isActivated)
    		_elementList.get(0)._gainPower.OnHoldComplete();
    }
    
    public boolean AddUserBullet(float x, float y, float velocityX, float velocityY)
    {
    	_saveCurrentPress = new PointF(x, y);
        if (_saveCurrentPress.y > FirePanel._DEFAULT_LOCATION_Y)
        {
        	try {
    			G.semaphore.acquire();
    			if (_isHold)
                {
                	if(_elementList.get(0)._type == ElementsEnum.RED) {
//                		G.soundIndex = (G.soundIndex + 1) % G.bigFireShootingSound.length;
//                		G.bigFireShootingSound[G.soundIndex].start();
                		G.soundPlayer.play(G.big_fire, 1, 1, 1, 0, 1);
                	}
                	else if(_elementList.get(0)._type == ElementsEnum.BLUE) {
//                		G.soundIndex = (G.soundIndex + 1) % G.bigWaterShootingSound.length;
//                		G.bigWaterShootingSound[G.soundIndex].start();
                		G.soundPlayer.play(G.big_water, 1, 1, 1, 0, 1);
                	}
                	else {
//                		G.soundIndex = (G.soundIndex + 1) % G.bigGreenShootingSound.length;
//                		G.bigGreenShootingSound[G.soundIndex].start();
                		G.soundPlayer.play(G.big_green, 1, 1, 1, 0, 1);
                	}
                		
                    if (_player._money >= _elementList.get(0)._bulletFactory[1]._price)
                    {
                    	Bullet b = _elementList.get(0)._bulletFactory[1].Clone(_saveCurrentHold.x, _saveCurrentHold.y, velocityX, velocityY);
                    	AddBullet(b);
                    	_player.UseMoney(_elementList.get(0)._bulletFactory[1]._price);
                    }
                }
                else
                {
                    if (_player._money >= _elementList.get(0)._bulletFactory[0]._price)
                    {
                    	if(_elementList.get(0)._type == ElementsEnum.RED) {
//                    		G.soundIndex = (G.soundIndex + 1) % G.fireShootingSound.length;
//                    		G.fireShootingSound[G.soundIndex].start();
                    		G.soundPlayer.play(G.small_fire, 1, 1, 1, 0, 1);
                    	}
                    	else if(_elementList.get(0)._type == ElementsEnum.BLUE) {
//                    		G.soundIndex = (G.soundIndex + 1) % G.waterShootingSound.length;
//                    		G.waterShootingSound[G.soundIndex].start();
                    		G.soundPlayer.play(G.small_water, 1, 1, 1, 0, 1);
                    	}
                    	else {
//                    		G.soundIndex = (G.soundIndex + 1) % G.greenShootingSound.length;
//                    		G.greenShootingSound[G.soundIndex].start();
                    		G.soundPlayer.play(G.small_green, 1, 1, 1, 0, 1);
                    	}
                    	Bullet b = _elementList.get(0)._bulletFactory[0].Clone(_saveCurrentPress.x, _saveCurrentPress.y, velocityX, velocityY);
                    	AddBullet(b);
                        _player.UseMoney(_elementList.get(0)._bulletFactory[0]._price);
                    }
                }
    			G.semaphore.release();
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            StopGainPower();
            return true;
        }
        return false;
    }
    
    public void AddBullet(Bullet bullet) {
		_bulletList.add(bullet);
}
    
    public void GameOver(boolean isWin) {
    	((MainActivity)G.context).mainClass.inGame.freeze = true;
    	((MainActivity)G.context).mainClass.inGame.GameOver(isWin);
    }
    
//    public void ReleaseGameEngine() {
//    	_monsterFactory = null;
//    	_flyingMonsterList = null;
//    	_bulletList = null;
//    	_monsterList = null;
//    	_elementList = null;
//    }
}
