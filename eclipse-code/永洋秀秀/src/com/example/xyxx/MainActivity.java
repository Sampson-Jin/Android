package com.example.xyxx;

import com.mt.mtxx.image.JNI;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	static{
		System.loadLibrary("mtimage-jni");
	}
	private Button bt_1;
	private Button bt_2;
	private Button bt_3;
	private Button bt_4;
	private ImageView iv;
	private JNI jni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.bt_one).setOnClickListener(this);
		findViewById(R.id.bt_two).setOnClickListener(this);
		findViewById(R.id.bt_three).setOnClickListener(this);
		findViewById(R.id.bt_four).setOnClickListener(this);
		iv=(ImageView)findViewById(R.id.iv);
		jni=new JNI();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_one:
			getPixels(1);
			break;
		case R.id.bt_two:
			getPixels(2);
			break;
		case R.id.bt_three:
			getPixels(3);
			break;
		case R.id.bt_four:
			getPixels(4);
			break;

		}
	}
	public void getPixels(int id){
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.jin);
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();
		int[] pixels=new int[width*height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
		if(id==1){
			jni.StyleLomoB(pixels, width, height);			
		}else if (id==2) {
			jni.StyleLomoC(pixels, width, height);			
		}else if (id==3) {
			jni.StyleLomoHDR(pixels, width, height);		
		}else if (id==4) {
			jni.StyleElegant(pixels, width, height);		
		}
		Bitmap bitmap2=Bitmap.createBitmap(pixels, width, height, bitmap.getConfig());
		iv.setImageBitmap(bitmap2);
	}
}
