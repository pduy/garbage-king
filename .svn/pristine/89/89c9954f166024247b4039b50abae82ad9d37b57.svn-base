package com.Screens;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;

import com.GarbageKing.*;
import com.InGame.*;

public class MainClass {
    
	public static final int MAIN_MENU = 0;
	public static final int IN_GAME = 1;
	public static final int ABOUT_US = 2;
	public static final int MAP = 3;	
	
	public InGame inGame;
	public Map map;	
	public int state;	
	private MainActivity context;
	private View tempView;
	
	public MainClass(Context context) {
		this.context = (MainActivity) context; 
		map = new Map(context);
		state = MAIN_MENU;
		tempView = new View(context);
	}

	public void draw() {
		switch (state) {
		case MAIN_MENU:
			context.setContentView(R.layout.main_menu);
			G.updateSoundBtnRes(context.findViewById(R.id.sound_menu_btn), R.drawable.sound_on, R.drawable.sound_off);
			break;
		case IN_GAME:
			context.setContentView(tempView);
			G.initGame(context.getResources());
//			inGame.start(1);
			inGame = new InGame(context, this);
			context.setContentView(inGame);
			break;
		case ABOUT_US:
			context.setContentView(R.layout.about_us_menu);
			break;		
		case MAP:
			context.setContentView(tempView);
			System.gc();
			map.onEnter();
			context.setContentView(map);
			break;
		default:
			break;
		}
	}

	public void invoke(int state_) {
		this.state = state_;
		draw();
	}

	public void keyUp(int keyCode, KeyEvent event) {
		switch (state) {
		case IN_GAME:
			inGame.keyUp(keyCode, event);
			break;
		}
	}	
}
