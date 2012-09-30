package cts.support.smsfwd;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SMSFwdSetting extends Activity {
	AppSettingsRecord app_rec;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsfwd_setting);
        app_rec = new AppSettingsRecord(getApplicationContext());
        app_rec.LoadSettings();
        
      	final CheckBox checkBox_EnableFwd = (CheckBox) findViewById(R.id.cb_settings_EnableForfarding);
    	final CheckBox checkBox_LogSMS = (CheckBox) findViewById(R.id.cb_settings_LogAllSMS);
    	final CheckBox checkBox_EnableSound = (CheckBox) findViewById(R.id.cb_settings_EnableSound);
     
    	
    	checkBox_EnableFwd.setChecked(app_rec.bEnableFwd);
    	checkBox_LogSMS.setChecked(app_rec.bLogAllSMS);
    	checkBox_EnableSound.setChecked(app_rec.bEnableSound);
    	
   	 	final Button button = (Button) findViewById(R.id.btnSettingsApply);
   	 	button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
        	 
        	 app_rec.bEnableFwd = checkBox_EnableFwd.isChecked();
        	 app_rec.bEnableSound = checkBox_EnableSound.isChecked();
        	 app_rec.bLogAllSMS = checkBox_LogSMS.isChecked();
        	 
        	 app_rec.SaveSettings();
        	 
//        	 if (checkBox_EnableFwd.isChecked()) {
//            	 checkBox_EnableFwd.setChecked(false);
//             }
             
        	// Toast.makeText(getApplicationContext(), "Apply", Toast.LENGTH_LONG).show();
        	 finish();
         }
   	 	});
        
    }

    private Object getActionBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_smsfwd_setting, menu);
        return true;
    }



    
}
