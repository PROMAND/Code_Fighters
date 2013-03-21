package pl.byd.promand.Team2.otherActivities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.promand.Team2.R;

import pl.byd.promand.Team2.sqlWorker.DbData;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
 
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
 
public class ScheduleActivity extends SherlockActivity {
	

	 private List<ContentValues> array = new ArrayList<ContentValues>();
	 ArrayAdapter<String> adapter;
	 DbData db = new DbData(this);
	 List<String> generalList = new ArrayList<String>();
	  public Intent intent;
	    //Selecting the menu iteam
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {

	        //Switch for do stuff
	        switch (item.getItemId()) {

	            //Case if user press back button
	            case R.id.btn_back:
	                //Goign back with onBackPressed class

	                this.onBackPressed();
	                return true;
	            case R.id.about:
	                //going to about actv
	                intent = new Intent(this, AboutActivity.class);
	                this.startActivity(intent);

	                return true;
	            case R.id.settings:
	                //going to setting actv
	                intent = new Intent(this,SettingsActivity.class);
	                this.startActivity(intent);


	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getSupportMenuInflater().inflate(R.menu.menu, menu);
	        return true;
	    }
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String date;
        String name;
        String duration;
        Integer patient_id;
        String surname;
        setContentView(R.layout.schedule);
        final ListView lv = (ListView)findViewById(R.id.list_schedule);
        List<ContentValues> listResult;
        listResult = new ArrayList<ContentValues>();
        listResult = db.getVisits();
        
        for(ContentValues visit : listResult){
            ContentValues patient = new ContentValues();
            date = String.valueOf(visit.get("date"));
            String time = String.valueOf(visit.get("time"));
            duration = String.valueOf(visit.get("duration"));
            patient_id = Integer.parseInt(String.valueOf(visit.get("patient_id")));
            patient = db.getPatientById(patient_id);
            array.add(patient);
            name = String.valueOf(patient.get("name"));
            surname = String.valueOf(patient.get("surname"));
            generalList.add(time + "    " + duration + "      " + name + "/" + surname );
        }
 
        
//        //create the grid item mapping 
//        String[] from = new String[] {"time", "duration", "name","surname"};
//        int[] to = new int[] { R.id.time, R.id.duration, R.id.patient_name,R.id.patient_surname};
////        
//        final int[] time = new int[] {1212,1345,1264,1322,3134,2654,4564,4533,7657,5678};
//    	final int[] duration = new int[] {15,30,30,30,15,60,15,30,40,45};
//    	final String[] name = new String[] {"artur","midaugas","mark","nerka","as","gytis","anton","boris","natasa","qwe"};
//    	final String[] surname = new String[] {"niam","ki","kik","asd","we","qweqwe","qwezxc","asdqwe","zcasd","qwez"};
// 
//        // prepare the list of all records
//        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
//        for(int i = 0; i < 10; i++){
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("time", ""+time[i]);
//            map.put("duration",""+ duration[i]);
//            map.put("name", name[i]);
//            map.put("surname",surname[i]);
//            
//            fillMaps.add(map);
//        }
 
        // fill in the grid_item layout
        adapter=new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1,
                generalList); 
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
//				 // selected item
//	              String product = ((TextView) view).getText().toString();
//
//	              // Launching new Activity on selecting single List Item
//	              Intent i = new Intent(getApplicationContext(), detailInfo.class);
//	              // sending data to new activity
//	              i.putExtra("name", product);
//	              startActivity(i);
				 //String selectedFromList = (lv.getItemAtPosition(position).toString());
				 // Log.v("selected id",selectedFromList);
				 int patent_idd = Integer.parseInt(String.valueOf(array.get(position).get("_id")));



				 Intent i  = new Intent(getApplicationContext(),detailInfo.class);
				 i.putExtra("data", patent_idd);
				 startActivity(i);

			}

        });
       
    }
}