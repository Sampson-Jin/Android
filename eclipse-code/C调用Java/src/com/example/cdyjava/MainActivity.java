package com.example.cdyjava;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	static{
		System.loadLibrary("cdyJava");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void btDiaoYong(View view){
		setCToJava();
	}
	public native void setCToJava();
	public void cDiaoJavaShow(String msg){
		Builder builder=new Builder(this);
		builder.setTitle("提示");
		builder.setMessage(msg);
		builder.show();
	}
}
