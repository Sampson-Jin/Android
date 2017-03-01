package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button bt_alpha;
	private Button bt_scale;
	private Button bt_translate;
	private Button bt_rotate;
	private Button bt_set;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_alpha=(Button) findViewById(R.id.bt_alpha);
		bt_scale=(Button) findViewById(R.id.bt_scale);
		bt_translate=(Button) findViewById(R.id.bt_translate);
		bt_rotate=(Button) findViewById(R.id.bt_rotate);
		bt_set=(Button) findViewById(R.id.bt_set);
		imageView=(ImageView) findViewById(R.id.imageView1);
		bt_alpha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "qweew", Toast.LENGTH_SHORT).show();
				AlphaAnimation aa=new AlphaAnimation(0, 1);
				aa.setDuration(3000);
				aa.setRepeatCount(3);
				aa.setRepeatMode(Animation.REVERSE);
				imageView.startAnimation(aa);
			}
		});
		bt_scale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "qweew", Toast.LENGTH_SHORT).show();
				//				ScaleAnimation sc =new ScaleAnimation(fromX, toX,
				//						fromY, toY,
				//						pivotXType, pivotXValue, 
				//						pivotYType, pivotYValue)
				ScaleAnimation sa =new ScaleAnimation(0.0f, 2.0f,
						0.0f, 2.0f,
						Animation.RELATIVE_TO_SELF, 0, 
						Animation.RELATIVE_TO_SELF, 0);
				sa.setDuration(3000);
				sa.setRepeatCount(3);
				sa.setRepeatMode(Animation.REVERSE);
				imageView.startAnimation(sa);
			}
		});
		bt_translate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "qweew", Toast.LENGTH_SHORT).show();
//				TranslateAnimation ta =new TranslateAnimation(fromXType, fromXValue,
//						toXType, toXValue, 
//						fromYType, fromYValue, 
//						toYType, toYValue)
				TranslateAnimation ta =new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f, 
						Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f);
				ta.setDuration(3000);
				ta.setRepeatCount(3);
				ta.setRepeatMode(Animation.REVERSE);
				imageView.startAnimation(ta);
				
			}
		});
		bt_rotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "qweew", Toast.LENGTH_SHORT).show();
				RotateAnimation ra =new RotateAnimation(0.0f, 360.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f, 
						Animation.RELATIVE_TO_PARENT, 0.5f);
				ra.setDuration(3000);
				ra.setRepeatCount(3);
				ra.setRepeatMode(Animation.REVERSE);
				imageView.startAnimation(ra);

			}
		});
		bt_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "qweew", Toast.LENGTH_SHORT).show();
				AnimationSet animationSet=new AnimationSet(false);
				ScaleAnimation sa =new ScaleAnimation(0.0f, 2.0f,
						0.0f, 2.0f,
						Animation.RELATIVE_TO_SELF, 0, 
						Animation.RELATIVE_TO_SELF, 0);
				sa.setDuration(3000);
				sa.setRepeatCount(3);
				sa.setRepeatMode(Animation.REVERSE);
				TranslateAnimation ta =new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f, 
						Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f);
				ta.setDuration(3000);
				ta.setRepeatCount(3);
				ta.setRepeatMode(Animation.REVERSE);
				RotateAnimation ra =new RotateAnimation(0.0f, 360.0f,
						Animation.RELATIVE_TO_PARENT, 0.5f, 
						Animation.RELATIVE_TO_PARENT, 0.5f);
				ra.setDuration(3000);
				ra.setRepeatCount(3);
				ra.setRepeatMode(Animation.REVERSE);
				animationSet.addAnimation(sa);
				animationSet.addAnimation(ta);
				animationSet.addAnimation(ra);
				imageView.startAnimation(animationSet);
			}
		});

	}
}
