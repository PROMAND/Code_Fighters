package pl.byd.promand.Team2.sqlWorker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbData {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private static final String myQ = "select * from patients as p LEFT JOIN contacts as c on p._id = c._id";

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
    public List<ContentValues> getPatients()  {
        List<ContentValues> listPatient = new ArrayList<ContentValues>();
       if(database!=null && database.isOpen()){
           close();
           return null;
       } else {
           open();
           Cursor result = database.rawQuery(myQ, null);
           ContentValues temp = new ContentValues();
           result.moveToFirst();
           int id;
           String name;
           String surname;
           Log.v("tag","startLoop");
           while(result.moveToNext()){
               id = result.getInt(0);
               name = result.getString(1);
               surname = result.getString(2);
               temp.put("id",id);
               temp.put("name",name);
               temp.put("surname",surname);
               listPatient.add(temp);
           }

           result.close();
           close();
       }
        return listPatient;
    }


}