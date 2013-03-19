package pl.byd.promand.Team2.otherActivities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

    private void SaveInformationToDict(){

        payer_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takePayer.get("id")));
        visit_id = Integer.parseInt(String.valueOf(SearchingResultActivity.takeVisit.get("id")));
        medical_certificate.put("patient_id",visit_id);
        medical_certificate.put("payer_id",payer_id);
        CheckBox cb = (CheckBox)findViewById(R.id.cb_appear);
        medical_certificate.put("arrived",String.valueOf(cb.isChecked()));
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.medical_certificate);
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
      db.open();
      SaveInformationToDict();
      db.insertMedicalCertificate(medical_certificate);
      db.close();
    }
}
