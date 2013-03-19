package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;

import java.util.ArrayList;

public class SearchEditActivity extends SherlockActivity {
    Spinner spinner;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        spinner = (Spinner)findViewById(R.id.sp_type);
        spinner.setOnItemSelectedListener(spinnerListener);
    }
    private  AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Object item = parent.getItemAtPosition(pos);
            Button btn = (Button)findViewById(R.id.btn_add);
            /*switch (String.valueOf(item)){
                case "Visit": {
                    btn.setEnabled(false);
                    break;
                }
                case "Patient":{
                    btn.setEnabled(true);
                    intent = new Intent(SearchEditActivity.this, PatientActivity.class);
                    break;
                }
                case "Payer":{
                    btn.setEnabled(true);
                    intent = new Intent(SearchEditActivity.this, PayerActivity.class);
                    break;
                }
                case "Medical certificate":{
                    btn.setEnabled(true);
                    intent = new Intent(SearchEditActivity.this, AddMedicalCertificate.class);
                    break;
                }
            }  */
            if(item.equals("Visit")){
                btn.setEnabled(false);
            }
            else {
                if(item.equals("Patient")){
                    btn.setEnabled(true);
                    intent = new Intent(SearchEditActivity.this, PatientActivity.class);

                }
                else{
                    if(item.equals("Payer")){
                     btn.setEnabled(true);
                    intent = new Intent(SearchEditActivity.this, PayerActivity.class);
                    }
                    else{
                        btn.setEnabled(true);
                        intent = new Intent(SearchEditActivity.this, AddMedicalCertificate.class);
                    }
                }
            }
        }
        public void onNothingSelected(AdapterView<?> parent) {
        }

    };
    public void btn_search_click(View v){
         Intent searchIntent = new Intent(SearchEditActivity.this, SearchingResultActivity.class);
        Spinner sp = (Spinner)findViewById(R.id.sp_type);

        searchIntent.putExtra("result", String.valueOf(sp.getSelectedItem()));
        Log.e("ERROR","Start intent");
        this.startActivity(searchIntent);
    }
    public void btn_add_click(View v){
            SearchEditActivity.this.startActivity(intent);
    }
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
    public void onResume(){
        if(String.valueOf(spinner.getSelectedItem()).equals("Patient") && SearchingResultActivity.takePatient!=null){
        ArrayList<String> generalList = new ArrayList<String>();
        generalList.add("Name: " + String.valueOf(SearchingResultActivity.takePatient.get("name")));
        generalList.add("Surname: " + String.valueOf(SearchingResultActivity.takePatient.get("surname")));
        generalList.add("Sex: " + String.valueOf(SearchingResultActivity.takePatient.get("sex")));
        generalList.add("Date of birth: " + String.valueOf(SearchingResultActivity.takePatient.get("date_of_birth")));
        generalList.add("Pesel: " + String.valueOf(SearchingResultActivity.takePatient.get("pesel")));
        generalList.add("Country: " + String.valueOf(SearchingResultActivity.takePatient.get("country")));
        generalList.add("State: " + String.valueOf(SearchingResultActivity.takePatient.get("state")));
        generalList.add("City: " + String.valueOf(SearchingResultActivity.takePatient.get("town")));
            generalList.add("Postal code: " + String.valueOf(SearchingResultActivity.takePatient.get("postal_code")));
            generalList.add("Street: " + String.valueOf(SearchingResultActivity.takePatient.get("street")));
            generalList.add("Number of house: " + String.valueOf(SearchingResultActivity.takePatient.get("number_of_house")));
            generalList.add("Number of flat: " + String.valueOf(SearchingResultActivity.takePatient.get("number_of_flat")));
            generalList.add("Phone: " + String.valueOf(SearchingResultActivity.takePatient.get("phone")));
            generalList.add("E-mail: " + String.valueOf(SearchingResultActivity.takePatient.get("email")));
        ArrayAdapter adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                generalList);
        ListView lv = (ListView)findViewById(R.id.lv_result);
       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String name= generalList.get(position);
              ////  takeVisit = null;
              //  takePatient = null;
              //  takePayer = listResult.get(position);
                //String pesel = String.valueOf(temp.get("pesel"));
              //  SearchingResultActivity.this.onDestroy();
                // Toast.makeText(SearchingResultActivity.this, pesel, 1000).show();
            }
        }); */
        lv.setAdapter(adapter);

    }
        super.onResume();
   }
}
