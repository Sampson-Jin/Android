package com.example.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

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
				
				Animation aa=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphaxml);
				imageView.startAnimation(aa);
			}
		});
		bt_scale.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});
		bt_translate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
			}
		});
		bt_rotate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Animation aa=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphaxml);
				imageView.startAnimation(aa);

			}
		});
		bt_set.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});

	}
}
