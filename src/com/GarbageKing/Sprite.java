package com.GarbageKing;

import android.graphics.*;

public class Sprite {
	public Bitmap texture;
	public int numOfFrames;
	public int index;
	public Rect srcRect;
	public Rect desRect;
	public int srcWidth;
	public int desWidth, desHeight;
	public Paint paint;
	private Matrix m;
	
	public Sprite(Bitmap texture, int numOfFrames, int width, int height)
    {
        this.texture = texture;
        this.numOfFrames = numOfFrames;
        this.index = 0;
        srcRect = new Rect(0, 0, 0, texture.getHeight());
        srcWidth = texture.getWidth() / numOfFrames;
        desRect = new Rect();
        desWidth = width;
        desHeight = height;
        paint = new Paint();
        m = new Matrix();
    }
	
	public void Show(Canvas canvas, int x, int y)
    {
		
        srcRect.left = index * srcWidth;
        srcRect.right = srcRect.left + srcWidth;
        desRect.left = x;
        desRect.top = y;
        desRect.right = x + desWidth;
        desRect.bottom = y + desHeight;

        canvas.drawBitmap(texture, srcRect, desRect, paint);
    }
	
	public void Show(Canvas canvas, int x, int y, float rotate)
    {
		srcRect.left = index * srcWidth;
        srcRect.right = srcRect.left + srcWidth;
        desRect.left = x;
        desRect.top = y;
        desRect.right = x + desWidth;
        desRect.bottom = y + desHeight;
//        m.preScale(0.5f, 0.5f);
        m.postRotate(rotate, x, y);
//        Bitmap rotatedBitmap = Bitmap.createBitmap(texture, srcRect.left, srcRect.top, srcWidth, srcRect.bottom, m, false);
        
        canvas.setMatrix(m);
        canvas.drawBitmap(texture, srcRect, desRect, paint);
        canvas.setMatrix(null);
        
        m.reset();
    }	
	
    public void NextFrame()
    {
        index = (index + 1) % numOfFrames;
    }

    public void FirstFrame()
    {
        index = 0;
    }
    
    public void ShowFlip(Canvas cv, int x, int y)
    {
    	srcRect.left = index * srcWidth;
        srcRect.right = srcRect.left + srcWidth;
        desRect.left = x;
        desRect.top = y;
        desRect.right = x + desWidth;
        desRect.bottom = y + desHeight;
        
        Matrix m = new Matrix();
        m.setScale(-1, 1);
 //       m.postTranslate(desWidth, 0);
        Bitmap flippedBitmap = Bitmap.createBitmap(texture, srcRect.left, srcRect.top, srcWidth, srcRect.height(), m, false);
        
        Rect src = new Rect(0, 0, flippedBitmap.getWidth(), flippedBitmap.getHeight());
        
        cv.drawBitmap(flippedBitmap, src, desRect, paint);
        m.reset();
    }
    
    public void ShowEffected(Canvas cv, int x, int y)
    {
        srcRect.left = index * srcWidth;
        srcRect.right = srcRect.left + srcWidth;
        desRect.left = x;
        desRect.top = y;
        desRect.right = x + desWidth;
        desRect.bottom = y + desHeight;
        //paint.setColorFilter(new android.graphics.);\
        
        paint.setColorFilter(G.cmcf);
        cv.drawBitmap(texture, srcRect, desRect, paint);
        
    }
}
