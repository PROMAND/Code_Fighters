package pl.byd.promand.Team2.otherActivities;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
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
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.medical_certificate);
    }
}
