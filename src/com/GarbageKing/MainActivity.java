package com.GarbageKing;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.Screens.MainClass;
import com.Screens.SplashScreen;

public class MainActivity extends Activity {

	public MainClass mainClass;
	private SplashScreen splashScreen;
	public static MainActivity pt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Display d = getWindowManager().getDefaultDisplay();
		splashScreen = new SplashScreen(this, d.getWidth(), d.getHeight());
		setContentView(splashScreen);
		pt = this;
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				pt.prepare();
			}
		}, 200);
	}
	
	public void prepare() {
		Display d = getWindowManager().getDefaultDisplay();
		G.init(this, getResources(), d.getWidth(), d.getHeight());
//		Toast.makeText(this, "Init complete", Toast.LENGTH_LONG).show();
		mainClass = new MainClass(this);
		splashScreen.animateIt();
	}
		
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		mainClass.keyUp(keyCode, event);
		return super.onKeyUp(keyCode, event);
	}
	
	public void onFinishSplashScreen() {
		mainClass.draw();	
		if (splashScreen != null) {
			splashScreen.release();
			splashScreen = null;
		}			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startGameBtnClick(View v) {
		G.click.start();
		mainClass.invoke(MainClass.IN_GAME);
	}
	
	public void loadGameBtnClick(View v) {
		G.click.start();
		mainClass.invoke(MainClass.MAP);
	}
	
	public void soundBtnClick(View v) {
		G.click.start();
		G.clickSoundBtn(v, R.drawable.sound_on, R.drawable.sound_off);
	}
	
	public void aboutUsBtnClick(View v) {
		G.click.start();
		mainClass.invoke(MainClass.ABOUT_US);
	}
	
	public void backBtnClick(View v) {
		G.click.start();
		mainClass.invoke(MainClass.MAIN_MENU);
	}	
	
	public void helpBtnClick(View v) {
		Toast.makeText(this, "How to play ?", Toast.LENGTH_SHORT).show();
	}
	
	public void exitBtnClick(View v) {
		G.click.start();
		System.runFinalizersOnExit(true);
		System.exit(0);
		this.finish();
	}
	
	@Override
	protected void onDestroy() {
		G.finalizes();
		super.onDestroy();
	}
}