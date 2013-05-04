package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  //�������ڷ���
        setContentView(R.layout.activity_main);//����Ҫʹ�õĲ��ֹ�����
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView txt=new TextView(this);
        txt.setText(R.string.info);
        Button but=new Button(this);
        but.setText(R.string.but);
        layout.addView(txt);
        layout.addView(but);
        super.setContentView(layout);//����Ҫʹ�õĲ��ֹ�����
        /*TextView txt=(TextView)super.findViewById(R.id.mytext);
        txt.setText(R.string.info);
        Button but=(Button)super.findViewById(R.id.mybut);
        but.setText(R.string.but);*/
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
