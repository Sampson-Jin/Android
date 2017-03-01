package com.example.bigtu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		image=(ImageView) findViewById(R.id.iv_bigtu);
		findViewById(R.id.bt_jiazai).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ͼƬ�ֱ���̫�󣬻�oom
//				Bitmap bitmap=BitmapFactory.decodeFile("mnt/sdcard/Pictures/jin.jpg");
//				image.setImageBitmap(bitmap);
				
				BitmapFactory.Options options=new Options();//����ͼƬ������ѡ��
				options.inJustDecodeBounds=true;//Ϊtrue ����ʵ����bitmap ���ǲ�ѯbitmap���
				BitmapFactory.decodeFile("mnt/sdcard/Pictures/jin.jpg", options);
				
				int tuWidth=options.outWidth;
				int tuHeight=options.outHeight;
				
				WindowManager manager=(WindowManager) getSystemService(WINDOW_SERVICE);
				int screenWidth=manager.getDefaultDisplay().getWidth();
				int screenHeight=manager.getDefaultDisplay().getHeight();
				
				int dx=tuWidth/screenWidth;
				int dy=tuHeight/screenHeight;
				int max=dx;
				if(dx<dy){
					max=dy;
				}
				options.inSampleSize=max;
				options.inJustDecodeBounds=false;
				Bitmap bitmap=BitmapFactory.decodeFile("mnt/sdcard/Pictures/jin.jpg", options);
				image.setImageBitmap(bitmap);
				
			}
		});
	}
}
