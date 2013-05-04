package cc.guodong.intentdemo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Send extends Activity {
	TextView text=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
		Button but=(Button)super.findViewById(R.id.sendbut);
		but.setOnClickListener(new OnClickListenerImpl());
		this.text=(TextView)super.findViewById(R.id.sendshow);
	}
	
	private class OnClickListenerImpl implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent it=new Intent(Send.this, Receive.class);
			it.putExtra("myinfo", "张国栋信息1001");
			Send.this.startActivityForResult(it, 1);//第二个参数大于1,执行操作
		
	}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(resultCode)
		{
		case RESULT_OK:
			Send.this.text.setText(data.getStringExtra("remsg"));
		break;
		case RESULT_CANCELED:
			Send.this.text.setText("操作取消");
		break;
		default:
			break;
		}
			
	}
	
}