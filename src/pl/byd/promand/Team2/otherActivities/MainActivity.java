package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.SherlockActivity;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;


public class MainActivity extends SherlockActivity {
    DbData datasource;
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
        Intent intent = new  Intent(this, ScheduleActivity.class);
        MainActivity.this.startActivity(intent);
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
}
