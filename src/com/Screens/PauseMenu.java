package com.Screens;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

import com.GarbageKing.G;
import com.GarbageKing.MainActivity;
import com.GarbageKing.R;
import com.InGame.InGame;

public class PauseMenu {
	
	private View bk, metalDisc;
	private ImageButton resumeBtn, restartBtn, homeBtn, soundBtn;
	
	public PauseMenu(Context context, InGame inGame) {
		
		bk = new View(context);
		bk.setBackgroundResource(R.drawable.black);
		bk.layout(0, 0, G.screenWidth, G.screenHeight);
		bk.getBackground().setAlpha(120);
		bk.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return true;
			}
		});
		inGame.addView(bk);
		
		int h_half = G.screenHeight/2;
		int w_half = G.screenWidth/2;
		metalDisc = new View(context);
		metalDisc.setBackgroundResource(R.drawable.metal_disc);
		int half = 385/2;
		metalDisc.layout(w_half - half, h_half - half, w_half + half, h_half + half);
		inGame.addView(metalDisc);
		
		half = 80;
		int doubleHalf = 2*half;
		resumeBtn = new ImageButton(context);
		resumeBtn.setBackgroundResource(R.drawable.play);
		resumeBtn.layout(G.screenWidth - doubleHalf, h_half - half, G.screenWidth, h_half + half);
		resumeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				G.click.start();
				((MainActivity)(G.context)).mainClass.inGame.freeze = false;
				((MainActivity)(G.context)).mainClass.inGame.postInvalidate();
				setInvisible();
			}
		});
		inGame.addView(resumeBtn);
		
		restartBtn = new ImageButton(context);
		restartBtn.setBackgroundResource(R.drawable.restart);
		restartBtn.layout(0, h_half - half, doubleHalf, h_half + half);
		inGame.addView(restartBtn);
		
		int shOverTen = G.screenHeight/10;
		homeBtn = new ImageButton(context);
		homeBtn.setBackgroundResource(R.drawable.home);
		homeBtn.layout(w_half - half, 7*shOverTen - 5 - half, w_half + half, 7*shOverTen - 5 + half);
		homeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				setInvisible();
				G.releaseGame();
//				((MainActivity)G.context).mainClass.inGame.ReleaseGameEngine();
				((MainActivity)G.context).mainClass.inGame = null;
				((MainActivity)G.context).mainClass.invoke(MainClass.MAIN_MENU);
			}
		});
		inGame.addView(homeBtn);
		
		soundBtn = new ImageButton(context);
		soundBtn.setBackgroundResource(R.drawable.sound1_on);
		soundBtn.layout(w_half - half, 3*shOverTen + 5 - half, w_half + half, 3*shOverTen + 5 + half);
		soundBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				G.clickSoundBtn(soundBtn, R.drawable.sound1_on, R.drawable.sound1_off);
			}
		});
		inGame.addView(soundBtn);
		
		setInvisible();
	}

	private void setInvisible() {
		bk.setVisibility(View.INVISIBLE);
		metalDisc.setVisibility(View.INVISIBLE);
		resumeBtn.setVisibility(ImageButton.INVISIBLE);
		restartBtn.setVisibility(ImageButton.INVISIBLE);
		homeBtn.setVisibility(ImageButton.INVISIBLE);
		soundBtn.setVisibility(ImageButton.INVISIBLE);
	}
	
	public void setVisible() {
		bk.setVisibility(View.VISIBLE);
		metalDisc.setVisibility(View.VISIBLE);
		resumeBtn.setVisibility(ImageButton.VISIBLE);
		restartBtn.setVisibility(ImageButton.VISIBLE);
		homeBtn.setVisibility(ImageButton.VISIBLE);
		soundBtn.setVisibility(ImageButton.VISIBLE);
		G.updateSoundBtnRes(soundBtn, R.drawable.sound1_on, R.drawable.sound1_off);
	}
	
}
