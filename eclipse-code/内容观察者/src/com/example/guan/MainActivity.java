package com.example.guan;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ContentResolver contentResolver= getContentResolver();
		Uri uri=Uri.parse("content://sms/");
		contentResolver.registerContentObserver(uri, true, new MyContentObserver(new Handler()));
	}
	private class MyContentObserver extends ContentObserver{

		public MyContentObserver(Handler handler) {
			super(handler);
		}

		@Override
		public boolean deliverSelfNotifications() {
			System.out.println("deliverSelfNotifications");
			return super.deliverSelfNotifications();
		}

		@Override
		public void onChange(boolean selfChange) {
			System.out.println("onChange(boolean selfChange)");
			Uri uri=Uri.parse("content://sms/");
			ContentResolver resolver=getContentResolver();
			Cursor cursor= resolver.query(uri, new String[]{"address","body","date","type"}, null, null, "date desc");
			cursor.moveToFirst();
			System.out.println(cursor.getString(0));
			System.out.println(cursor.getString(1));
			System.out.println(cursor.getString(2));
			System.out.println(cursor.getString(3));
			super.onChange(selfChange);
		}
		
	}
}
