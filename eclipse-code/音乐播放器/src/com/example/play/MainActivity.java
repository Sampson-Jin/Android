package com.example.play;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.service.IMusicService;
import com.example.service.PlayService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMenuItemClickListener{
	//	AppCompat
	private ListView lise;
	@SuppressLint("SdCardPath")
	private final String path="/mnt/sdcard/Music";
	private List<String> lists;
	private MyConn myConn;
	private IMusicService iMusicService;
	private Button bt_s;
	private Button bt_p;
	private Button bt_x;
	private Button bt_showPopu;
	private static int positon=0;
	private final int STOP=0;
	private final int PLAYING=1;
	private final int PAUSE=2;
	private SharedPreferences sharedPref =null;
	private Editor editor=null;
	private int[] res=new int[]{R.drawable.danqu,R.drawable.shunxu,R.drawable.xunhuan,R.drawable.suiji};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lise=(ListView) findViewById(R.id.lv_list);
		bt_s=(Button) findViewById(R.id.bt_s);
		bt_p=(Button) findViewById(R.id.bt_p);
		bt_x=(Button) findViewById(R.id.bt_x);
		bt_showPopu=(Button) findViewById(R.id.button_showPopup);
		sharedPref=getSharedPreferences("config", Context.MODE_PRIVATE);
		editor=sharedPref.edit();

		bt_showPopu.setBackgroundResource(res[sharedPref.getInt("config", 0)]);

		initList();
		lise.setAdapter(new MyAdapter());
		Intent intent=new Intent(getApplicationContext(),PlayService.class);
		startService(intent);
		myConn=new MyConn();
		bindService(intent, myConn, BIND_ABOVE_CLIENT);
		lise.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				iMusicService.MPlay(lists.get(arg2),lists,arg2);
				positon=arg2;
			}
		});

		bt_s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(iMusicService!=null){
					if(positon>0){	
						positon--;
						iMusicService.MPlay(lists.get(positon),lists,positon);
					}
				}
			}
		});
		bt_p.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(iMusicService!=null){
					if(iMusicService.getState()==STOP){
						iMusicService.MPlay(lists.get(positon),lists,positon);
					}else if(iMusicService.getState()==PAUSE){
						iMusicService.MRPlay();
					}else if(iMusicService.getState()==PLAYING){
						iMusicService.MPause();
					}	
				}
			}
		});
		bt_x.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(iMusicService!=null){
					if(positon<lists.size()-1){	
						positon++;
						iMusicService.MPlay(lists.get(positon),lists,positon);
					}
				}
			}
		});

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Intent intent=new Intent(getApplicationContext(),PlayService.class);
		bindService(intent, myConn, BIND_ABOVE_CLIENT);
	}

	private void initList(){
		File file =new File(path);
		File[] flies=file.listFiles();
		lists=new ArrayList<String>();
		for(File f:flies){
			if(f.getName().endsWith(".mp3")){
				lists.add(f.getAbsolutePath());
			}
		}
	}

	public void showPopup(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.popumenu, popup.getMenu());
		popup.setOnMenuItemClickListener(this);	
		popup.show();
	}

	public boolean onMenuItemClick(MenuItem arg0) {
		switch (arg0.getItemId()) {
		case R.id.mi_tone:
			editor.putInt("config", 0);
			editor.commit();
			bt_showPopu.setBackgroundResource(res[0]);
			break;
		case R.id.mi_two:
			editor.putInt("config", 1);
			editor.commit();
			bt_showPopu.setBackgroundResource(res[1]);
			break;
		case R.id.mi_three:
			editor.putInt("config", 2);
			editor.commit();
			bt_showPopu.setBackgroundResource(res[2]);
			break;
		case R.id.mi_four:
			editor.putInt("config", 3);
			editor.commit();
			bt_showPopu.setBackgroundResource(res[3]);
			break;
		}
		return false;
	}

	public class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return lists.size();
		}

		@Override
		public Object getItem(int arg0) {
			return lists.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View view=null;
			if(arg1==null){
				view=View.inflate(getApplicationContext(), R.layout.myadapter, null);
				TextView MusicName=(TextView) view.findViewById(R.id.tv_name);
				ImageView MusicPlay=(ImageView) view.findViewById(R.id.iv_play);
				MusicName.setText(lists.get(arg0).substring(lists.get(arg0).lastIndexOf("/")+1));

			}else {
				view=arg1;
			}
			return view;
		}
	} 
	private class MyConn implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			iMusicService=(IMusicService) arg1;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {

		}

	}
	@Override
	protected void onPause() {
		super.onPause();
		unbindService(myConn);
		System.out.println("解除绑定");
	}
	@Override
	protected void onStop() {
		super.onStop();
		if(iMusicService.getState()==PAUSE||iMusicService.getState()==STOP){
			Intent intent=new Intent(getApplicationContext(),PlayService.class);
			stopService(intent);
			System.out.println("停止服务");
		}
	}

}
