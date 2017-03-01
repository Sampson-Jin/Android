package com.example.contentprivoder;

import com.example.dao.DBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button =(Button) findViewById(R.id.add);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DBHelper dbHelper=new DBHelper(MainActivity.this);
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				for(int i=1;i<=100;i++){
					ContentValues values=new ContentValues();
					values.put("name", "金"+i);
					values.put("phone", ""+i);
					database.insert("persioninfo", null, values);
				}			
				database.close();
				Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
