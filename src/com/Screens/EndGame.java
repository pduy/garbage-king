package com.Screens;

import com.GarbageKing.G;
import com.GarbageKing.MainActivity;
import com.GarbageKing.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageButton;

public class EndGame extends View {
	public ImageButton _back;
	private boolean _state;
	private Bitmap _background;
	private Paint _paint;
	private Rect _srcRect, _desRect;
	
	public EndGame (Context context) {
		super(context);
		_paint = new Paint();
		_srcRect = new Rect(0, 0, G.blackImg.getWidth(), G.blackImg.getHeight());
		_desRect = new Rect(0, 0, G.screenWidth, G.screenHeight);
		_back = new ImageButton(context);
		_back.setBackgroundResource(R.drawable.back);
		_back.layout(0, G.screenHeight - 110, 110, G.screenHeight);
		_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				((MainActivity)G.context).mainClass.invoke(MainClass.MAP);
			}
		});
		_back.setVisibility(VISIBLE);
	}
	
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(_background, 0, 0, _paint);
		
		_paint.setAlpha(150);
		canvas.drawBitmap(G.blackImg, _srcRect, _desRect, _paint);
		_paint.reset();
		
		if(_state)
			canvas.drawBitmap(G.winTexture, (G.screenWidth - G.winTexture.getWidth()) / 2, (G.screenHeight - G.winTexture.getHeight()) / 2, _paint);
		else
			canvas.drawBitmap(G.loseTexture, (G.screenWidth - G.loseTexture.getWidth()) / 2, (G.screenHeight - G.loseTexture.getHeight()) / 2, _paint);
	}
	
	public void SetState(boolean isWin, Bitmap background) {
		_state = isWin;
		_background = background;
	} 
}
