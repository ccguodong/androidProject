package cc.guodong.filetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final String File_name="test.bin";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText writeEdit=(EditText)super.findViewById(R.id.writeEdit);
		Button write=(Button)super.findViewById(R.id.write);
		final EditText show=(EditText)super.findViewById(R.id.show);
		Button read=(Button)super.findViewById(R.id.read);
		write.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				write(writeEdit.getText().toString());
				writeEdit.setText("");
			}
		});
		read.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show.setText(show());
			}
		});
	}
	public void write(String str)
	{
		try {
			//FileOutputStream��FileInputStream���Ǵ����ֽ���
			FileOutputStream fout=openFileOutput(File_name, Context.MODE_APPEND);
			//��Ҫ���ַ������͵�����ת�����ֽ�����
			fout.write(str.getBytes());
			//��ջ�������������д�롣�������Ҳ����
		//	fout.flush();
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String show()
	{
		//StringBuilder��StringBuffer����
		StringBuilder sb=new StringBuilder();
		try {
			//ͨ��openFileInput������������һ��FileInputStream����
			FileInputStream fin=openFileInput(File_name);
			byte[] bytes=new byte[1024];
			
			int hasread=0;
			/*read(buffer, 0, buffer.length)
			*fin.read(bytes)����˼Ӧ���ǣ�����������0�ֽڿ�ʼ������bytes�ĳ��ȣ��Ѷ�ȡ�����ݴ洢��
			*bytes�����С����ص�ֵΪ��ȡ�ĳ��ȣ��������󷵻�-1
			*/
			
			while((hasread=fin.read(bytes))>0)
			{
				sb.append(new String(bytes, 0, hasread));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
