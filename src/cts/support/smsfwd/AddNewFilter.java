package cts.support.smsfwd;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddNewFilter extends Activity {

	private String sId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_filter);
        
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null)
        {
        	String sName = bundle.getString("Name");
        	String sPhone = bundle.getString("Phone");
        	String sPayload = bundle.getString("Payload");
        	sId = bundle.getString("ID");
        	
        	TextView nume = (TextView)findViewById(R.id.editText1_Name);
        	TextView payload = (TextView)findViewById(R.id.editText2_Filter);
        	TextView phone1 = (TextView)findViewById(R.id.editText3_Phone);
        	
        	nume.setText(sName);
        	payload.setText(sPayload);
        	phone1.setText(sPhone);
        }
        
        final Button button = (Button) findViewById(R.id.btn_add_new_filter);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	OnClickOk();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_new_filter, menu);
        return true;
    }
    

    
    
    public void OnClickOk()
    {
    	DataSource d = new DataSource(this);
    	d.open();
    	TextView nume = (TextView)findViewById(R.id.editText1_Name);
    	TextView payload = (TextView)findViewById(R.id.editText2_Filter);
    	TextView phone1 = (TextView)findViewById(R.id.editText3_Phone);
    	String st_nume = nume.getText().toString();
    	String st_payload = payload.getText().toString();
    	String st_phone = phone1.getText().toString();
    	
    	if (sId.length() > 0)
    		d.CrateFiltreEntry_with_ID(sId,st_payload,st_phone,st_nume);
    	else
    		d.CrateFiltreEntry(st_payload,st_phone,st_nume);
    			
    	d.close();
    	finish();
    }
}
