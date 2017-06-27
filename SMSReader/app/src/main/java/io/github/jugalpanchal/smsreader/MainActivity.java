package io.github.jugalpanchal.smsreader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<SMSModel> smsList = new ArrayList<>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor= getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(cursor);

        if(cursor.moveToFirst()) {
            for(int i=0; i < cursor.getCount(); i++) {
                SMSModel sms = new SMSModel();
                sms.setNumber(cursor.getString(cursor.getColumnIndexOrThrow("address")).toString());
                sms.setText(cursor.getString(cursor.getColumnIndexOrThrow("body")).toString());
                smsList.add(sms);

                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
