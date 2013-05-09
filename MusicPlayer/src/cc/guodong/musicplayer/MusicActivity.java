package cc.guodong.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MusicActivity extends Activity implements OnClickListener{
	Button start;
	Button stop;
	Button pause;
	ProgressBar mySeekBar;
	public MediaPlayer myMediaPlayer = MusicPlayService.mPlayer;
	//此静态常量是用来 声明ServiceReceiver能接受的intent
	public static final String SERVICE_ACTION="cc.guodong.musicplayer.SERVICE_ACTION";
	//此静态常量是用来 声明ActivityReceiver能接受的intent
	public static final String ACTIVITY_ACTION="cc.guodong.musicplayer.ACTIVITY_ACTION";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		//给三个按钮分别绑定事件
		start=(Button)super.findViewById(R.id.start);
		pause=(Button)super.findViewById(R.id.pause);
		stop=(Button)super.findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		pause.setOnClickListener(this);
		//声明要启动的service
		final Intent intent=new Intent(this, MusicPlayService.class);
		//启动service
		startService(intent);
		ActivityReceiver activityReceiver=new ActivityReceiver();
		IntentFilter activityReceiverFilter=new IntentFilter(ACTIVITY_ACTION);
		registerReceiver(activityReceiver, activityReceiverFilter);
		//取出progressBar
		mySeekBar=(ProgressBar)super.findViewById(R.id.myprogressBar);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int source = v.getId();
		//此Intent用来启动 BroadcastReceiver
		final Intent intent=new Intent();
		//设置intent的action,即要启动的 BroadcastReceiver
		intent.setAction(SERVICE_ACTION);
		switch(source)
		{
		case R.id.start:
			intent.putExtra("control", 1);
			sendBroadcast(intent);
			break;
		case R.id.pause:
			intent.putExtra("control", 2);
			sendBroadcast(intent);
			break;		
		case R.id.stop:
			intent.putExtra("control", 3);
			sendBroadcast(intent);
			break;
		}
	}
	//此类用来接受Progress发过来的播放进度的信息
	public class ActivityReceiver extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int currentPosition=intent.getIntExtra("currentPosition", 0);
			int duration=intent.getIntExtra("duration", 1);
			Log.v("-----currentPosition---", currentPosition+"");
			Log.v("-----duration---", duration+"");
			//myMediaCurrentPotion=process;
			/*Mythead mthread=new Mythead();
			mthread.start();*/
			//states=100*(currentPosition/duration);
			float temp=1000*((float)currentPosition/duration);
			int process=(int)temp;
			Log.v("-----process---",process+"");
			//这样写，程序为什么没有死掉？？？没在主线程中休眠，就可以这样用吗？？
			mySeekBar.setProgress(process);
		}	
	}
	//此类用于处理进度条
	/*class Mythead extends Thread
	{

		@Override
		public void run() {
			states=100*(myMediaCurrentPotion/myMediaDuration);
			myprogressBar.setProgress(states);
		}
		
	}*/
	

}
