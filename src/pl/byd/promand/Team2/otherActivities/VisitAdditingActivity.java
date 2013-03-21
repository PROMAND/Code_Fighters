package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

public class VisitAdditingActivity extends SherlockActivity {
   DatePickerDialog dataPicker;
    ContentValues patient;
    private String _Year;
    private String _Month;
    private String _Day;
   TimePickerDialog timePicker;
    private String _Hour;
    private String _Min;
    private ContentValues visitFields = new ContentValues();
    private DbData db = new DbData(this);
    private String SaveInformationToDict(){
        long received = 0;


        TextView tv_date = (TextView)findViewById(R.id.tv_yourDateResult);
        TextView tv_time = (TextView)findViewById(R.id.tv_yourTimeResult);
        Spinner sp_duration = (Spinner)findViewById(R.id.sp_chooseLength);
        EditText et_additional_info_visit = (EditText)findViewById(R.id.et_information);
        Integer patient_id;
        if(SearchingResultActivity.takePatient == null){
            if(SearchingResultActivity.takeVisit == null) return "Choose patient";
            patient_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takeVisit.get("patient_id")));
        }
        else {
            patient_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takePatient.get("id")));
        }
        String time = String.valueOf(tv_time.getText());
        if(time.equals(getString(R.string.temp))) return "Choose time!";
        String date = String.valueOf(tv_date.getText());
        if(date.equals(getString(R.string.temp))) return "Choose date!";
        Integer duration = Integer.parseInt(String.valueOf(sp_duration.getSelectedItem()));
        String additional_info_visit = String.valueOf(et_additional_info_visit.getText());

        visitFields.put("patient_id",patient_id);
        visitFields.put("date", date);
        visitFields.put("time",time);
        visitFields.put("duration",duration);
        visitFields.put("additional_info",additional_info_visit);

        Intent temp = this.getIntent();
        String extra = temp.getStringExtra("result");
        db.open();
        if(extra!=null && extra.equals("edVisit")){
         visitFields.put("_id",Integer.parseInt(String.valueOf(SearchingResultActivity.takeVisit.get("id"))));
         received = db.updateVisit(visitFields);
        }
        else{
            received = db.insertVisit(visitFields);
        }
        db.close();
        if(received==1){
            return "This visit exist";
        }
        if(visitFields.get("_id")!=null && received!=1) {
        SearchingResultActivity.takeVisit = db.getVisitById(Integer.parseInt(String.valueOf(SearchingResultActivity.takeVisit.get("id"))));
        }


      return null;
    }
    private void LoadFields(){
        ContentValues tempVisit = SearchingResultActivity.takeVisit;
        TextView tv_date = (TextView)findViewById(R.id.tv_yourDateResult);
        TextView tv_time = (TextView)findViewById(R.id.tv_yourTimeResult);
        TextView tv_patient = (TextView)findViewById(R.id.tv_yourPatientResult);
        EditText et_info = (EditText)findViewById(R.id.et_information);
        tv_date.setText(String.valueOf(tempVisit.get("date")));
        tv_time.setText(String.valueOf(tempVisit.get("time")));

        patient = db.getPatientById(Integer.parseInt(String.valueOf(tempVisit.get("patient_id"))));
        tv_patient.setText(String.valueOf(patient.get("name"))+ " " + String.valueOf(patient.get("surname")));
        et_info.setText(String.valueOf(tempVisit.get("additional_info")));
        //TO DO
        Spinner sp = (Spinner)findViewById(R.id.sp_chooseLength);
        if(Integer.parseInt(String.valueOf(tempVisit.get("duration"))) == 30){
            sp.setSelection(1);
        }
        if(Integer.parseInt(String.valueOf(tempVisit.get("duration"))) == 45){
            sp.setSelection(2);
        }
        if(Integer.parseInt(String.valueOf(tempVisit.get("duration"))) == 60) {
            sp.setSelection(3);

        }
        dataPicker = new DatePickerDialog(this,mDateSetListener,2013,2,10);//new DatePickerDialog(this,mDateSetListener,Integer.parseInt(String.valueOf(tempVisit.get("date")).substring(String.valueOf(tempVisit.get("date")).lastIndexOf('/')),4)
                //,Integer.parseInt(String.valueOf(tempVisit.get("date")).substring(String.valueOf(tempVisit.get("date")).indexOf('/')),2),Integer.parseInt(String.valueOf(tempVisit.get("date")).substring(0,2)));
        timePicker = new TimePickerDialog(this,mTimeSetListener,19,00,true);



    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.visit);

        Intent temp = this.getIntent();
        String result = temp.getStringExtra("result");
        dataPicker = new DatePickerDialog(this,mDateSetListener,2013,2,10);
        timePicker = new TimePickerDialog(this,mTimeSetListener,19,00,true);
        if(result!=null && result.equals("edVisit")){

            LoadFields();
        }
    }
    public void btn_chooseDate_click(View v){

        dataPicker.show();
    }
    public void btn_chooseTime_click(View v){

       timePicker.show();
    }
    @Override
    public void onResume() {
        if(SearchingResultActivity.takePatient!=null){
            TextView tv = (TextView)findViewById(R.id.tv_yourPatientResult);
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
                    tv_result.setText(_Year + "-" + _Month + "-" + _Day);
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
    public void btn_save_visit_info_click(View v){
        Intent temp = VisitAdditingActivity.this.getIntent();
        String extra = temp.getStringExtra("result");

            String received = SaveInformationToDict();
            if(received != null){
                Toast.makeText(this,received,1).show();
            }else{
                onBackPressed();

            }








    }
}

