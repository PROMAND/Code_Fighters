package pl.byd.promand.Team2.otherActivities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.*;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 15.03.13
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class PatientActivity extends SherlockActivity {
    private ContentValues patientFields = new ContentValues();
    private ContentValues patientAddressFields = new ContentValues();
    private DbData db = new DbData(this);
    private static final String TABLE_PATIENTS = "patients";

    private void SaveInformationToDict(){
        db.open();
        EditText et_name = (EditText)findViewById(R.id.et_name);
        EditText et_surname = (EditText)findViewById(R.id.et_surname);
        EditText et_sex = (EditText)findViewById(R.id.et_sex);
        EditText et_date_of_birth = (EditText)findViewById(R.id.et_date_of_birth);
        EditText et_id_patient = (EditText)findViewById(R.id.et_id_patient);
        EditText et_country = (EditText)findViewById(R.id.et_country);
        EditText et_postal_code = (EditText)findViewById(R.id.et_postal_code);
        EditText et_email = (EditText)findViewById(R.id.et_email);
        EditText et_phone = (EditText)findViewById(R.id.et_phone);
        EditText et_additional_info = (EditText)findViewById(R.id.et_additional_info);

        String name = String.valueOf(et_name.getText());
        String surname = String.valueOf(et_surname.getText());
        String sex = String.valueOf(et_sex.getText());
        String date_of_birth = String.valueOf(et_date_of_birth.getText());
        String id_patient = String.valueOf(et_id_patient.getText());
        String country = String.valueOf(et_country.getText());
        String postal_code = String.valueOf(et_postal_code.getText());
        String email = String.valueOf(et_email.getText());
        String phone = String.valueOf(et_phone.getText());
        String additional_info = String.valueOf(et_additional_info.getText());


        patientFields.put("name", name);
        patientFields.put("surname", surname);
        patientFields.put("sex", sex);
        patientFields.put("date_of_birth", date_of_birth);
        patientFields.put("pesel", id_patient);
        patientAddressFields.put("country", country);
        patientAddressFields.put("postal_code", postal_code);
        patientAddressFields.put("email", email);
        patientAddressFields.put("phone", phone);
        patientFields.put("additional_info", additional_info);

        long test = db.insertPatient(patientFields);
        patientAddressFields.put("patient_id", test);
        db.insertPatientAddress(patientAddressFields);
        db.close();
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.patient);
        DbData db = null;
    }

    public void btn_save_patient_click(View v){
        DbData db = null;
        SaveInformationToDict();
    }


}
