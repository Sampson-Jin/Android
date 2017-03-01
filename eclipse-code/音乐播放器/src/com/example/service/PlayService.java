package com.example.service;

import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;

public class PlayService extends Service {

	private MediaPlayer mediaPlayer=null;
	MyBinder myBinder=new MyBinder();
	private final int STOP=0;
	private final int PLAYING=1;
	private final int PAUSE=2;
	private static int state=0;
	private SharedPreferences sharedPref =null;
	private List<String> slists;
	private static int sposition=0;
	@Override
	public IBinder onBind(Intent arg0) {

		return myBinder;
	}
	@Override
	public void onCreate() {
		System.out.println("音乐服务开始");
		super.onCreate();
		mediaPlayer = new MediaPlayer();
		sharedPref=getSharedPreferences("config", Context.MODE_PRIVATE);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		System.out.println("音乐服务关闭");
		state=STOP;
		super.onDestroy();
	}
	private void MusicStart(String path){
		try {
			if(mediaPlayer!=null){
				if(state!=PLAYING){
					mediaPlayer.reset();
					mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
					mediaPlayer.setDataSource(path);
					mediaPlayer.prepare();
					mediaPlayer.start();
					state=PLAYING;
					mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer arg0) {
							mediaPlayer.reset();
							state=STOP;
							whatDoNext();
						}
					});}else{
						MusicStop();
						MusicStart(path);
					}
			}
		}catch(Exception e) {
			e.printStackTrace();
			state=STOP;
		}
	}
	private void MusicStop(){
		if(mediaPlayer!=null){
			mediaPlayer.stop();
			state=STOP;
		}
	}
	private void MusicPause(){
		if(mediaPlayer!=null){
			mediaPlayer.pause();
			state=PAUSE;
		}
	}
	private void MusicRestart(){
		if(mediaPlayer!=null){
			mediaPlayer.start();
			state=PLAYING;
		}
	}

	private void whatDoNext(){

		if(sharedPref.getInt("config", 0)==0){
			MusicStart(slists.get(sposition));
		}else if (sharedPref.getInt("config", 0)==1) {
			if(sposition<slists.size()-1){
				sposition++;
				MusicStart(slists.get(sposition));
			}

		}else if (sharedPref.getInt("config", 0)==2) {
			sposition++;
			if(sposition>=slists.size()){
				sposition=0;		
			}
			MusicStart(slists.get(sposition));
		}else if (sharedPref.getInt("config", 0)==3) {
			double d=Math.random();
			MusicStart(slists.get((int)(Math.floor(d*slists.size()))));
		}
	}

	private class MyBinder extends Binder implements IMusicService{

		@Override
		public void MPlay(String path,List<String> lists,int position) {
			MusicStart(path);
			slists=lists;
			sposition=position;
		}

		@Override
		public void MPause() {
			MusicPause();
		}

		@Override
		public void MStop() {
			MusicStop();			
		}

		@Override
		public void MRPlay() {
			MusicRestart();
		}

		@Override
		public int getState() {
			return state;
		}
	}
}
