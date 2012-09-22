package cts.support.smsfwd;




import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int ID_UP     = 1;
	private static final int ID_DOWN   = 2;
	private static final int ID_RECORDS = 3;
	private static final int ID_EDIT   = 4;
	private static final int ID_ERASE  = 5;	
	private static final int ID_OK     = 6;
	
	private ListView mContactList;
	private int SelectedItemList;
	private SimpleCursorAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RefreshList();
        
		//---------------------

        ActionItem recordsItem 	= new ActionItem(ID_RECORDS, "Records", getResources().getDrawable(R.drawable.menu_search));
        ActionItem editItem 	= new ActionItem(ID_EDIT, "Edit", getResources().getDrawable(R.drawable.menu_info));
        ActionItem eraseItem 	= new ActionItem(ID_ERASE, "Delete", getResources().getDrawable(R.drawable.menu_eraser));
      
		
		final QuickAction quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
		
		//add action items into QuickAction
 
        quickAction.addActionItem(recordsItem);
        quickAction.addActionItem(editItem);
        quickAction.addActionItem(eraseItem);
        
        
        //Set listener for action item clicked
		quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {			
			public void onItemClick(QuickAction source, int pos, int actionId) {				
				ActionItem actionItem = quickAction.getActionItem(pos);
				
				switch(actionId)
				{
				case ID_ERASE:
				{
					mContactList = (ListView) findViewById(R.id.main_listView1);
					String s = mContactList.getItemAtPosition(SelectedItemList).toString();
					Cursor mycursor = (Cursor) mContactList.getItemAtPosition(SelectedItemList);
					
					Toast.makeText(getApplicationContext(), mycursor.getString(0), Toast.LENGTH_LONG).show();
					DataSource db= new DataSource(getApplicationContext());
					db.open();
					db.deleteFilterFromId(mycursor.getString(0));
					db.close();
					RefreshList();
				}
				break;
				case ID_EDIT:
				{
					Bundle bundle2 = new Bundle();
					DataSource datasource = new DataSource(getApplicationContext());
					datasource.open();
					String s = mContactList.getItemAtPosition(SelectedItemList).toString();
					Cursor mycursor = (Cursor) mContactList.getItemAtPosition(SelectedItemList);
					String sId = mycursor.getString(0);
					Filtre filtre_p = datasource.getFiltruFromId(sId);
					datasource.close();
					//String trgTel=target_p.getTargetTelefon();
					bundle2.putString("Phone",filtre_p.getPhone());
					bundle2.putString("Name", filtre_p.getName());
					bundle2.putString("Payload", filtre_p.getPayload());
					bundle2.putString("ID", Integer.toString(filtre_p.getId()));
					
					Intent i = new Intent(getApplicationContext(), AddNewFilter.class);
					
			        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        i.putExtras(bundle2);
			        getApplicationContext().startActivity(i);
				}
				break;
				}
				//here we can filter which action item was clicked with pos or actionId parameter
				
			}
		});
		
		//set listnener for on dismiss event, this listener will be called only if QuickAction dialog was dismissed
		//by clicking the area outside the dialog.
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {			
		
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed", Toast.LENGTH_SHORT).show();
			}
		});
		//---------------
		mContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() 
			{
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				SelectedItemList = position;
				quickAction.show(arg1);
			}
			});
		}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
  
        
        return true;
    }
    
	   private void populateContactList() {
	        // Build adapter with contact entries
		   DataSource ds = new DataSource(this);
		   ds.open();
	        Cursor cursor = ds.getAllCursorFiltre();
	        String[] fields = new String[] {
	                "_id","Name","Phone"
	        };
	       adapter = new SimpleCursorAdapter(this, R.layout.filtru_view, cursor,
	                fields, new int[] {R.id.textView2_ID,R.id.textView1_filtru, R.id.textView1_phone});
	        mContactList.setAdapter(adapter);
	        ds.close();
	    }
	   
	   public boolean onMenuItemSelected(int featureId, MenuItem item) {
	  // public boolean onMenuItemClick(MenuItem item) {
			// TODO Auto-generated method stub
			
			Log.d("dbg", "onMenuItemClick MenuItem="+item.getItemId());
			Toast.makeText(getApplicationContext(), "asdasd", Toast.LENGTH_SHORT).show();
			switch (item.getItemId()) {
				case R.id.menu_add_filter:
				{
					Bundle bundle2 = new Bundle();
				
					bundle2.putString("telefon","");
					Intent i = new Intent(getApplicationContext(), AddNewFilter.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.putExtras(bundle2);
					getApplicationContext().startActivity(i);
					break;
				}
				
				case R.id.menu_settings:
				{
					Intent i = new Intent(getApplicationContext(), SMSFwdSetting.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					getApplicationContext().startActivity(i);
					
					
					break;
				}
			}
			
			return false;
	   }
	   
	 public void RefreshList()  
	 {
		 DataSource ds = new DataSource(this);
	        //ds.open();
	        //ds.CrateFiltreEntry("asasa", "123","ss");
	        //ds.close();
	        //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,c ,columns ,to);
	        mContactList = (ListView) findViewById(R.id.main_listView1);
	        populateContactList();
	        mContactList.setClickable(true);
	     
	 }
	 protected void onResume()
		{
			super.onResume();
			RefreshList();
		}
	 
}
