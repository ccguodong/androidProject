package cc.guodong.intentdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Receive extends Activity {
	private TextView show=null;
	private Button but=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receive);
		show=(TextView)super.findViewById(R.id.show);
		Intent it=super.getIntent();//取得当前的Intent
		String info=it.getStringExtra("myinfo");
		this.show.setText(info);
		this.but=(Button)super.findViewById(R.id.sendbut);
		but.setOnClickListener(new SetOnclic());
	}
	private class SetOnclic implements android.view.View.OnClickListener
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Receive.this.getIntent().putExtra("remsg", "能否成功返回");
			Receive.this.setResult(RESULT_OK, Receive.this.getIntent());
			Receive.this.finish();
		}
		
	}

}
