package net.basilwang;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.widget.TextView;

public class ContentProviderExampleActivity extends Activity {
    /** Called when the activity is first created. */
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        TextView tv=new TextView(this);
	        String string="";
	        ContentResolver cr=getContentResolver();
	        //ContactsContract.Contacts.CONTENT_URI
	        Cursor cursor=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
	        while(cursor.moveToNext())
	        { 
	        	//ContactsContract.Contacts.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER
	        	int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
	        	String contact=cursor.getString(nameFieldColumnIndex);
	        	int numberFieldColumnIndex=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
	        	String number=cursor.getString(numberFieldColumnIndex);
	        	string +=(contact+":"+number+"\n");
	        } 
	        cursor.close();
	        tv.setText(string);
	        setContentView(tv);
	    }
}