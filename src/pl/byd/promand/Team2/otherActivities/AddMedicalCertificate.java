package pl.byd.promand.Team2.otherActivities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 18.03.13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class AddMedicalCertificate extends SherlockActivity {
    ContentValues medical_certificate = new ContentValues();
    Integer visit_id;
    Integer payer_id;
    ContentValues temp = SearchingResultActivity.takeMedicalCertificate;

    private void SaveInformationToDict(){
        if(SearchingResultActivity.takePayer!=null){
            payer_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takePayer.get("id")));
        }
        if(SearchingResultActivity.takeVisit!=null){
            visit_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takeVisit.get("id")));
        }
        medical_certificate.put("patient_id",visit_id);
        medical_certificate.put("payer_id",payer_id);
        CheckBox cb = (CheckBox)findViewById(R.id.cb_appear);
        medical_certificate.put("arrived",String.valueOf(cb.isChecked()));
        if(AddMedicalCertificate.this.getIntent().getStringExtra("result")!=null && AddMedicalCertificate.this.getIntent().getStringExtra("result").equals("edMedical"))
        {
            medical_certificate.put("_id",String.valueOf(temp.get("id")));


        }
    }
    public void LoadFields(){
        ContentValues temp = SearchingResultActivity.takeMedicalCertificate;
        payer_id = Integer.parseInt(String.valueOf(temp.get("payer_id")));
        visit_id = Integer.parseInt(String.valueOf(temp.get("patient_id")));
        TextView yourPayer = (TextView)findViewById(R.id.tv_yourPayerResult);
        TextView yourVisit = (TextView)findViewById(R.id.tv_yourVisitResult);
        DbData db = new DbData(this);

        ContentValues visit = db.getVisitById(visit_id);
        ContentValues payer = db.getPayerById(payer_id);
        yourPayer.setText(String.valueOf(payer.get("name")));
        yourVisit.setText(String.valueOf(visit.get("date")) + " " + String.valueOf(visit.get("time")));

        CheckBox ch = (CheckBox)findViewById(R.id.cb_appear);
        ch.setChecked(Boolean.valueOf(String.valueOf(temp.get("arrived"))));
        Log.e("tag","blabla");
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.medical_certificate);
        if(AddMedicalCertificate.this.getIntent().getStringExtra("result")!=null && AddMedicalCertificate.this.getIntent().getStringExtra("result").equals("edMedical"))
        {
            LoadFields();
        }
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
    public void btn_select_payer_click(View v){

        intent = new Intent(this,SearchingResultActivity.class);
        intent.putExtra("result", "Payer");
        this.startActivity(intent);

    }
    public void btn_select_visit_click(View v){

        intent = new Intent(this,SearchingResultActivity.class);
        intent.putExtra("result","Visit");
        this.startActivity(intent);

    }
    public void btn_save_medical_certificate_click(View v){
      DbData db = new DbData(this);

      SaveInformationToDict();
        if(AddMedicalCertificate.this.getIntent().getStringExtra("result")!=null && AddMedicalCertificate.this.getIntent().getStringExtra("result").equals("edMedical"))
        {   db.open();
            db.updateMedicalCertifcates(medical_certificate);
            db.close();
            SearchingResultActivity.takeMedicalCertificate = db.getMedicalCertificateById(Integer.parseInt(String.valueOf(SearchingResultActivity.takeMedicalCertificate.get("id"))));
        }else{
            db.open();
            db.insertMedicalCertificate(medical_certificate);
            db.close();
        }


      onBackPressed();
    }
}
