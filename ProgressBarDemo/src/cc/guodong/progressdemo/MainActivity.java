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
	//采用匿名类的方法来重写Handler的handleMessage()方法,这是采用回调的方法
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
			//给msg赋值
			msg.what=001;
			while(states<100)
			{
				doWork();
				probar.setProgress(states);
				//在这个线程中不能调用主线程创建的View的方法，probar.setProgress(states);是个例外，要使用Handler
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
