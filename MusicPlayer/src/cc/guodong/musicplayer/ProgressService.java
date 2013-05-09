package cc.guodong.musicplayer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class ProgressService extends Service {
	//声明一个定时器
	private Timer myTimer = new Timer();
	public MediaPlayer myMediaPlayer = MusicPlayService.mPlayer;
	Intent actRecIntent;
	public ProgressService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		/*Log.v("----------right------", "----------ok--------");
		Log.v("------getCurrentPosition--", myMediaPlayer.getCurrentPosition()
				+ "");
		Log.v("------getDuration ()---------", myMediaPlayer.getDuration()
				+ "mm");*/
		//给这个定时器安排任务，使它0.1秒执行一次，这个service每0.1秒向BroadcastReceiver发送一次
		myTimer.schedule(task,0,100);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			/*Log.v("------delay--",
					myMediaPlayer.getCurrentPosition() + "");*/
			actRecIntent=new Intent();
			//这个intent指向ActivityReceiver
			actRecIntent.setAction(MusicActivity.ACTIVITY_ACTION);
			//把歌曲的总时间和现在歌曲播放的时间绑定到intent中
			actRecIntent.putExtra("currentPosition", myMediaPlayer.getCurrentPosition());
			actRecIntent.putExtra("duration", myMediaPlayer.getDuration());
			//发送这个广播
			sendBroadcast(actRecIntent);
		}
	};

}
