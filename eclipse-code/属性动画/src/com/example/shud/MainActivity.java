package com.example.shud;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button bt_bu;
	private Button bt_shu;
	private ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_bu=(Button) findViewById(R.id.button1);
		bt_shu=(Button) findViewById(R.id.button2);
		imageView=(ImageView) findViewById(R.id.imageView1);
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "点到我了", Toast.LENGTH_SHORT).show();
			}
		});
		bt_bu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TranslateAnimation ta =new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 1.0f, 
						Animation.RELATIVE_TO_PARENT, 0.0f,
						Animation.RELATIVE_TO_PARENT, 1.0f);
				ta.setDuration(2000);
				ta.setRepeatCount(4);
				ta.setRepeatMode(Animation.REVERSE);
				imageView.startAnimation(ta);
			}
		});
		bt_shu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				imageView.set
				ObjectAnimator animator=ObjectAnimator.ofFloat(imageView, "translationX", 10.0f ,20.0f,50.0f,80.0f,150.0f ,250.0f );
//				ObjectAnimator animator2=ObjectAnimator.ofFloat(imageView, "alpha", 0.0f ,0.2f,0.3f,0.5f,0.8f ,1.0f );
//				ObjectAnimator animator3=ObjectAnimator.ofFloat(imageView, "rotattion", 10.0f ,30.0f,90.0f,180.0f,270.0f ,360.0f );
//				ObjectAnimator animator4=ObjectAnimator.ofFloat(imageView, "scaleType", 0.0f ,0.2f,0.3f,0.5f,0.8f ,1.0f );
				animator.setDuration(2000);
//				animator.setRepeatCount(4);
				animator.setRepeatCount(ObjectAnimator.INFINITE);
				animator.setRepeatMode(Animation.RESTART);			
				animator.start();
				//采用set
				AnimatorSet animatorSet=new AnimatorSet();
				//顺序播放
//				animatorSet.playSequentially(animator,animator2);
				//一起播放
//				animatorSet.playTogether(items);
//				animatorSet.start();
			}
		});
	}
}
