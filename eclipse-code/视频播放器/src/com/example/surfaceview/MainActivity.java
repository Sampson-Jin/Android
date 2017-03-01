package com.example.surfaceview;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity  {

	private SurfaceView surfaceView;
	private MediaPlayer mediaPlay;
	private SeekBar seekBar;
	private Timer timer;
	private TimerTask timerTask;
	private ProgressBar progressBar;
	private SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		surfaceView=(SurfaceView) findViewById(R.id.surfaceView1);
		sharedPreferences=getSharedPreferences("config", MODE_PRIVATE);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		progressBar=(ProgressBar) findViewById(R.id.progressBar1);
		surfaceView.getHolder().addCallback(new Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(mediaPlay!=null&&mediaPlay.isPlaying()){
					int position=mediaPlay.getCurrentPosition();
					Editor editor=sharedPreferences.edit();
					editor.putInt("position", position);
					editor.commit();
					mediaPlay.stop();
					mediaPlay.release();
					mediaPlay=null;
					timer.cancel();
					timerTask.cancel();
					timer=null;
					timerTask=null;
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				mediaPlay=new MediaPlayer();
				mediaPlay.setAudioStreamType(AudioManager.STREAM_MUSIC);
				//设置缩放比例
				//mediaPlay.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
				try {
					//192.168.1.102
					//192.168.22.2
					mediaPlay.setDataSource("http://192.168.1.102:8080/jin.mp4");
					//播放视频必须设置播放的容器
					mediaPlay.setDisplay(surfaceView.getHolder());
					//需要先准备完毕在播放
					//mediaPlay.prepare();
					mediaPlay.prepareAsync();		
					mediaPlay.setOnCompletionListener(new OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer mp) {
							Editor editor=sharedPreferences.edit();
							editor.putInt("position", 0);
							editor.commit();
						}
					});
					mediaPlay.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							if(progressBar.getVisibility()==View.VISIBLE){
								progressBar.setVisibility(View.GONE);
							}
							mediaPlay.start();
							int position=sharedPreferences.getInt("position", 0);
							mediaPlay.seekTo(position);
							setMYSeekBar();
						}
					});
				} catch (Exception e) {
					System.out.println("asas-----------------------");
					e.printStackTrace();

				} 
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

			}
		});
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if(mediaPlay!=null){
					int position= seekBar.getProgress();
					mediaPlay.seekTo(position);
					setMYSeekBar();
				}
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				timer.cancel();

			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}
		});

	}
	private void setMYSeekBar(){
		timer=new Timer();
		timerTask=new TimerTask() {
			@Override
			public void run() {
				if(mediaPlay!=null){
					int max=mediaPlay.getDuration();
					int position=mediaPlay.getCurrentPosition();
					seekBar.setMax(max);
					seekBar.setProgress(position);
				}
			}
		};
		timer.schedule(timerTask, 0, 500);
	}
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction()==MotionEvent.ACTION_DOWN){
				if(seekBar.getVisibility()==View.GONE){
					seekBar.setVisibility(View.VISIBLE);
	
					new Thread(){
						public void run() {
							try {
								Thread.sleep(5000);
								runOnUiThread(new Runnable() {
	
									@Override
									public void run() {
										if(seekBar.getVisibility()==View.VISIBLE){
											seekBar.setVisibility(View.GONE);
										}
									}
								});
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}					
						};
					}.start();
				}else if(seekBar.getVisibility()==View.VISIBLE){
					seekBar.setVisibility(View.GONE);}
			}
			return true;
		}
}
