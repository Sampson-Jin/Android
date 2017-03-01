package com.example.bs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv_tu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_tu=(ImageView) findViewById(R.id.iv_tu);
		findViewById(R.id.bt_big).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toBig();
			}
		});
		findViewById(R.id.bt_smail).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toSmail();
			}
		});
	}
	private void toBig(){
		Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.jin);
		Matrix matrix=new Matrix();
		matrix.setScale(2, 2);
		Bitmap bitmap2=Bitmap.createBitmap(bitmap.getWidth()*2, bitmap.getWidth()*2, bitmap.getConfig());
		
		Canvas canvas=new Canvas(bitmap2);
		
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		
		canvas.drawBitmap(bitmap, matrix, paint);	
		iv_tu.setImageBitmap(bitmap2);
	}
	private void toSmail(){
		Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.jin);
		Matrix matrix=new Matrix();
		matrix.setScale(0.5f, 0.5f);
		Bitmap bitmap2=Bitmap.createBitmap(bitmap.getWidth()/2, bitmap.getWidth()/2, bitmap.getConfig());
		
		Canvas canvas=new Canvas(bitmap2);
		
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		
		canvas.drawBitmap(bitmap, matrix, paint);	
		iv_tu.setImageBitmap(bitmap2);
	}
}
