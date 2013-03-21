package pl.byd.promand.Team2.otherActivities;


import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import pl.byd.promand.Team2.sqlWorker.DbData;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.BufferType;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;

public class detailInfo extends SherlockActivity{
		
		public String value;
		public TextView text;
	  public Intent intent;
	  private List<ContentValues> array = new ArrayList<ContentValues>();
		 ArrayAdapter<String> adapter;
		 DbData db = new DbData(this);
		 List<String> generalList = new ArrayList<String>();
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

	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detailinfo);
		//text = (TextView) findViewById(R.id.textas);
		TextView textdate = (TextView) findViewById(R.id.textView2);
		TextView texttime = (TextView) findViewById(R.id.textView4);
		TextView textduration = (TextView) findViewById(R.id.textView6);
		TextView textname = (TextView) findViewById(R.id.textView8);
		TextView textsurname = (TextView) findViewById(R.id.textView10);
		TextView textsex = (TextView) findViewById(R.id.textView12);
		TextView textdateofbirth = (TextView) findViewById(R.id.textView14);
		TextView textpesel = (TextView) findViewById(R.id.textView16);
		TextView textadd_info = (TextView) findViewById(R.id.textView18);
 		Bundle extras = getIntent().getExtras();
		String newString;
		if (savedInstanceState == null) {
		    extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {
	        newString= String.valueOf(extras.getInt("data"));
		    }
		} else {
		    newString= (String) savedInstanceState.getSerializable("data");
		}
		//text.setText(newString);
		//newString = extras.getString("data");
		String niam = ""+ newString;
		 Log.v("patent id",niam);
		
		 
		 DbData db = new DbData(this);
		 int id = Integer.parseInt(newString);
		 db.getPatientById(id);
//		newString = newString.replace("{", "");
//		newString = newString.replace("}", "");
		
	
		 	String date = null;
	        String name;
	        String duration = null;
	        Integer patient_id;
	        String surname;
	        String dateofbirth;
	        String pesel;
	        String add_info;
	        String sex;
	        String time = null;
	        final ListView lv= (ListView)findViewById(R.id.list_schedule);
	        List<ContentValues> listResult;
	        listResult = new ArrayList<ContentValues>();
	        int id123 = Integer.parseInt(newString);
	        ContentValues datab = db.getPatientById(id123);
	        ContentValues datav = db.getVisitById(id123);
	        
	            //date = String.valueOf(datab.get("date"));
	            //time = String.valueOf(datab.get("time"));
	            //duration = String.valueOf(datab.get("duration"));
	            pesel = String.valueOf(datab.get("pesel"));
	            dateofbirth = String.valueOf(datab.get("dateofbirth"));
	            name = String.valueOf(datab.get("name"));
	            surname = String.valueOf(datab.get("surname"));
	            add_info = String.valueOf(datab.get("add_info"));
	            sex = String.valueOf(datab.get("sex"));
	            
	            date = String.valueOf(datav.get("date"));
	            time = String.valueOf(datav.get("time"));
	            duration = String.valueOf(datav.get("duration"));
	            
	            
	        
	        
	            
//	            patient_id = Integer.parseInt(String.valueOf(visit.get("patient_id")));
//	            patient = db.getPatientById(patient_id);
//	            array.add(patient);
//	            name = String.valueOf(patient.get("name"));
//	            surname = String.valueOf(patient.get("surname"));
	          
	           // generalList.add(time + "    " + duration + "      " + name + "/" + surname );
	        
	        //	 
//	        adapter=new ArrayAdapter<String>(this,
//	        		android.R.layout.simple_list_item_1,
//	                generalList); 
//	        lv.setAdapter(adapter);
//	        text.setText("Date: "+date +"\n time: "+ time+"\n duration: "  + duration
//	        		+"\n pesel: "+ pesel +"\n Date of birth: "+dateofbirth + "\n Name: " + name + "\n Surname: "+
//	        		surname + "sex" + sex + " add info " + add_info);
	            
	        textdate.setText(date);
	        texttime.setText(time);
	        textduration.setText(duration);
	        textname.setText(name);
	        textsurname.setText(surname);
	        textsex.setText(sex);
	        textdateofbirth.setText(dateofbirth);
	        textpesel.setText(pesel);
	        textadd_info.setText(add_info);
		
	} 

}
