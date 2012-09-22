package cts.support.smsfwd;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SMSFwdSQLiteHelper extends SQLiteOpenHelper {

	public static final String TB_FILTRE = "Filtre";
	
	public static final String FD_FILTRU_ID = "_id";
	public static final String FD_FILTRU_TEXT = "Payload";
	public static final String FD_FILTRU_PHONE = "Phone";
	public static final String FD_FILTRU_NAME = "Name";
	
	
	public static final String TB_SETTINGS = "Settings";
	
	public static final String FD_SETTINGS_ID = "_id";
	public static final String FD_SETTINGS_ATTR_NAME = "AttributeName";
	public static final String FD_SETTINGS_ATTR_VAL = "AttributeValue";
	
	
	public static final String TB_LOGS = "Logs";
	
	public static final String FD_LOGS_ID = "_id";
	public static final String FD_LOGS_DATE = "Date";
	public static final String FD_LOGS_TYPE = "Type";
	public static final String FD_LOGS_SRC = "Source";
	public static final String FD_LOGS_TEXT = "Text";
	
	
	
	private static final String DATABASE_NAME_FILTRE = "SMSFwdFiltre.db";
	private static final int DATABASE_VERSION_FILTRE = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE_FILTRE = "create table "
			+ TB_FILTRE + "(" 
			+ FD_FILTRU_ID + " integer primary key autoincrement, "
			+ FD_FILTRU_TEXT + " text not null, "
			+ FD_FILTRU_PHONE + " text not null," 
			+FD_FILTRU_NAME + " text not null)";
	
	private static final String DATABASE_CREATE_SETTINGS = "create table "
			+ TB_SETTINGS +"("
			+ FD_SETTINGS_ID + " integer primary key autoincrement, "
			+ FD_SETTINGS_ATTR_NAME + " text not null, "
			+ FD_SETTINGS_ATTR_VAL + " text not null)";
	
	private static final String DATABASE_CREATE_LOGS = "create table "
			+ TB_LOGS + "("
			+ FD_LOGS_ID + " integer primary key autoincrement, "
			+ FD_LOGS_DATE + " text not null, "
			+ FD_LOGS_TYPE + " text not null, "
			+ FD_LOGS_SRC + " text not null, "
			+ FD_LOGS_TEXT + " text not null)";
	
	
	public SMSFwdSQLiteHelper(Context context) {
		super(context, DATABASE_NAME_FILTRE, null, DATABASE_VERSION_FILTRE);
	}
	

	@Override
	public void onCreate(SQLiteDatabase database) throws SQLException{
		try {
		database.execSQL(DATABASE_CREATE_FILTRE);
		database.execSQL(DATABASE_CREATE_SETTINGS);
		database.execSQL(DATABASE_CREATE_LOGS);
		}
		catch (SQLException e)
		{
		
			Log.v("a1", e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SMSFwdSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + TB_FILTRE);
		db.execSQL("DROP TABLE IF EXISTS" + TB_LOGS);
		db.execSQL("DROP TABLE IF EXISTS" + TB_SETTINGS);
		
		onCreate(db);
	}

}
