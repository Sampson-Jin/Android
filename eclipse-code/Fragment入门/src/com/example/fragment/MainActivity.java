package com.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private Button bt_one;
	private Button bt_one2;
	private Button bt_one3;
	private LinearLayout ll_fragment;
	private android.app.FragmentManager fragmentManger;
	private Fragment one;
	private One2 one2;
	private One3 one3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_one=(Button) findViewById(R.id.bt_one);
		bt_one2=(Button) findViewById(R.id.bt_two);
		bt_one3=(Button) findViewById(R.id.bt_three);
		ll_fragment=(LinearLayout) findViewById(R.id.ll_fragment);
		fragmentManger=getFragmentManager();
		one=new One();
		one2=new One2();
		one3=new One3();
		FragmentTransaction transaction=fragmentManger.beginTransaction();
		transaction.replace(R.id.ll_fragment, one, "one");
		transaction.commit();
		bt_one.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction=fragmentManger.beginTransaction();
				transaction.replace(R.id.ll_fragment, one, "one");
				transaction.commit();
			}
		});
		bt_one2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction=fragmentManger.beginTransaction();
				transaction.replace(R.id.ll_fragment, one2, "one2");
				transaction.commit();
			}
		});
		bt_one3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction=fragmentManger.beginTransaction();
				transaction.replace(R.id.ll_fragment, one3, "one3");
				transaction.commit();
			}
		});
	}
}
