package cc.guodong.progressdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.app.Activity;

public class MainActivity extends Activity {
	private ProgressBar probar;
	private int states=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		probar=(ProgressBar)super.findViewById(R.id.myprogressBar);
		Thread mthread=new MyThread();
		mthread.start();
	}
	//����������ķ�������дHandler��handleMessage()����,���ǲ��ûص��ķ���
	final Handler myhandler=new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what==001)
				probar.setVisibility(View.INVISIBLE);
		}
	};
	class MyThread extends Thread
	{
		public void run()
		{
			Message msg=new Message();
			//��msg��ֵ
			msg.what=001;
			while(states<100)
			{
				doWork();
				probar.setProgress(states);
				//������߳��в��ܵ������̴߳�����View�ķ�����probar.setProgress(states);�Ǹ����⣬Ҫʹ��Handler
				/*if(states==50)
				probar.setVisibility(View.INVISIBLE);*/
				if(states==50)
					myhandler.sendMessage(msg);
			}
		}
	}
	public int doWork()
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		states++;
		return states;
	}
}
