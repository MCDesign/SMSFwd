package cts.support.smsfwd;

import android.content.Context;

public class AppSettingsRecord {



public Boolean bEnableFwd = true;
public Boolean bEnableSound = true;
public Boolean bLogAllSMS = true;
public DataSource db;

private String attrEnableFwd = "EnableFwd";
private String attrEnableSound = "EnableSound";
private String attrLogAllSMS = "LogAllSMS";


private String strEnableFwd = "";
private String strEnableSound = "";
private String strLogAllSMS = "";

public AppSettingsRecord(Context ctx)
{
	db = new DataSource(ctx);
}

void LoadSettings()
{
	db.open();
	
	strEnableFwd = db.getAttributeValue(attrEnableFwd);
	strEnableSound = db.getAttributeValue(attrEnableSound);
	strLogAllSMS   = db.getAttributeValue(attrLogAllSMS);
	
	if (strEnableFwd.contentEquals("1"))
		bEnableFwd = true;
	else
		bEnableFwd = false;
	
	if (strEnableSound.contentEquals("1"))
		bEnableSound = true;
	else
		bEnableSound = false;
	
	if (strLogAllSMS.contentEquals("1"))
		bLogAllSMS = true;
	else
		bLogAllSMS = false;
	
	db.close();
	//db.setAttributeValue("EnableFwd", "1");
	
}

void SaveSettings()
{
	db.open();
	if (bEnableFwd)
		db.setAttributeValue(attrEnableFwd, "1");
	else
		db.setAttributeValue(attrEnableFwd, "0");
	if (bEnableSound)
		db.setAttributeValue(attrEnableSound, "1");
	else
		db.setAttributeValue(attrEnableSound, "0");
	
	if (bLogAllSMS)
		db.setAttributeValue(attrLogAllSMS, "1");
	else
		db.setAttributeValue(attrLogAllSMS, "0");
		
	db.close();
	return;
}


}
