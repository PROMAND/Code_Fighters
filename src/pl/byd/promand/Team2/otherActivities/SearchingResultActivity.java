package pl.byd.promand.Team2.otherActivities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;
import com.promand.Team2.R;
import pl.byd.promand.Team2.sqlWorker.DbData;

/**
 * Created with IntelliJ IDEA.
 * User: Asus
 * Date: 18.03.13
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class SearchingResultActivity extends SherlockActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        DbData db = new DbData(this);
        db.getPatients();

    }
}