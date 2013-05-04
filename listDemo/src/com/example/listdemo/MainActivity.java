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
	private String[] names = new String[] { "QQ", "浏览器", "git", "笔记" };
	private int[] imagesID = new int[] { R.drawable.p1, R.drawable.p2,
			R.drawable.p3, R.drawable.p4 };
	private String[] tests=new String[]{"测试1","测试2","测试3","测试3","测试4"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 * ListView list=(ListView)super.findViewById(R.id.listview); String[]
		 * arr={"孙悟空","猪八戒","沙和尚"}; ArrayAdapter<String> arrayadapter=new
		 * ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
		 * arr);//android.R.layout.simple_list_item_1表示布局形式，系统已经把它封装成一个int的数据型
		 * list.setAdapter(arrayadapter);
		 */

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < names.length; i++) {
			// HashMap来实例化一个Map
			Map<String, Object> listItem = new HashMap<String, Object>();
			//显示的顺序是按照Map中的排列顺序
			listItem.put("header", imagesID[i]);
			listItem.put("names", names[i]);
			listItem.put("mytest", tests[i]);
			listItems.add(listItem);
		}
		// 各个参数是啥意思？？？？
		SimpleAdapter adapter = new SimpleAdapter(this, listItems,
				R.layout.activity_main,
				//String数组中的names和header必须和Map中的key的名字一致
				new String[] {"mytest", "names", "header" }, 
				new int[] {R.id.test, R.id.name,  //这里的View的id是和前面的String里的元素是一一对应的
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
