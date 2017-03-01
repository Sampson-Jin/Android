package com.example.pool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private SoundPool soundPool;
	private int id;
	private Button bt_play;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		soundPool=new SoundPool(4, AudioManager.STREAM_MUSIC, 5);
		id=soundPool.load(this, R.raw.hongri1, 1);
		bt_play=(Button) findViewById(R.id.bt_play);
		bt_play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundPool.play(id, 1.0f, 1.0f, 0, 0, 2.0f);
				
			}
		});
	}
}
