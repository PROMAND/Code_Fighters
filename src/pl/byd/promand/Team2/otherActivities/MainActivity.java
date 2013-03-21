package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends SherlockActivity {
    DbData datasource;
    String _Month,_Year,_Day;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.main);
        datasource = new DbData(this);
        datasource.open();
    }
    public void btn_searchEdit_click(View v){
        Intent intent = new Intent(this, SearchEditActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void btn_addVisit_click(View v){
        Intent intent = new  Intent(this, VisitAdditingActivity.class);
        MainActivity.this.startActivity(intent);
    }
    public void btn_schedule_click(View v){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String DATE = simpleDateFormat.format(new Date());
        _Year =  DATE.substring(0,4);
        _Month = DATE.substring(5,7);
        _Day = DATE.substring(8,10);
        DatePickerDialog pickerDialog = new DatePickerDialog(this,mDateSetListener,Integer.parseInt(_Year),Integer.parseInt(_Month)-1,Integer.parseInt(_Day));
        //Log.e("TAG", _Day);
        pickerDialog.show();

    }
    public void btn_medical_certificate_click(View v){
        Intent intent = new Intent(this,AddMedicalCertificate.class);
        MainActivity.this.startActivity(intent);
    }

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
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    _Year = String.valueOf(year);
                    monthOfYear++;
                    if(monthOfYear<10){
                        _Month = "0" + String.valueOf(monthOfYear);
                    }
                    else {
                        _Month = String.valueOf(monthOfYear);
                    }
                    if(dayOfMonth<10){
                        _Day = "0" + String.valueOf(dayOfMonth);
                    }
                    else{
                        _Day = String.valueOf(dayOfMonth);
                    }


                    Intent intent = new  Intent(MainActivity.this, ScheduleActivity.class);
                    intent.putExtra("result",_Year+"-"+_Month+"-"+_Day);
                    MainActivity.this.startActivity(intent);
                }
            };
}
