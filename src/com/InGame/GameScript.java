package com.InGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.GarbageKing.G;
import com.Object.Wave;

public class GameScript {
	public int _curWave;
    public Wave[] _waveList;
    private Paint _paint;
    
    public GameScript(Wave[] list)
    {
        _waveList = list;
        _curWave = 0;
        _paint = new Paint();
        _paint.setColor(Color.BLACK);
        _paint.setTextSize(27);
        _paint.setTypeface(Typeface.create("Helvetica", Typeface.BOLD));
    }

    public boolean IsEndOfWaveList()
    {
        if (_curWave == _waveList.length - 1 && _waveList[_curWave].IsEndOfWave())
            return true;

        return false;    
    }
    
    public void Show(Canvas canvas)
    {
    	canvas.drawText("Wave " + String.valueOf(_curWave + 1) + " / " + String.valueOf(_waveList.length), G.screenWidth/2 - 60, 40, _paint);
    }
}
