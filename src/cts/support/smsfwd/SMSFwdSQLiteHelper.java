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
	
	private static final String DATABASE_NAME_FILTRE = "SMSFwdFiltre.db";
	private static final int DATABASE_VERSION_FILTRE = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE_FILTRE = "create table "
			+ TB_FILTRE + "(" 
			+ FD_FILTRU_ID + " integer primary key autoincrement, "
			+ FD_FILTRU_TEXT + " text not null, "
			+ FD_FILTRU_PHONE + " text not null," 
			+FD_FILTRU_NAME + " text not null)";
	
	public SMSFwdSQLiteHelper(Context context) {
		super(context, DATABASE_NAME_FILTRE, null, DATABASE_VERSION_FILTRE);
	}
	

	@Override
	public void onCreate(SQLiteDatabase database) throws SQLException{
		try {
		database.execSQL(DATABASE_CREATE_FILTRE);
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
		onCreate(db);
	}

}
