package pl.byd.promand.Team2.otherActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

import java.util.Dictionary;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 15.03.13
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class PatientActivity extends SherlockActivity {
    Dictionary<String,String> fields;
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.patient);
    }
        DbData datasource;
    public void btn_save_patient_click(View v){
        EditText et_name = (EditText)findViewById(R.id.et_name);
        EditText et_surname = (EditText)findViewById(R.id.et_surname);
        String name = String.valueOf(et_name.getText());
        String surname = String.valueOf(et_surname.getText());
        fields.put("name",name);
        fields.put("surname",surname);
        datasource = new DbData(this);
        datasource.open();

    }

}
