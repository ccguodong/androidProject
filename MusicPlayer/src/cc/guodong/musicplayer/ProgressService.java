package cc.guodong.musicplayer;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class ProgressService extends Service {
	//����һ����ʱ��
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
		//�������ʱ����������ʹ��0.1��ִ��һ�Σ����serviceÿ0.1����BroadcastReceiver����һ��
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
			//���intentָ��ActivityReceiver
			actRecIntent.setAction(MusicActivity.ACTIVITY_ACTION);
			//�Ѹ�������ʱ������ڸ������ŵ�ʱ��󶨵�intent��
			actRecIntent.putExtra("currentPosition", myMediaPlayer.getCurrentPosition());
			actRecIntent.putExtra("duration", myMediaPlayer.getDuration());
			//��������㲥
			sendBroadcast(actRecIntent);
		}
	};

}
