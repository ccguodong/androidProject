package com.example.listdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	private String[] names = new String[] { "QQ", "�����", "git", "�ʼ�" };
	private int[] imagesID = new int[] { R.drawable.p1, R.drawable.p2,
			R.drawable.p3, R.drawable.p4 };
	private String[] tests=new String[]{"����1","����2","����3","����3","����4"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 * ListView list=(ListView)super.findViewById(R.id.listview); String[]
		 * arr={"�����","��˽�","ɳ����"}; ArrayAdapter<String> arrayadapter=new
		 * ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
		 * arr);//android.R.layout.simple_list_item_1��ʾ������ʽ��ϵͳ�Ѿ�������װ��һ��int��������
		 * list.setAdapter(arrayadapter);
		 */

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			// HashMap��ʵ����һ��Map
			Map<String, Object> listItem = new HashMap<String, Object>();
			//��ʾ��˳���ǰ���Map�е�����˳��
			listItem.put("header", imagesID[i]);
			listItem.put("names", names[i]);
			listItem.put("mytest", tests[i]);
			listItems.add(listItem);
		}
		// ����������ɶ��˼��������
		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.activity_main,
				//String�����е�names��header�����Map�е�key������һ��
				new String[] {"mytest", "names", "header" }, 
				new int[] {R.id.test, R.id.name,  //�����View��id�Ǻ�ǰ���String���Ԫ����һһ��Ӧ��
						R.id.header});
		ListView list2 = (ListView) super.findViewById(R.id.list2);
		list2.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
