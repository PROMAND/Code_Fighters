package pl.byd.promand.Team2.otherActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 16.03.13
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
public class PayerActivity extends SherlockActivity {
    Map<String, String> payerFields = new HashMap<String, String>();
    private void SaveInformationToDict(){
        EditText et_name_of_company = (EditText)findViewById(R.id.et_name_of_company);
        EditText et_email_payer = (EditText)findViewById(R.id.et_email_payer);
        EditText et_phone_payer = (EditText)findViewById(R.id.et_phone_payer);
        EditText et_address = (EditText)findViewById(R.id.et_address_payer);
        EditText et_additional_info_payer = (EditText)findViewById(R.id.et_additional_info_payer);

        String name_of_company = String.valueOf(et_name_of_company.getText());
        String email_payer = String.valueOf(et_email_payer.getText());
        String phone_payer = String.valueOf(et_phone_payer.getText());
        String address = String.valueOf(et_address.getText());
        String additional_info_payer = String.valueOf(et_additional_info_payer.getText());

        payerFields.put("name_of_company",name_of_company);
        payerFields.put("email", email_payer);
        payerFields.put("phone",phone_payer);
        payerFields.put("address",address);
        payerFields.put("additional_info",additional_info_payer);
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.payer);
    }

    public void btn_save_payer_click(View v){
       SaveInformationToDict();
    }
}
