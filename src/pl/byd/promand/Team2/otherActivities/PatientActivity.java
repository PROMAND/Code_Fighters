package pl.byd.promand.Team2.otherActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 15.03.13
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class PatientActivity extends SherlockActivity {
    Map<String, String> fields = new HashMap<String, String>();
    private void SaveInformationToDict(){
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

        fields.put("name",name);
        fields.put("surname",surname);
        fields.put("sex",sex);
        fields.put("date_of_birth",date_of_birth);
        fields.put("id_patient",id_patient);
        fields.put("country",country);
        fields.put("postal_code",postal_code);
        fields.put("email",email);
        fields.put("phone",phone);
        fields.put("additional_info",additional_info);
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.patient);
    }

    public void btn_save_patient_click(View v){
        SaveInformationToDict();
    }


}
