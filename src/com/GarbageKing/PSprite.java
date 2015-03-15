package com.GarbageKing;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PSprite extends Sprite {

	public int x, y;
	
	public PSprite(Bitmap texture, int numOfFrames, int x, int y, int width, int height) {
		super(texture, numOfFrames, width, height);
		this.x = x;
		this.y = y;
	}

	public void Show(Canvas canvas) {
		super.Show(canvas, x, y);
	}
}
