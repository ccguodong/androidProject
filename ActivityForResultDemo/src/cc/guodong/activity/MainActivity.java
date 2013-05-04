package cc.guodong.activity;

import cc.guodong.test.SelectCityActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button bn;
	EditText city;	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ȡ�����ϵ����
		bn = (Button)findViewById(R.id.bn);
		city = (EditText)findViewById(R.id.city);
		//Ϊ��ť���¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//������Ҫ��Ӧ��Ŀ��Activity��Intent
				/*Intent intent = new Intent(MainActivity.this
					, SelectCityActivity.class);*/
				//�������д���Ҳ����������Ĵ���ʵ��
				ComponentName comp=new ComponentName(MainActivity.this,SelectCityActivity.class);
				Intent intent =new Intent();
				intent.setComponent(comp);
				//����ָ��Activity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
				startActivityForResult(intent , 0);
			}
		});
	}
	//��д�÷������÷����Իص��ķ�ʽ����ȡָ��Activity���صĽ��
	@Override
	public void onActivityResult(int requestCode , int resultCode
		, Intent intent)
	{
		//��requestCode��resultCodeͬʱΪ0��Ҳ���Ǵ����ض��Ľ��
		if (requestCode == 0
			&& resultCode == 0)
		{
			//ȡ��Intent���Extras����
			Bundle data = intent.getExtras();
			//ȡ��Bundle�е�����
			String resultCity = data.getString("city");
			//�޸�city�ı��������
			city.setText(resultCity);
		}
	}

}
