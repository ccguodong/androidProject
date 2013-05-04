package cc.guodong.readothersharedpreferences;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Context useCount=null;
		//flags  ��־λ����CONTEXT_INCLUDE_CODE��CONTEXT_IGNORE_SECURITY����ѡ�
		//CONTEXT_INCLUDE_CODE����˼�ǰ������룬Ҳ����˵����ִ�����������Ĵ��롣
		//CONTEXT_IGNORE_SECURITY����˼�Ǻ��԰�ȫ���棬������������־�Ļ�����Щ�������ò��˵ģ�����ְ�ȫ���档
		try {
			useCount=createPackageContext("cc.guodong.sharedpreferences", Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ʹ�ó����е�Context����ȡ��Ӧ��SharedPreferences
		SharedPreferences prefer=useCount.getSharedPreferences("time", Context.MODE_WORLD_READABLE);
		int random=prefer.getInt("random", 0);
		TextView text=(TextView)super.findViewById(R.id.show);
		text.setText(random+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
