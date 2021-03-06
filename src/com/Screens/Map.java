package com.Screens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.GarbageKing.G;
import com.GarbageKing.MainActivity;
import com.GarbageKing.R;
import com.Object.Player;

class InnerMap extends View {

	private Paint paint;
	private int x, y;
	private int xdiff, ydiff;
	private int xMin, yMin;
	private Map parent;
	public Bitmap map;

	public InnerMap(Context context, Map parent) {
		super(context);
		paint = new Paint();
		x = -83;
		y = -256;		
		this.parent = parent;		

	}

	public void onEnter() {
		map = BitmapFactory.decodeResource(getResources(), R.drawable.map);
		xMin = -map.getWidth() + G.screenWidth;
		yMin = -map.getHeight() + G.screenHeight;
	}
	
	public void onExit() {
		if (map != null) {
			map.recycle();
			map = null;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(map, x, y, paint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
		
		switch (event.getAction()) {		
		case MotionEvent.ACTION_DOWN:
			xdiff = this.x - x;
			ydiff = this.y - y;
			break;
		case MotionEvent.ACTION_MOVE:
			this.x = x + xdiff;
			this.y = y + ydiff;
			
			if (this.x > 0) this.x = 0;
			if (this.y > 0) this.y = 0;
			if (this.x < xMin) this.x = xMin;
			if (this.y < yMin) this.y = yMin;
			
			invalidate();
			parent.updateAllLevelBtns(this.x, this.y);
			break;
		}

		return true;
	}
}

class Store extends View {

	private Paint paint, textPaint, bkPaint;
	private Rect srcRect, desRect;
	private Rect srcRect1, desRect1;
	private Rect srcRect2, desRect2;
	private int xdiff, xMin;
	private boolean chooseSkill;
	private Map parent;
	private int textHeight;
	public int swordWidth, swordHeight;
	public int twoS, threeS, fourS;
	public boolean animation;
	private Bitmap sword, pyramid;
	
	public Store(Context context, Map parent) {
		super(context);
		this.parent = parent;
		paint = new Paint();
		bkPaint = new Paint();
		bkPaint.setAlpha(180);
		textPaint = new Paint();
		textPaint.setColor(Color.YELLOW); 
		textPaint.setTextSize(25);
		textPaint.setTypeface(Typeface.create("SERIF", Typeface.BOLD));
		srcRect = new Rect(0, 0, G.blackImg.getWidth(), G.blackImg.getHeight());
		desRect = new Rect(0, 0, G.screenWidth, G.screenHeight);
//		srcRect1 = new Rect(0, 0, G.pyramid.getWidth(), G.pyramid.getHeight());
		desRect1 = new Rect(G.screenWidth / 2, G.screenHeight/4, G.screenWidth / 2, 3*G.screenHeight/4);
//		srcRect2 = new Rect(0, 0, G.sword.getWidth(), G.sword.getHeight());
		swordHeight = 31*G.screenHeight/210;
		swordWidth = (int)(6.55 * swordHeight);
		twoS = swordHeight + swordHeight;
		threeS = twoS + swordHeight;
		fourS = threeS + swordHeight;
		desRect2 = new Rect(0, 0, swordWidth, swordHeight);
		xdiff = 0;
		xMin = -swordWidth + G.screenWidth;
		chooseSkill = false;
		textHeight = swordHeight + 24;
		animation = true;
		flag = false;
	}
	
	private boolean flag;
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (animation) {
			canvas.drawBitmap(G.blackImg, srcRect, desRect, bkPaint);
			canvas.drawBitmap(pyramid, srcRect1, desRect1, paint);
			desRect1.left -= 9;
			desRect1.right += 9;			
			if (desRect1.left <= 0) 
			{
				animation = false;
				flag = true;
//				desRect1.left = 0;
//				desRect1.right = G.screenWidth;
			}			
			invalidate(desRect1);
			return;
		}
		
		canvas.drawBitmap(G.blackImg, srcRect, desRect, bkPaint);
		canvas.drawBitmap(sword, srcRect2, desRect2, paint);
		canvas.drawBitmap(pyramid, srcRect1, desRect1, paint);	

		canvas.drawText("100", desRect2.left + 268, textHeight, textPaint); 
		canvas.drawText("1200", desRect2.left + 288 + swordHeight, textHeight, textPaint);
		canvas.drawText("500", desRect2.left + 308 + twoS, textHeight, textPaint);
		canvas.drawText("1000", desRect2.left + 328 + threeS, textHeight, textPaint);
		canvas.drawText("You have 2000 coins", 118, 754, textPaint);
		canvas.drawText("in your pocket", 119, 788, textPaint);
		
		if (flag) {
			parent.updateSomething();
			flag = false;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int)event.getX();
		int y = (int)event.getY();
				
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (y <= swordHeight) {
				chooseSkill = true;
				xdiff = desRect2.left - x;
			}			
			break;
		case MotionEvent.ACTION_MOVE:
			if (chooseSkill) {
				desRect2.left = x + xdiff;
				desRect2.right = desRect2.left + swordWidth;
				
				if (desRect2.left > 0) {
					desRect2.left = 0;
					desRect2.right = swordWidth;
				}
				if (desRect2.left < xMin) {
					desRect2.left = xMin;
					desRect2.right = desRect2.left + swordWidth;
				}
				invalidate(0, 0, G.screenWidth, textHeight);
				parent.updateAllSkillBtns(desRect2.left);
			}
			break;
		case MotionEvent.ACTION_UP:
			chooseSkill = false;
			break;			
		}
		return true;
	}

	public void onExit() {
		animation = true;
		desRect1.left = desRect1.right = G.screenWidth/2;
	}

	public void onEnterMap() {
		sword = BitmapFactory.decodeResource(getResources(), R.drawable.long_sword);
		pyramid = BitmapFactory.decodeResource(getResources(), R.drawable.pyramid);
		srcRect1 = new Rect(0, 0, pyramid.getWidth(), pyramid.getHeight());
		srcRect2 = new Rect(0, 0, sword.getWidth(), sword.getHeight());
	}

	public void onExitMap() {
		sword.recycle();
		sword = null;
		pyramid.recycle();
		pyramid = null;
	}
}

public class Map extends ViewGroup {

	private InnerMap innerMap; 
	private Store store;
	private ImageButton btnLevel_1, btnLevel_2, btnLevel_3;
	private ImageButton btnBackInStore;
	private View skillBtn1, skillBtn2, skillBtn3, skillBtn4;
	private ImageButton ElementBtn1, ElementBtn2, ElementBtn3;
	
	private static final int ELEMENT1 = 0;
	private static final int ELEMENT2 = 1;
	private static final int ELEMENT3 = 2;
	private int element;
	
	public Map(Context context) {
		super(context);
		innerMap = new InnerMap(context, this);
		addView(innerMap);						
		
		btnLevel_1 = new ImageButton(context);		
		btnLevel_1.setBackgroundResource(R.drawable.tick);
		btnLevel_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				onExit();
				((MainActivity)(G.context)).mainClass.invoke(MainClass.IN_GAME);
			}
		});
		addView(btnLevel_1);
		
		btnLevel_2 = new ImageButton(context);		
		btnLevel_2.setBackgroundResource(R.drawable.tick);
		btnLevel_2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				onExit();
				((MainActivity)(G.context)).mainClass.invoke(MainClass.IN_GAME);
			}
		});
		addView(btnLevel_2);
		
		btnLevel_3 = new ImageButton(context);		
		btnLevel_3.setBackgroundResource(R.drawable.tick);
		btnLevel_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				onExit();
				((MainActivity)(G.context)).mainClass.invoke(MainClass.IN_GAME);
			}
		});
		addView(btnLevel_3);
		
		ImageButton btnBack = new ImageButton(context);	
		btnBack.layout(0, G.screenHeight - 110, 110, G.screenHeight);
		btnBack.setBackgroundResource(R.drawable.back);
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				G.click.start();
				onExit();
				((MainActivity)G.context).mainClass.invoke(MainClass.MAIN_MENU);
			}
		});
		addView(btnBack);
		
		ImageButton btnMagic = new ImageButton(context);		
		btnMagic.layout(G.screenWidth - 110, G.screenHeight - 110, G.screenWidth, G.screenHeight);
		btnMagic.setBackgroundResource(R.drawable.skills_btn);
		btnMagic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				store.setVisibility(VISIBLE);								
			}
		});
		addView(btnMagic);
		
		store = new Store(context, this);
		store.setVisibility(INVISIBLE);
		addView(store);
		
		btnBackInStore = new ImageButton(context);	
		btnBackInStore.layout(0, G.screenHeight - 110, 110, G.screenHeight);
		btnBackInStore.setBackgroundResource(R.drawable.back);
		btnBackInStore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				G.click.start();
				store.setVisibility(INVISIBLE);
				store.onExit();
				btnBackInStore.setVisibility(INVISIBLE);
				skillBtn1.setVisibility(INVISIBLE);
				skillBtn2.setVisibility(INVISIBLE);
				skillBtn3.setVisibility(INVISIBLE);
				skillBtn4.setVisibility(INVISIBLE);
				ElementBtn1.setVisibility(INVISIBLE);
				ElementBtn2.setVisibility(INVISIBLE);
				ElementBtn3.setVisibility(INVISIBLE);
			}
		});
		btnBackInStore.setVisibility(INVISIBLE);
		addView(btnBackInStore);
		
		skillBtn1 = new View(context);		
		skillBtn1.setVisibility(INVISIBLE);
		addView(skillBtn1);
		
		skillBtn2 = new View(context);		
		skillBtn2.setVisibility(INVISIBLE);
		addView(skillBtn2);
		
		skillBtn3 = new View(context);		
		skillBtn3.setVisibility(INVISIBLE);
		addView(skillBtn3);
		
		skillBtn4 = new View(context);		
		skillBtn4.setVisibility(INVISIBLE);
		addView(skillBtn4);
		
		ElementBtn1 = new ImageButton(context);		
		ElementBtn1.setBackgroundResource(R.drawable.water);
		ElementBtn1.setVisibility(INVISIBLE);
		ElementBtn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				if (element != ELEMENT1) {
					element = ELEMENT1;
					updateAllSkillBtnsBK();
				}
			}
		});
		addView(ElementBtn1);
		
		ElementBtn2 = new ImageButton(context);		
		ElementBtn2.setBackgroundResource(R.drawable.wood);
		ElementBtn2.setVisibility(INVISIBLE);
		ElementBtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				if (element != ELEMENT2) {
					element = ELEMENT2;
					updateAllSkillBtnsBK();
				}
			}
		});
		addView(ElementBtn2);
		
		ElementBtn3 = new ImageButton(context);		
		ElementBtn3.setBackgroundResource(R.drawable.fire);
		ElementBtn3.setVisibility(INVISIBLE);
		ElementBtn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				G.click.start();
				if (element != ELEMENT3) {
					element = ELEMENT3;
					updateAllSkillBtnsBK();
				}
			}
		});
		addView(ElementBtn3);
		
		updateAllLevelBtns(-83, -256);
		updateAllSkillBtns(0);
		updateAllElementBtns();
		element = ELEMENT3;
		updateAllSkillBtnsBK();
		
		if (G.player == null)
			G.player = new Player();
		G.player.LoadGame();
	}
	
	public void updateSomething() {
		btnBackInStore.setVisibility(VISIBLE);
		skillBtn1.setVisibility(VISIBLE);
		skillBtn2.setVisibility(VISIBLE);
		skillBtn3.setVisibility(VISIBLE);
		skillBtn4.setVisibility(VISIBLE);
		ElementBtn1.setVisibility(VISIBLE);
		ElementBtn2.setVisibility(VISIBLE);
		ElementBtn3.setVisibility(VISIBLE);
	}

	private void updateAllSkillBtnsBK() {
		switch (element) {
		case ELEMENT1:		
			skillBtn1.setBackgroundResource(R.drawable.small_water);
			skillBtn2.setBackgroundResource(R.drawable.thunder_spell);
			skillBtn3.setBackgroundResource(R.drawable.big_water);
			skillBtn4.setBackgroundResource(R.drawable.wave_spell);
			break;
		case ELEMENT2:
			skillBtn1.setBackgroundResource(R.drawable.small_wood);
			skillBtn2.setBackgroundResource(R.drawable.stone_spell);
			skillBtn3.setBackgroundResource(R.drawable.big_wood);
			skillBtn4.setBackgroundResource(R.drawable.broom_spell);
			break;
		case ELEMENT3:
			skillBtn1.setBackgroundResource(R.drawable.small_fire);
			skillBtn2.setBackgroundResource(R.drawable.explosion_spell);
			skillBtn3.setBackgroundResource(R.drawable.big_fire);
			skillBtn4.setBackgroundResource(R.drawable.sword_spell);
			break;
		}
	}

	private void updateAllElementBtns() {	
		ElementBtn3.layout(181, 290, 301, 410);
		ElementBtn1.layout(74, 448, 194, 568);
		ElementBtn2.layout(288, 448, 408, 568);		
	}

	public void updateAllSkillBtns(int x) {
		int fiveS = store.fourS + store.swordHeight;
		skillBtn1.layout(store.twoS + x - 20, 0, store.threeS + x - 20, store.swordHeight);
		skillBtn2.layout(store.threeS + x, 0, store.fourS + x, store.swordHeight);
		skillBtn3.layout(store.fourS + x + 20, 0, fiveS + x + 20, store.swordHeight);
		skillBtn4.layout(fiveS + x + 40, 0, fiveS + store.swordHeight + x + 40, store.swordHeight);
	}

	public void updateAllLevelBtns(int x, int y) {
		btnLevel_1.layout(214 + x, 683 + y, 264 + x, 733 + y);
		btnLevel_2.layout(509 + x, 760 + y, 559 + x, 810 + y);
		btnLevel_3.layout(220 + x, 896 + y, 270 + x, 946 + y);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		innerMap.layout(l, t, r, b);
		store.layout(l, t, r, b);
	}	
	
	public void onEnter() {
		innerMap.onEnter();
		store.onEnterMap();
	}
	
	public void onExit() {
		innerMap.onExit();
		store.onExitMap();
	}
}