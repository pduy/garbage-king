package com.GarbageKing;

import java.io.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.media.*;
import android.util.Log;
import android.view.*;

import com.Object.Player;

/*
 * All macros, constants, global variables, general methods are listed here.
 */
public abstract class G {

	// Macros, constants, global variables...-----------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	static ColorMatrixColorFilter cmcf;
	
	public static Semaphore semaphore;
	public static Player player;
	public static MediaPlayer music;
	public static MediaPlayer click;
	
	//Shooting sound
//	public static int soundIndex = 0;
//	public static MediaPlayer[] fireShootingSound;
//	public static MediaPlayer[] waterShootingSound;
//	public static MediaPlayer[] greenShootingSound;
//	public static MediaPlayer[] bigFireShootingSound;
//	public static MediaPlayer[] bigWaterShootingSound;
//	public static MediaPlayer[] bigGreenShootingSound;
//	
//	public static MediaPlayer[] monsterDamageFire;
//	public static MediaPlayer[] monsterDamageWater;
//	public static MediaPlayer[] monsterDamageGreen;
//	public static MediaPlayer[] monsterDamageBigFire;
//	public static MediaPlayer[] monsterDamageBigWater;
//	public static MediaPlayer[] monsterDamageBigGreen;
//	
//	public static MediaPlayer gainPowerSound;
//	public static MediaPlayer bulletBoomSound;
//	public static MediaPlayer[] explosionSound;
//	public static MediaPlayer[] thunderSound;
	
	public static int small_fire;
	public static int small_water;
	public static int small_green;
	public static int big_fire;
	public static int big_water;
	public static int big_green;
	public static int fire_damage;
	public static int water_damage;
	public static int green_damage;
	public static int fire_big_damage;
	public static int green_big_damage;
	public static int water_big_damage;
	public static int earthquake;
	public static int stone_sound;
	
	public static int gainPower;
	public static int explosion;
	public static int lightning;
	public static SoundPool soundPlayer;
	
	public static Context context;
	public static int timerTick;
	
	public static Bitmap blackImg;
	
	//Monster bitmap
	public static Bitmap stage1;
	
	public static Bitmap cocacolaGoDown;
//	public static Bitmap cocacolaGoUp;
	public static Bitmap cocacolaGoRight;
	public static Bitmap cocacolaAttack;
	
	public static Bitmap cigarGoDown;
//	public static Bitmap cigarGoUp;
	public static Bitmap cigarGoRight;
	public static Bitmap cigarAttack;
	
	public static Bitmap snackGoDown;
//	public static Bitmap snackGoUp;
	public static Bitmap snackGoRight;
	public static Bitmap snackAttack;
	
	public static Bitmap bossGoDown;
	public static Bitmap bossGoRight;
	public static Bitmap bossAttack;
	
	public static Bitmap treeLiving;
	public static Bitmap treeDying;
	
	public static Bitmap winTexture;
	public static Bitmap loseTexture;
	
	//region Sound 
//    public static SoundEffect btnClickSoundEffect;
//    public static SoundEffect lightning;
	
    //region Others
    public static int screenWidth;
    public static int screenHeight;
    public static Bitmap dot;

    //region Elements
    public static Bitmap eFire;
    public static Bitmap eWater;
    public static Bitmap eWood;
    public static Bitmap eEarth;
    public static Bitmap eMetal;
    public static Bitmap fireExplosion;
    public static Bitmap thunder;
    public static Bitmap stone;

    //region objects
    public static Bitmap red_bullet_1;
    public static Bitmap red_bullet_2;
    public static Bitmap blue_bullet_1;
    public static Bitmap blue_bullet_2;
    public static Bitmap green_bullet_1;
    public static Bitmap green_bullet_2;
    
//    public static Bitmap yellow_electric_bullet;
    
    public static Bitmap towerTexture;

    public static Bitmap explosion_red;
    public static Bitmap explosion_blue;
    public static Bitmap explosion_green;

    public static Bitmap red_bar;
    public static Bitmap blue_bar;
    public static Bitmap green_bar;

    public static Bitmap gain_power;
    public static Random _random;
    
	// Initialization-----------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
   
    
    public static void initGame(Resources res) {
    	soundPlayer = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    	
		_random = new Random();
		
		stage1 = BitmapFactory.decodeResource(res, R.drawable.stage1);
    	cocacolaGoDown = BitmapFactory.decodeResource(res, R.drawable.go_down_cocacola);
//    	cocacolaGoUp = BitmapFactory.decodeResource(res, R.drawable.go_up_cocacola);
    	cocacolaGoRight = BitmapFactory.decodeResource(res, R.drawable.go_right_cocacola);      
    	cigarGoDown = BitmapFactory.decodeResource(res, R.drawable.cigar_series);
//    	cigarGoUp = BitmapFactory.decodeResource(res, R.drawable.cigar_up_series);
        cigarGoRight = BitmapFactory.decodeResource(res, R.drawable.cigar_right_series);
        cocacolaAttack = BitmapFactory.decodeResource(res, R.drawable.attack_cocacola);
        cigarAttack = BitmapFactory.decodeResource(res, R.drawable.cigar_attack);
        snackGoDown = BitmapFactory.decodeResource(res, R.drawable.down_snack);
//        snackGoUp = BitmapFactory.decodeResource(res, R.drawable.up_snack);
//        snackGoRight = BitmapFactory.decodeResource(res, R.drawable.right_snack);
        snackAttack = BitmapFactory.decodeResource(res, R.drawable.attack_snack);
        bossGoDown = BitmapFactory.decodeResource(res, R.drawable.boss_move_down);
        bossGoRight = BitmapFactory.decodeResource(res, R.drawable.boss_move_right);
		bossAttack = BitmapFactory.decodeResource(res, R.drawable.boss_attack);
        
        treeLiving = BitmapFactory.decodeResource(res, R.drawable.tree_living);
        treeDying = BitmapFactory.decodeResource(res, R.drawable.tree_dying);
    	
        
     // Load Bullet
        red_bullet_1 = BitmapFactory.decodeResource(res, R.drawable.fire_bullet_1);
        red_bullet_2 = BitmapFactory.decodeResource(res, R.drawable.fire_bullet_2);
        blue_bullet_1 = BitmapFactory.decodeResource(res, R.drawable.blue_bullet_1);
        blue_bullet_2 = BitmapFactory.decodeResource(res, R.drawable.blue_bullet_2);
        green_bullet_1 = BitmapFactory.decodeResource(res, R.drawable.green_bullet_1);
        green_bullet_2 = BitmapFactory.decodeResource(res, R.drawable.green_bullet_2);
        
//        yellow_electric_bullet = BitmapFactory.decodeResource(res, R.drawable.yellow_electric_bullet);
        
        towerTexture = BitmapFactory.decodeResource(res, R.drawable.tower);
        
        explosion_red = BitmapFactory.decodeResource(res, R.drawable.fire_explosion_1);
        explosion_blue = BitmapFactory.decodeResource(res, R.drawable.blue_explosion);
        explosion_green = BitmapFactory.decodeResource(res, R.drawable.green_explosion);

        red_bar = BitmapFactory.decodeResource(res, R.drawable.fire_line_red_png);
        blue_bar = BitmapFactory.decodeResource(res, R.drawable.fire_line_blue_png);
        green_bar = BitmapFactory.decodeResource(res, R.drawable.fire_line_green_png);

        gain_power = BitmapFactory.decodeResource(res, R.drawable.gain_power);
        winTexture = BitmapFactory.decodeResource(res, R.drawable.you_win);
        loseTexture = BitmapFactory.decodeResource(res, R.drawable.you_lose);
        
		//Load elements
        eFire = BitmapFactory.decodeResource(res, R.drawable.e_fire);
        eWater = BitmapFactory.decodeResource(res, R.drawable.e_water);
        eWood = BitmapFactory.decodeResource(res, R.drawable.e_wood);
        eEarth = BitmapFactory.decodeResource(res, R.drawable.e_earth);
        eMetal = BitmapFactory.decodeResource(res, R.drawable.e_metal);
        
        //Skill texture
        fireExplosion = BitmapFactory.decodeResource(res, R.drawable.fire_explosion);
        thunder = BitmapFactory.decodeResource(res, R.drawable.thunder);
        stone = BitmapFactory.decodeResource(res, R.drawable.stone);
        
        //Sound
//        fireShootingSound = new MediaPlayer[5];
//        for(int i = 0; i < fireShootingSound.length; ++i)
//        	fireShootingSound[i] = MediaPlayer.create(context, R.raw.small_fire);
//          
//        waterShootingSound = new MediaPlayer[5];
//        for(int i = 0; i < waterShootingSound.length; ++i)
//        	waterShootingSound[i] = MediaPlayer.create(context, R.raw.small_water);
//
//        greenShootingSound = new MediaPlayer[5];
//        for(int i = 0; i < greenShootingSound.length; ++i)
//        	greenShootingSound[i] = MediaPlayer.create(context, R.raw.small_green);
//        
//        bigFireShootingSound = new MediaPlayer[2];
//        for(int i = 0; i < bigFireShootingSound.length; ++i)
//        	bigFireShootingSound[i] = MediaPlayer.create(context, R.raw.big_fire);
//        
//        bigWaterShootingSound = new MediaPlayer[2];
//        for(int i = 0; i < bigWaterShootingSound.length; ++i)
//        	bigWaterShootingSound[i] = MediaPlayer.create(context, R.raw.big_water);
//        
//        bigGreenShootingSound = new MediaPlayer[2];
//        for(int i = 0; i < bigGreenShootingSound.length; ++i)
//        	bigGreenShootingSound[i] = MediaPlayer.create(context, R.raw.big_green);        
//        
//        monsterDamageFire = new MediaPlayer[5];
//        for(int i = 0; i < monsterDamageFire.length; ++i)
//        	monsterDamageFire[i] = MediaPlayer.create(context, R.raw.fire_damage);  
//
//        monsterDamageWater = new MediaPlayer[5];
//        for(int i = 0; i < monsterDamageWater.length; ++i)
//        	monsterDamageWater[i] = MediaPlayer.create(context, R.raw.water_damage); 
//        
//        monsterDamageGreen = new MediaPlayer[5];
//        for(int i = 0; i < monsterDamageGreen.length; ++i)
//        	monsterDamageGreen[i] = MediaPlayer.create(context, R.raw.green_damage); 
//        
//        monsterDamageBigFire = new MediaPlayer[2];
//        for(int i = 0; i < monsterDamageBigFire.length; ++i)
//        	monsterDamageBigFire[i] = MediaPlayer.create(context, R.raw.fire_big_damage); 
//
//        monsterDamageBigWater = new MediaPlayer[2];
//        for(int i = 0; i < monsterDamageBigWater.length; ++i)
//        	monsterDamageBigWater[i] = MediaPlayer.create(context, R.raw.water_big_damage); 
//        
//        monsterDamageBigGreen = new MediaPlayer[2];
//        for(int i = 0; i < monsterDamageBigGreen.length; ++i)
//        	monsterDamageBigGreen[i] = MediaPlayer.create(context, R.raw.green_big_damage); 
//        
//        gainPowerSound = MediaPlayer.create(context, R.raw.energy);
//        
//        explosionSound = new MediaPlayer[3];
//        for(int i = 0; i < explosionSound.length; ++i)
//        	explosionSound[i] = MediaPlayer.create(context, R.raw.boom);
//        
//        thunderSound = new MediaPlayer[5];
//        for(int i = 0; i < thunderSound.length; ++i)
//        	thunderSound[i] = MediaPlayer.create(context, R.raw.lightning); 
        
        small_fire = soundPlayer.load(context, R.raw.new_small_fire_2, 1);
        small_water = soundPlayer.load(context, R.raw.small_water, 1);
        small_green = soundPlayer.load(context, R.raw.small_green, 1);
        big_fire = soundPlayer.load(context, R.raw.big_fire, 1);
        big_water = soundPlayer.load(context, R.raw.big_water, 1);
        big_green = soundPlayer.load(context, R.raw.big_green, 1);
        fire_damage = soundPlayer.load(context, R.raw.fire_damage, 1);
        green_damage = soundPlayer.load(context, R.raw.green_damage, 1);
        water_damage = soundPlayer.load(context, R.raw.water_damage, 1);
        fire_big_damage = soundPlayer.load(context, R.raw.fire_big_damage, 1);
        green_big_damage = soundPlayer.load(context, R.raw.green_big_damage, 1);
        water_big_damage = soundPlayer.load(context, R.raw.water_big_damage, 1);
        gainPower = soundPlayer.load(context, R.raw.energy, 1);
        lightning = soundPlayer.load(context, R.raw.lightning, 1);
        explosion = soundPlayer.load(context, R.raw.boom, 1);
        earthquake = soundPlayer.load(context, R.raw.earthquake, 1);
        stone_sound = soundPlayer.load(context, R.raw.stone_sound, 1);
        
		player = new Player(0, 2000, 2);
    }
    
    public static void releaseGame() {
    	stage1.recycle();
    	cocacolaGoDown.recycle();
//    	cocacolaGoUp.recycle();
    	cocacolaGoRight.recycle();      
    	cigarGoDown.recycle();
//    	cigarGoUp.recycle();
        cigarGoRight.recycle();
        cocacolaAttack.recycle();
        cigarAttack.recycle();
        snackGoDown.recycle();
//        snackGoUp.recycle();
//        snackGoRight.recycle();
        snackAttack.recycle();
        red_bullet_1.recycle();
        red_bullet_2.recycle();
        blue_bullet_1.recycle();
        blue_bullet_2.recycle();
        green_bullet_1.recycle();
        green_bullet_2.recycle();
        towerTexture.recycle();
        explosion_red.recycle();
        explosion_blue.recycle();
        explosion_green.recycle();
        red_bar.recycle();
        blue_bar.recycle();
        green_bar.recycle();
        gain_power.recycle();
        winTexture.recycle();
        loseTexture.recycle();
       
        stage1 = null;
    	cocacolaGoDown = null;
//    	cocacolaGoUp = null;
    	cocacolaGoRight = null;      
    	cigarGoDown = null;
//    	cigarGoUp = null;
        cigarGoRight = null;
        cocacolaAttack = null;
        cigarAttack = null;
        snackGoDown = null;
//        snackGoUp = null;
//        snackGoRight = null;
        snackAttack = null;        
        red_bullet_1 = null;
        red_bullet_2 = null;
        blue_bullet_1 = null;
        blue_bullet_2 = null;
        green_bullet_1 = null;
        green_bullet_2 = null;
        towerTexture = null;
        explosion_red = null;
        explosion_blue = null;
        explosion_green = null;
        red_bar = null;
        blue_bar = null;
        green_bar = null;
        gain_power = null;
        winTexture = null;
        loseTexture = null;
        
        eFire.recycle();
        eWater.recycle();
        eWood.recycle();
        eEarth.recycle();
        eMetal.recycle();        
        fireExplosion.recycle();
        thunder.recycle();
        stone.recycle();
        
        
        eFire = null;
        eWater = null;
        eWood = null;
        eEarth = null;
        eMetal = null;        
        fireExplosion = null;
        thunder = null;
        stone = null;
        
        soundPlayer.release();
        soundPlayer = null;
        
//        for(int i = 0; i < fireShootingSound.length; ++i)
//        	fireShootingSound[i].release();
//        for(int i = 0; i < waterShootingSound.length; ++i)
//        	waterShootingSound[i].release();
//        for(int i = 0; i < greenShootingSound.length; ++i)
//        	greenShootingSound[i].release();
//        for(int i = 0; i < bigFireShootingSound.length; ++i)
//        	bigFireShootingSound[i].release();
//        for(int i = 0; i < bigWaterShootingSound.length; ++i)
//        	bigWaterShootingSound[i].release();
//        for(int i = 0; i < bigGreenShootingSound.length; ++i)
//        	bigGreenShootingSound[i].release();
//        
//        for(int i = 0; i < monsterDamageFire.length; ++i)
//        	monsterDamageFire[i].release();
//        for(int i = 0; i < monsterDamageGreen.length; ++i)
//        	monsterDamageGreen[i].release();
//        for(int i = 0; i < monsterDamageWater.length; ++i)
//        	monsterDamageWater[i].release();
//        for(int i = 0; i < monsterDamageBigFire.length; ++i)
//        	monsterDamageBigFire[i].release();
//        for(int i = 0; i < monsterDamageBigGreen.length; ++i)
//        	monsterDamageBigGreen[i].release();
//        for(int i = 0; i < monsterDamageBigWater.length; ++i)
//        	monsterDamageBigWater[i].release();
//        
//        fireShootingSound = null;
//        waterShootingSound = null;
//        greenShootingSound = null;
//        bigFireShootingSound = null;
//        bigWaterShootingSound = null;
//        bigGreenShootingSound = null;
//        
//        monsterDamageFire = null;
//        monsterDamageBigFire = null;
//        monsterDamageGreen = null;
//        monsterDamageBigGreen = null;
//        monsterDamageWater = null;
//        monsterDamageBigWater = null;
//        
//        gainPowerSound.release();
//        
//        for(int i = 0; i < explosionSound.length; ++i)
//        	explosionSound[i].release();
//        
//        for(int i = 0; i < thunderSound.length; ++i)
//        	thunderSound[i].release();
//        thunderSound = null;
//        
//        fireShootingSound = null;
//        gainPowerSound = null;
//        explosionSound = null;
//        thunderSound = null;        
    }
    
	public static void init(Context c, Resources res, int screenWidth, int screenHeight) {
		music = MediaPlayer.create(c, R.raw.main_menu_music);
		music.setLooping(true);
		music.start();
		
		click = MediaPlayer.create(c, R.raw.button_click);
		
		G.screenWidth = screenWidth;
		G.screenHeight = screenHeight;
		context = c;
		timerTick = 20;
		
		blackImg = BitmapFactory.decodeResource(res, R.drawable.black);
		semaphore = new Semaphore(1);
		ColorMatrix cm = new ColorMatrix();
		ColorMatrix cma = new ColorMatrix();
		cma.setSaturation(0);
        cm.setScale(0.72f, 0.80f, 1.15f, 1f);
        cm.setConcat(cm, cma);
        
        cmcf = new ColorMatrixColorFilter(cm);
	}

	// Finalization-------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	public static void finalizes() {
		if (music != null) {
			if (music.isPlaying()) {
				music.stop();
			}
			music.release();
			music = null;
		}
		
		if (click != null) {
			if (click.isPlaying()) {
				click.stop();
			}
			click.release();
			click = null;
		}
	}

	// More supporting functions-------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------
	public static void clickSoundBtn(View v, int on_id, int off_id) {				
		if (music.isPlaying()) {
			music.pause();			
			music.seekTo(0);
			v.setBackgroundResource(off_id);
		}
		else {
			music.start();
			v.setBackgroundResource(on_id);
		}
	}
	
	public static void updateSoundBtnRes(View v, int on_id, int off_id) {
		int id = music.isPlaying() ? on_id : off_id;
		v.setBackgroundResource(id);
	}
	
	public static String ReadFile(String filename)
	{
        String ret = "";
        
        try {
            FileInputStream inputStream = new FileInputStream(filename);
             
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                 
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                 
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(MainActivity.class.getName(), "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(MainActivity.class.getName(), "Can not read file: " + e.toString());
        }
 
        return ret;
	}
	
	public static void WriteFile(String filename, String text)
	{
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filename));
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(MainActivity.class.getName(), "File write failed: " + e.toString());
        }
	}
}
