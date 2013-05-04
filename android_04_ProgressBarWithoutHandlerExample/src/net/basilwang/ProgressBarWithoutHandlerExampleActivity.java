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
	// �ó���ģ����䳤��Ϊ100������
	private int[] data = new int[100];
	int hasData = 0;
	// ��¼ProgressBar����ɽ���
	int status = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		final ProgressBar bar2 = (ProgressBar) findViewById(R.id.progressBar2);
		
        //�����߳���ִ������
        new Thread()
        {
        	public void run()
        	{
        		while(status<100)
        		{
        			//��ȡ��ʱ��������ɰٷֱ�
        			status=doWork();

        			//�̰߳�ȫ
        			bar.setProgress(status);
        			bar2.setProgress(status);
        			//����UI�߳��޷��޸�UI�߳̿ؼ�
        			bar.setBackgroundColor(255);
        		}
        	}
        }.start();
        
    }
    //ģ��һ����ʱ�Ĳ���
	protected int doWork() {
		// TODO Auto-generated method stub
		//Ϊ����Ԫ�ظ�ֵ
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









