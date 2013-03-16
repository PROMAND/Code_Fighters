package pl.byd.promand.Team2.sqlWorker;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbData {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;


    public DbData(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


}