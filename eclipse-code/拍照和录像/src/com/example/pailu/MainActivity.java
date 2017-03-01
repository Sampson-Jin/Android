package com.example.pailu;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button bt_pai;
	private Button bt_lu;
	private File tu;
	private File shipin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_pai=(Button) findViewById(R.id.bt_pai);
		bt_lu=(Button) findViewById(R.id.bt_lu);
		bt_pai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "拍照", 1).show();
				Intent intent=new Intent();
				intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
				tu=new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tu));
				startActivityForResult(intent, 0);
			}
		});
		bt_lu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "视频", 1).show();
				Intent intent=new Intent();
				intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
				shipin=new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".3gp");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(shipin));
				startActivityForResult(intent, 1);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Log.i("tuqqqqqqq", tu.getAbsolutePath());
		super.onActivityResult(requestCode, resultCode, data);
	}
}
