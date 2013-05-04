package cc.guodong.bundletest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bn=(Button)super.findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			EditText name=(EditText)findViewById(R.id.username_edit);
			EditText password=(EditText)findViewById(R.id.password_edit);
			RadioButton male=(RadioButton)findViewById(R.id.male);
			String sex= male.isChecked()?"ÄÐ":"Å®";
			Person person=new Person(name.getText().toString(), password.getText().toString(), sex);
			Bundle bundle=new Bundle();
			bundle.putSerializable("person", person);
			Intent intent=new Intent(MainActivity.this,ResultActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
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
