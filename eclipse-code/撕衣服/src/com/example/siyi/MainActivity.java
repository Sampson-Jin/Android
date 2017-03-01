package com.example.siyi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity implements OnTouchListener {

	private ImageView iv_one;
	private int startx;
	private int starty;
	private Bitmap bitmap2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_one=(ImageView) findViewById(R.id.iv_one);
		iv_one.setOnTouchListener(this);
		Bitmap bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.one);
		bitmap2=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas=new Canvas(bitmap2);
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		try {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startx=(int) event.getX();
				starty=(int) event.getY();
				bitmap2.setPixel(startx, starty,Color.TRANSPARENT);
				iv_one.setImageBitmap(bitmap2);
				break;
			case MotionEvent.ACTION_MOVE:
				int newx=(int) event.getX();
				int newy=(int) event.getY();
				for(int x=-3;x<4;x++){
					for(int y=-3;y<4;y++){
						if(Math.sqrt(x*x+y*y)<=3){							
						bitmap2.setPixel(newx+x, newy+y,Color.TRANSPARENT);
						}
					}
				}
				
				iv_one.setImageBitmap(bitmap2);
				break;
			case MotionEvent.ACTION_UP:

				break;

			}
		} catch (Exception e) {
		}
		return true;
	}
}
