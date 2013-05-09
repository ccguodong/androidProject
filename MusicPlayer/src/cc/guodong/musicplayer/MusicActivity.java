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
	//�˾�̬���������� ����ServiceReceiver�ܽ��ܵ�intent
	public static final String SERVICE_ACTION="cc.guodong.musicplayer.SERVICE_ACTION";
	//�˾�̬���������� ����ActivityReceiver�ܽ��ܵ�intent
	public static final String ACTIVITY_ACTION="cc.guodong.musicplayer.ACTIVITY_ACTION";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		//��������ť�ֱ���¼�
		start=(Button)super.findViewById(R.id.start);
		pause=(Button)super.findViewById(R.id.pause);
		stop=(Button)super.findViewById(R.id.stop);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		pause.setOnClickListener(this);
		//����Ҫ������service
		final Intent intent=new Intent(this, MusicPlayService.class);
		//����service
		startService(intent);
		ActivityReceiver activityReceiver=new ActivityReceiver();
		IntentFilter activityReceiverFilter=new IntentFilter(ACTIVITY_ACTION);
		registerReceiver(activityReceiver, activityReceiverFilter);
		//ȡ��progressBar
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
		//��Intent�������� BroadcastReceiver
		final Intent intent=new Intent();
		//����intent��action,��Ҫ������ BroadcastReceiver
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
	//������������Progress�������Ĳ��Ž��ȵ���Ϣ
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
			//����д������Ϊʲôû������������û�����߳������ߣ��Ϳ����������𣿣�
			mySeekBar.setProgress(process);
		}	
	}
	//�������ڴ��������
	/*class Mythead extends Thread
	{

		@Override
		public void run() {
			states=100*(myMediaCurrentPotion/myMediaDuration);
			myprogressBar.setProgress(states);
		}
		
	}*/
	

}
