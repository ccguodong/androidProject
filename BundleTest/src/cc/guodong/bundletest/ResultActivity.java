package cc.guodong.bundletest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ResultActivity extends Activity {//������ʵ��������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actitviy_result);
		TextView name=(TextView)findViewById(R.id.nametxt);
		TextView password=(TextView)findViewById(R.id.passwordtxt);
		TextView sex=(TextView)findViewById(R.id.sextxt);
		Intent intent=getIntent();
		Bundle bun=new Bundle();
		bun=intent.getExtras();
		Person p=new Person();
		p=(Person)bun.getSerializable("person");
		name.setText("�����û����ǣ�"+p.getName());
		password.setText("���������ǣ�"+p.getPassword());
		sex.setText("�����Ա��ǣ�"+p.getSex());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
