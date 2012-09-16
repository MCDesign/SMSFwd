package cts.support.smsfwd;

import java.util.Iterator;
import java.util.List;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceive extends BroadcastReceiver {
private String mesajSMS;

	public void onReceive(Context context, Intent intent) {
		DataSource db = new DataSource (context);
		db.open();
		Bundle bundle = intent.getExtras();
		Toast.makeText(context, "Mesaj receptionat", Toast.LENGTH_SHORT).show();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++) {
		smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		mesajSMS = smsMessage[0].getMessageBody();
		Toast.makeText(context, mesajSMS, Toast.LENGTH_SHORT).show();
		List <Filtre> filtre = (List<Filtre>) db.getAllFiltre();
		
		int i;
		for (i=0;i<filtre.size();i++)
		{
			//Toast.makeText(context, filtre.get(i).getName(), Toast.LENGTH_LONG).show();
			if (mesajSMS.contains(filtre.get(i).getPayload()))
			{
				SmsManager sms = SmsManager.getDefault();

				PendingIntent pi_sent = PendingIntent.getActivity(context, 0,
			            new Intent(context, SMSReceive.class), 0);  	
				PendingIntent pi_delivery = PendingIntent.getActivity(context, 0,
			            new Intent(context, SMSReceive.class), 0);  

					
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		        sendIntent.putExtra("sms_body", "Content of the SMS goes here..."); 
		        sendIntent.setType("vnd.android-dir/mms-sms");
				sms.sendTextMessage(filtre.get(i).getPhone(), 
						null, 
						mesajSMS, 
						pi_sent, 
						pi_delivery);
				Toast.makeText(context, filtre.get(i).getName(), Toast.LENGTH_LONG).show();
				
			}
		
		}
		
	}
}
