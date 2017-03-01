package com.example.hellondk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView;
	static{
		System.loadLibrary("hello");
	}

	public static native String GetStringFromC();
	public static native void updateFile(String Path);
	public static native int[] updateIntArray(int[] data);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView=(TextView) findViewById(R.id.textView1);
		textView.setText(GetStringFromC());
		//updateFile("/mnt/sdcard/boys.txt");
		int data[]={1,2,3,4,5};
		int result[]=updateIntArray(data);
		for(int i=0;i<result.length;i++){
			int q=result[i];
			Log.i("123", q+"");
		}
	}


}
