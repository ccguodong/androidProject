package cc.guodong.test;


import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectCityActivity extends ExpandableListActivity
{
	//����ʡ������
	private String[] provinces = new String[]
	{ "�㶫", "����", "����"};
	private String[][] cities = new String[][]
	{
		{ "����", "����", "�麣", "��ɽ" },
		{ "����", "����", "����", "����" },
		{ "��ɳ", "����" , "����" , "����" }
	};	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ExpandableListAdapter adapter = new BaseExpandableListAdapter()
		{
			//��ȡָ����λ�á�ָ�����б�������б�������
			@Override
			public Object getChild(int groupPosition, int childPosition)
			{
				return cities[groupPosition][childPosition];
			}
			@Override
			public long getChildId(int groupPosition, int childPosition)
			{
				return childPosition;
			}
			@Override
			public int getChildrenCount(int groupPosition)
			{
				return cities[groupPosition].length;
			}
			private TextView getTextView()
			{
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, 64);
				TextView textView = new TextView(SelectCityActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}
			//�÷�������ÿ����ѡ������
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent)
			{
				TextView textView = getTextView();			
				textView.setText(getChild(groupPosition, childPosition).toString());
				return textView;
			}
			//��ȡָ����λ�ô���������
			@Override
			public Object getGroup(int groupPosition)
			{
				return provinces[groupPosition];
			}
			@Override
			public int getGroupCount()
			{
				return provinces.length;
			}
			@Override
			public long getGroupId(int groupPosition)
			{
				return groupPosition;
			}
			//�÷�������ÿ����ѡ������
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent)
			{
				LinearLayout ll = new LinearLayout(SelectCityActivity.this);
				ll.setOrientation(0);
				ImageView logo = new ImageView(SelectCityActivity.this);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());				
				ll.addView(textView);			
				return ll;
			}
			@Override
			public boolean isChildSelectable(int groupPosition, int childPosition)
			{
				return true;
			}
			@Override
			public boolean hasStableIds()
			{
				return true;
			}
		};
		// ���øô�����ʾ�б�
		setListAdapter(adapter);
		getExpandableListView().setOnChildClickListener(
			new OnChildClickListener()
		{
			@Override
			public boolean onChildClick(ExpandableListView parent, View source,
				int groupPosition, int childPosition, long id)
			{
				//��ȡ������Activity֮ǰ��Activity��Ӧ��Intent
				Intent intent = getIntent();
				Bundle data = new Bundle();
				data.putString("city" ,cities[groupPosition][childPosition]);
				intent.putExtras(data);
				// ���ø�SelectActivity�Ľ���룬�����ý���֮���˻ص�Activity
				SelectCityActivity.this.setResult(0 , intent);
				//����SelectCityActivity��
				SelectCityActivity.this.finish();
				return false;
			}
		});
	}
}