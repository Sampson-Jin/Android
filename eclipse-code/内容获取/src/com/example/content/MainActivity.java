package com.example.content;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView) findViewById(R.id.tv_xianshi);
		findViewById(R.id.read).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentResolver contentResolver=getContentResolver();
				Uri uri=Uri.parse("content://com.jin.yong/yang");
				Cursor cursor=contentResolver.query(uri, new String[]{"name","phone"}, "name= ?", new String[]{"½ð8"},null );
				while(cursor.moveToNext()){
					textView.setText(cursor.getString(0)+":"+cursor.getString(1));
				}
			}
		});
	}
}
