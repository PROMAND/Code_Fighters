package pl.byd.promand.Team2.otherActivities;
import java.text.SimpleDateFormat;
import java.util.*;

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


        String temp =  this.getIntent().getStringExtra("result");
        if(temp!=null){
        listResult = db.getTodayVisits(temp);
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
        adapter=new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1,
                generalList); 
        lv.setAdapter(adapter);
        }
    }
}