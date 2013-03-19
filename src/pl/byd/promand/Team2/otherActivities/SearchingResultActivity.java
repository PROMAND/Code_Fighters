package pl.byd.promand.Team2.otherActivities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 18.03.13
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class SearchingResultActivity extends SherlockActivity {
    ArrayList<String> generalList = new ArrayList<String>();
    List<ContentValues> listResult;
    public static ContentValues takePatient;
    DbData db;
    ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
      Intent intent = this.getIntent();
       String result = intent.getStringExtra("result");
       if(result!=null){
          if(result.equals("Patient")){
              String name;
              String surname;
              String id;
              String pesel;
              db = new DbData(this);
              Log.e("ERROR", "NULL");
              listResult = new ArrayList<ContentValues>();
              listResult = db.getPatients();
              Log.e("ERROR",String.valueOf(listResult.size()));
              for(ContentValues patient : listResult){
                  name = String.valueOf(patient.get("name"));
                  surname = String.valueOf(patient.get("surname"));
                 // id = String.valueOf(patient.get("id"));
                //  pesel = String.valueOf(patient.get("pesel"));
              generalList.add(name + " " + surname);
              }
              if(generalList == null){
              Log.e("blala","ERRROOROR"); }
              adapter=new ArrayAdapter<String>(this,
                      android.R.layout.simple_list_item_1,
                      generalList);
              ListView lv = (ListView)findViewById(R.id.lv_searching_result);
              lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      //String name= generalList.get(position);
                      takePatient = listResult.get(position);
                      //String pesel = String.valueOf(temp.get("pesel"));
                      onBackPressed();
                     // Toast.makeText(SearchingResultActivity.this, pesel, 1000).show();
                  }
              });
              lv.setAdapter(adapter);
          }

        }

    }
}
