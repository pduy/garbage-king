package com.Screens;

import com.GarbageKing.G;
import com.GarbageKing.MainActivity;
import com.GarbageKing.R;
import com.InGame.InGame;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

public class EndScreen {
	private View shadowBackground;
	private View notification;
	private ImageButton back;
	
	public EndScreen(InGame parent) {
		shadowBackground = new View(G.context);
		shadowBackground.setBackgroundResource(R.drawable.black);
		shadowBackground.getBackground().setAlpha(150);
		shadowBackground.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		shadowBackground.layout(0, 0, G.screenWidth, G.screenHeight);
		shadowBackground.setVisibility(View.INVISIBLE);
		parent.addView(shadowBackground);
		
		notification = new View(G.context);
		notification.layout(40, G.screenHeight / 4, G.screenWidth - 40, 3 * G.screenHeight / 4);
		notification.setVisibility(View.INVISIBLE);
		parent.addView(notification);
		
		back = new ImageButton(G.context);
		back.setBackgroundResource(R.drawable.back);
		back.layout(0, G.screenHeight - 110, 110, G.screenHeight);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				G.releaseGame();
//				((MainActivity)(G.context)).mainClass.inGame.ReleaseGameEngine();
				((MainActivity)(G.context)).mainClass.inGame = null;
				shadowBackground.setVisibility(View.INVISIBLE);
				notification.setVisibility(View.INVISIBLE);
				back.setVisibility(ImageButton.INVISIBLE);
				((MainActivity)(G.context)).mainClass.invoke(MainClass.MAP);
			}
		});
		back.setVisibility(ImageButton.INVISIBLE);
		parent.addView(back);
	}

	public void GameOver(boolean isWin) {
		// TODO Auto-generated method stub
		shadowBackground.setVisibility(View.VISIBLE);
		notification.setVisibility(View.VISIBLE);
		back.setVisibility(ImageButton.VISIBLE);
		notification.setBackgroundResource(isWin ? R.drawable.you_win : R.drawable.you_lose);
	}
}