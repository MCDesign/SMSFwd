package cts.support.smsfwd;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataSource {

	
	private SQLiteDatabase database;
	private SMSFwdSQLiteHelper dbHelper;
	private String[] allColumns = { SMSFwdSQLiteHelper.FD_FILTRU_ID,
									SMSFwdSQLiteHelper.FD_FILTRU_TEXT,
									SMSFwdSQLiteHelper.FD_FILTRU_PHONE,
									SMSFwdSQLiteHelper.FD_FILTRU_NAME};
		 
	
	public  DataSource(Context context) {
		Log.w("a1","DataSource crator");
		dbHelper = new SMSFwdSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public int CrateFiltreEntry(String payload, String phone, String name)
	{
		ContentValues values = new ContentValues();
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_TEXT, payload);
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_PHONE, phone);
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_NAME, name);
		
		
		

		long insertId = database.insert(SMSFwdSQLiteHelper.TB_FILTRE, SMSFwdSQLiteHelper.FD_FILTRU_ID + ", "+
				SMSFwdSQLiteHelper.FD_FILTRU_TEXT + ", " + SMSFwdSQLiteHelper.FD_FILTRU_PHONE + ", " + SMSFwdSQLiteHelper.FD_FILTRU_NAME,
				values);
		Log.w("a1", "Created Filtre id="+insertId);
		return 0;
	}
	
	public int CrateFiltreEntry_with_ID(String id,String payload, String phone, String name)
	{
		ContentValues values = new ContentValues();
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_TEXT, payload);
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_PHONE, phone);
		values.put(SMSFwdSQLiteHelper.FD_FILTRU_NAME, name);
		
		
		deleteFilterFromId(id);

		long insertId = database.insert(SMSFwdSQLiteHelper.TB_FILTRE, SMSFwdSQLiteHelper.FD_FILTRU_ID + ", "+
				SMSFwdSQLiteHelper.FD_FILTRU_TEXT + ", " + SMSFwdSQLiteHelper.FD_FILTRU_PHONE + ", " + SMSFwdSQLiteHelper.FD_FILTRU_NAME,
				values);
		Log.w("a1", "Created Filtre id="+insertId);
		return 0;
	}
	
	public Filtre cursorToFiltru(Cursor cursor)
	{
		Filtre filtru = new Filtre();
		filtru.setId(cursor.getInt(0));
		filtru.setPayload(cursor.getString(1));
		filtru.setPhone(cursor.getString(2));
		filtru.setName(cursor.getString(3));
		return filtru;
	}
	
	
	public Filtre getFiltruFromId(String sId)
	{
		Filtre f = null;
		
		Cursor cursor = database.query(SMSFwdSQLiteHelper.TB_FILTRE,
				allColumns, SMSFwdSQLiteHelper.FD_FILTRU_ID + "="+sId, null, null, null, null);
		cursor.moveToFirst();
		f = cursorToFiltru(cursor);
		return f;
	}
	public List <Filtre> getAllFiltre()
	{
		List <Filtre>  filtre = new ArrayList<Filtre>();
		Cursor cursor = database.query(SMSFwdSQLiteHelper.TB_FILTRE,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Filtre filtru = cursorToFiltru(cursor);
			
			filtre.add(filtru);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return filtre;
	}
	
	public Cursor getAllCursor()
	{
		Cursor cursor = database.query(SMSFwdSQLiteHelper.TB_FILTRE,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		return cursor;
	}
	public String getPhoneForThisText(String mesaj)
	{
		Cursor cursor = database.query(SMSFwdSQLiteHelper.TB_FILTRE,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Filtre filtru = cursorToFiltru(cursor);
			if (mesaj.contains(filtru.getPayload()))
			{
				cursor.close();
				return filtru.getPhone();
			}
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return "";
	}
	public void deleteFilterFromId(String id)
	{
		database.delete(SMSFwdSQLiteHelper.TB_FILTRE, SMSFwdSQLiteHelper.FD_FILTRU_ID + " ='" + id + "'", null);
		//database.delete(TrackMe2SQLiteHelper.TABLE_TARGETS,TrackMe2SQLiteHelper.COLUMN_NAME + "='"+name+"'",null );
		//return db.delete(TABLE_SIMPLETABLE_CLIENT1,KEY_EMPLOYEE_NAME + "='"+ContactName+"'",null); 
		return;
	}
}
