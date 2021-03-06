package pl.byd.promand.Team2.otherActivities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

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
    ContentValues payerFields = new ContentValues();
    private DbData db = new DbData(this);
    private void LoadFields(){
        EditText et_name_of_company = (EditText)findViewById(R.id.et_name_of_company);
        EditText et_email_payer = (EditText)findViewById(R.id.et_email_payer);
        EditText et_phone_payer = (EditText)findViewById(R.id.et_phone_payer);
        EditText et_address = (EditText)findViewById(R.id.et_address_payer);
        EditText et_additional_info_payer = (EditText)findViewById(R.id.et_additional_info_payer);
        ContentValues tempPayer = SearchingResultActivity.takePayer;
        et_name_of_company.setText(String.valueOf(tempPayer.get("name")));
        et_email_payer.setText(String.valueOf(tempPayer.get("email")));
        et_phone_payer.setText(String.valueOf(tempPayer.get("phone")));
        et_address.setText(String.valueOf(tempPayer.get("address")));
        et_additional_info_payer.setText(String.valueOf(tempPayer.get("additional_info")));
    }
    private String SaveInformationToDict(){

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
        if(name_of_company.equals("")) return "Empty name of company field!";
        payerFields.put("name",name_of_company);
        payerFields.put("email", email_payer);
        payerFields.put("phone",phone_payer);
        payerFields.put("address",address);
        payerFields.put("additional_info",additional_info_payer);
        if(PayerActivity.this.getIntent().getStringExtra("result")!=null && PayerActivity.this.getIntent().getStringExtra("result").equals("edPayer")){
           payerFields.put("_id",Integer.parseInt(String.valueOf(SearchingResultActivity.takePayer.get("id"))));
            db.open();
           long temp = db.updatePayer(payerFields);
            db.close();
            if(temp!=1){
            SearchingResultActivity.takePayer = db.getPayerById(Integer.parseInt(String.valueOf(SearchingResultActivity.takePayer.get("id"))));
            }
            else return "This payer exist!";
        }
        else {
            db.open();
            long temp = db.insertPayer(payerFields);
            db.close();
            if(temp == 1){
               return "This payer exist!";
            }
        }

       return null;
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.payer);

        if(PayerActivity.this.getIntent().getStringExtra("result")!=null && PayerActivity.this.getIntent().getStringExtra("result").equals("edPayer")){
            LoadFields();
        }
    }

    public void btn_save_payer_click(View v){


       String temp = SaveInformationToDict();
        if(temp !=null){
            Toast.makeText(this,temp,1).show();
        }
        else{
            onBackPressed();
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
}
