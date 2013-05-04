package net.basilwang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceExampleActivity extends Activity {
    /** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//��main.xml�����л��Button����
		Button button_start = (Button)findViewById(R.id.start);
		Button button_stop = (Button)findViewById(R.id.stop);
		//���ð�ť��Button������
		button_start.setOnClickListener(start);
        button_stop.setOnClickListener(stop);

	}
	
	//��ʼ��ť
	private OnClickListener start = new OnClickListener()
    {
        public void onClick(View v)
        {   
        	//����Service
            startService(new Intent("net.basilwang.Android.MUSIC"));
        }
    };
   //ֹͣ��ť
    private OnClickListener stop = new OnClickListener()
    {
        public void onClick(View v)
        {
        	//ֹͣService
            stopService(new Intent("net.basilwang.Android.MUSIC"));       
        }
    };
}