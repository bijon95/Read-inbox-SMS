package com.appstcit.readsms;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SMSData sms;
    String body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv=(ListView)findViewById(R.id.SmsList);

        if(fetchInbox()!=null){
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,fetchInbox());
            lv.setAdapter(adapter);
        }

    }
    public ArrayList<String> fetchInbox(){

        ArrayList<String> sms=new ArrayList<String>();

        Uri uriSms=Uri.parse("content://sms/inbox");

        Cursor cursor=getContentResolver().query(uriSms, new String[]{"_id","address","date","body"}, null, null, null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){

            String address=cursor.getString(1);
            String body=cursor.getString(3);

            sms.add("Address=>"+address+"\n Sms=>"+body);
        }
        return sms;
    }
}
