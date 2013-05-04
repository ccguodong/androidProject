package cc.guodong.sharedpreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//getSharedPreferences����Context���еĶ���
		@SuppressWarnings("deprecation")
		final SharedPreferences preferences=super.getSharedPreferences("time", MODE_WORLD_READABLE);
		//Editor�ӿ���SharedPreferences���ڲ��ӿڣ����ڱ༭����edit������������ȡEditor����
		final Editor editor= preferences.edit();
		Button read=(Button)super.findViewById(R.id.read);
		Button write=(Button)super.findViewById(R.id.wirte);
		write.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy��MM��dd��"+"hh:mm:ss");
				editor.putString("time", sdf.format(new Date()));
				editor.putInt("random", (int)(Math.random()*100));
				//�༭��ɺ�Ҫ�ύ
				editor.commit();
			}
		});
		read.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String time=preferences.getString("time", null);
				int random=preferences.getInt("random", 0);
				String result=time==null?"����ʱ��ûд������":"д��ʱ��"+time+"\nд��������ǣ�"+random;
				Toast.makeText(MainActivity.this, result,10000 ).show();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
