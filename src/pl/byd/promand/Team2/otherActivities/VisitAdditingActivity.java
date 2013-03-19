package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;

public class VisitAdditingActivity extends SherlockActivity {
   DatePickerDialog dataPicker;
    private String _Year;
    private String _Month;
    private String _Day;
   TimePickerDialog timePicker;
    private String _Hour;
    private String _Min;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit);
    }
    public void btn_chooseDate_click(View v){
        dataPicker = new DatePickerDialog(this,mDateSetListener,2013,2,10);
        dataPicker.show();
    }
    public void btn_chooseTime_click(View v){
       timePicker = new TimePickerDialog(this,mTimeSetListener,19,00,true);
       timePicker.show();
    }
    @Override
    public void onResume() {
        if(SearchingResultActivity.takePatient!=null){
            TextView tv = (TextView)findViewById(R.id.tv_yourResult);
            tv.setText(String.valueOf(SearchingResultActivity.takePatient.get("name")) + " " + String.valueOf(SearchingResultActivity.takePatient.get("surname")));
        }
        super.onResume();
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
                    TextView tv_result = (TextView)findViewById(R.id.tv_yourDateResult);
                    tv_result.setText(_Day + "/" + _Month + "/" + _Year);
                }
            };
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute) {
                    if(selectedHour < 10){
                        _Hour = "0" + String.valueOf(selectedHour);
                    }
                    else {
                        _Hour = String.valueOf(selectedHour);
                    }
                    if(selectedMinute < 10) {
                        _Min = "0" + String.valueOf(selectedMinute);
                    }
                    else {
                        _Min = String.valueOf(selectedMinute);
                    }

                    TextView tv_result = (TextView)findViewById(R.id.tv_yourTimeResult);
                    tv_result.setText(_Hour + ":" + _Min );
                }
            };
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
    public void btn_submit_click(View v){
        intent = new Intent(this,SearchingResultActivity.class);
        intent.putExtra("result","Patient");
        this.startActivity(intent);
    }
}

