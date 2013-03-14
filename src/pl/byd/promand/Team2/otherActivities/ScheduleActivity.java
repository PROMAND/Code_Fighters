package pl.byd.promand.Team2.otherActivities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.promand.Team2.R;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 14.03.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class ScheduleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
    }
    public void btn_back_click(View v){
       super.onBackPressed();
    }
}
