package pl.byd.promand.Team2.sqlWorker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public long insertPatient(ContentValues patient) {

        database.insert( "patients", null, patient);
        String selectQuery = "SELECT  * FROM  patients";
        Cursor cursor = database.rawQuery(selectQuery, null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }
    public long insertPatientAddress(ContentValues patient) {
       return database.insert( "contacts", null, patient);
    }
}