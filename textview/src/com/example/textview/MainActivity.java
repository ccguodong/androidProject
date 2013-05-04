package com.example.textview;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.app.Activity;

public class MainActivity extends Activity {
	View view2=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.view2=(View)super.findViewById(R.id.et2);
        view2.setEnabled(false);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
