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
		//flags  标志位，有CONTEXT_INCLUDE_CODE和CONTEXT_IGNORE_SECURITY两个选项。
		//CONTEXT_INCLUDE_CODE的意思是包括代码，也就是说可以执行这个包里面的代码。
		//CONTEXT_IGNORE_SECURITY的意思是忽略安全警告，如果不加这个标志的话，有些功能是用不了的，会出现安全警告。
		try {
			useCount=createPackageContext("cc.guodong.sharedpreferences", Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//使用程序中的Context来获取对应的SharedPreferences
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
