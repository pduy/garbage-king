package com.InGame;

import com.Object.*;

import android.os.*;
import android.view.*;
import android.view.GestureDetector.*;
import android.view.View.*;

public class InteractionListener implements OnTouchListener{
	MyInGame _inGame;
	public static int LONG_PRESS_TIME = 1000; // Time in miliseconds
	public static float TERMINATE_DISTANCE = 4.0f;
	final Handler _handler = new Handler();
	MotionEvent _saveEvent;
	
	boolean flied;
	
	final GestureDetector gesture = new GestureDetector(new OnGestureListener() {
	
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			
			return _inGame._gameEngine.SingleTapUp(e);
		}
		
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// TODO Auto-generated method stub
			float x = e1.getX(), y = e1.getY();
			if (_inGame._gameEngine._elementList.get(0)._firePanel.IsChangePanel((int)x, (int)y, (int)velocityX, (int)velocityY))
			{
				Element.ChangeElement(_inGame._gameEngine._elementList, (int)velocityX);
				return true;
			}
			
			float xk = e2.getX() - e1.getX(), yk = e2.getY() - e1.getY();
			float dx = xk / (float)Math.sqrt((xk * xk) + (yk * yk));
			float dy = yk / (float)Math.sqrt((xk * xk) + (yk * yk));
			velocityX = dx * (float)Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) / 200;
			velocityY = dy * (float)Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) / 200;
			flied = _inGame._gameEngine.AddUserBullet(x, y, velocityX, velocityY);
			return flied;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub

			return true;
		}
	});
	
	 
	public InteractionListener(MyInGame inGame)
	{
		this._inGame = inGame;
		gesture.setIsLongpressEnabled(false);
	}
	
//	public boolean onTouch(View v, MotionEvent event) {
//	// TODO Auto-generated method stub
//		return gesture.onTouchEvent(event);
//	}

	Runnable _longPressed = new Runnable() { 
	    public void run() {
			_inGame._gameEngine.GainPower(_saveEvent.getX(), _saveEvent.getY());
	    }   
	};
	
	Runnable _releaseLongPress = new Runnable() { 
	    public void run() {
	    	if (_inGame._gameEngine._isHold)
	    		_inGame._gameEngine.StopGainPower();
	    }   
	};

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		boolean consume = gesture.onTouchEvent(event);
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			_saveEvent = event;

			_handler.postDelayed(_longPressed, LONG_PRESS_TIME);
			flied = false;
			break;
			
		case MotionEvent.ACTION_MOVE:
			if ((flied == true) || 
			(Math.abs(event.getX() - _saveEvent.getX()) > TERMINATE_DISTANCE) || (Math.abs(event.getY() - _saveEvent.getY()) > TERMINATE_DISTANCE))
			{
				_handler.removeCallbacks(_longPressed);
				_handler.postDelayed(_releaseLongPress, LONG_PRESS_TIME / 2);
			}
			break;
		case MotionEvent.ACTION_UP:
			_handler.removeCallbacks(_longPressed);
			_handler.postDelayed(_releaseLongPress, LONG_PRESS_TIME / 2);
			break;
		}
		return consume;
	}
}
