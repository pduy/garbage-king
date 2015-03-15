package com.Object;

import android.graphics.*;
import com.InGame.*; 
import com.GarbageKing.*;

public class FirePanel extends GameObject{
     public static int _DEFAULT_LOCATION_X = 0;
     public static int _DEFAULT_LOCATION_Y = 7 * G.screenHeight / 8;
     public static int _DEFAULT_CHANGE_PANEL_DX = 3;
     public static int _DEFAULT_CHANGE_PANEL_DY = 6;

     public FirePanel(float x, float y, float speed, int updatePeriod, Sprite living, Sprite dying, double totalMiliseconds) 
     {
    	 super(x, y, speed, updatePeriod, living, dying);
         this._livingSprite.desWidth = G.screenWidth;
         this._livingSprite.desHeight = G.screenHeight / 8;

     }

     public void Fire()
     {

     }

     public boolean IsChangePanel(int x, int y, int dx, int dy)
     {
         float _dy = dy/200;
         float _dx = dx /200;
         if (y > _DEFAULT_LOCATION_Y)
         {
             if (Math.abs(_dx) > _DEFAULT_CHANGE_PANEL_DX && Math.abs(_dy) < _DEFAULT_CHANGE_PANEL_DY)
                 return true;
         }
         return false;
     }

     public FirePanel Clone(float x, float y, double totalMiliseconds)
     {
         return new FirePanel(x, y, _speed, _updatePeriod, _livingSprite, _dyingSprite, totalMiliseconds);
     }

     public static int CenterX()
     {
         return _DEFAULT_LOCATION_X + G.screenWidth/ 2;
     }

     public static int CenterY()
     {
         return _DEFAULT_LOCATION_Y + G.screenHeight / 16;
     }

     public static Point Center()
     {
         return new Point(CenterX(), CenterY());
     }
}
