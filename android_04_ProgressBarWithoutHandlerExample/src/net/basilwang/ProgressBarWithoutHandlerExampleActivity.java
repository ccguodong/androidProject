package net.basilwang;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarWithoutHandlerExampleActivity extends Activity {
	// 该程序模拟填充长度为100的数组
	private int[] data = new int[100];
	int hasData = 0;
	// 记录ProgressBar的完成进度
	int status = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		final ProgressBar bar2 = (ProgressBar) findViewById(R.id.progressBar2);
		
        //启动线程来执行任务
        new Thread()
        {
        	public void run()
        	{
        		while(status<100)
        		{
        			//获取耗时操作的完成百分比
        			status=doWork();

        			//线程安全
        			bar.setProgress(status);
        			bar2.setProgress(status);
        			//非主UI线程无法修改UI线程控件
        			bar.setBackgroundColor(255);
        		}
        	}
        }.start();
        
    }
    //模拟一个耗时的操作
	protected int doWork() {
		// TODO Auto-generated method stub
		//为数组元素赋值
		data[hasData++]=(int)(Math.random()*100);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasData;
		
	}
}









