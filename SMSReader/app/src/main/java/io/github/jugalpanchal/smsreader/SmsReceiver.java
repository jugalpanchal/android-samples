package io.github.jugalpanchal.smsreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jugal on 27-06-2017.
 */

public class SmsReceiver extends BroadcastReceiver {

    final SmsManager smsManager = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusList= (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusList.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusList[i]);
                    String senderNumber = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "Sender Number: "+ senderNumber + ", Message: " + message);
                    Toast toast = Toast.makeText(context,
                            "Sender Number: "+ senderNumber + ", Message: " + message, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception " +e);
        }
    }
}