package com.Screens;

import com.GarbageKing.MainActivity;
import com.GarbageKing.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

@SuppressLint("ViewConstructor")
public class SplashScreen extends View {

	private Bitmap layer1, layer2;
	private Rect srcRect, desRect, srcRect0, desRect0;
	private Paint paint, paint0;
	private int alpha;
	public MainActivity mainActivity;
	private boolean flag;
	
	public SplashScreen(Context context, int sWidth, int sHeight) {
		super(context);
		layer1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_screen);
		layer2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.main_menu);
		srcRect = new Rect(0, 0, layer2.getWidth(), layer2.getHeight());
		desRect = new Rect(0, 0, sWidth, sHeight);
		srcRect0 = new Rect(0, 0, layer1.getWidth(), layer1.getHeight());
		desRect0 = new Rect(0, 0, sWidth, sHeight);
		paint = new Paint();
		paint0 = new Paint();
		mainActivity = (MainActivity)context;
		alpha = 255;
		flag = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(layer1, srcRect0, desRect0, paint0);
		if (alpha >= 0)
			canvas.drawBitmap(layer2, srcRect, desRect, paint);
		
		desRect.left -= 25;
		desRect.top -= 45;
		desRect.right += 25;
		desRect.bottom += 45;
		alpha -= 15;
		paint.setAlpha(alpha);	
		
		if (alpha < -15) {
			mainActivity.onFinishSplashScreen();
			return;
		}
		
		if (flag) {
			invalidate();
		}	
	}
	
	public void animateIt() {
		flag = true;
		invalidate();
	}

	public void release() {
		if (layer1 != null) {
			layer1.recycle();
			layer1 = null;
		}
		
		if (layer2 != null) {
			layer2.recycle();
			layer2 = null;
		}
	}
}
