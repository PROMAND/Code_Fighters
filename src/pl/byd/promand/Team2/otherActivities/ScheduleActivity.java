package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;

public class ScheduleActivity extends SherlockActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
    }
    public void btn_back_click(View v){
       super.onBackPressed();
    }
}
