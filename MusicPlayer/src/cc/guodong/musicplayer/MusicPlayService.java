package cc.guodong.musicplayer;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager.ServiceResponseListener;
import android.os.IBinder;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class MusicPlayService extends Service {
	AssetFileDescriptor afd;
	AssetManager am;
	public static MediaPlayer mPlayer = new MediaPlayer();

	public MusicPlayService() {
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
		ServiceReceiver myserviceReceiver = new ServiceReceiver();
		IntentFilter filter = new IntentFilter(MusicActivity.SERVICE_ACTION);
		registerReceiver(myserviceReceiver, filter);
		// Log.v("------", "--------completed");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void preparedAndPlay() {
		// 以下为MediaPlayer播放音乐的固定格式
		am = getAssets();
		try {
			afd = am.openFd("Complicated.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mPlayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mPlayer.start();
	}

	public class ServiceReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int control = intent.getIntExtra("control", -1);
			switch (control) {
			case 1:
				//Log.v("-----------", "------case1");
				preparedAndPlay();
				break;
			case 2:
				mPlayer.pause();
				//Log.v("-----------pause", "------case2");
				/*Log.v("------getDuration ()---------", mPlayer.getDuration()
						+ "mm");
				Log.v("------getcurrentPosition---------",
						mPlayer.getCurrentPosition() + "mm");*/

				break;
			case 3:
			//	Log.v("-----------3", "------case3");
				mPlayer.stop();
				break;
			case -1:
				break;
			}
			//启动ProgressService
			Intent intentProService=new Intent();
			intentProService.setAction("android.intent.action.PRO_SERVICE");
			startService(intentProService);		
		}

	}

}
