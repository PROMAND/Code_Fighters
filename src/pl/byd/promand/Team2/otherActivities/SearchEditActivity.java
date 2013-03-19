package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;

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
}
