package com.example.jniyuan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	static{
		System.loadLibrary("jniutils");
	}

	private Button bt_one;
	private EditText ed_inp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_one=(Button) findViewById(R.id.button1);
		ed_inp=(EditText)findViewById(R.id.editText1);
	}
	public void onDianji(View view){
		Toast.makeText(getApplicationContext(), setMi("abc", 3), Toast.LENGTH_LONG).show();
		int sum=add(2, 3);
		int[] arr={1,2,3};
		asd(arr);
		ed_inp.setText(sum+":"+arr[1]);
	}
	public native String setMi(String str,int len);
	public native int add(int a,int b);
	public native void asd(int[] as);
}
