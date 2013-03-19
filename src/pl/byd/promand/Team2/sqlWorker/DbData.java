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
    private static final String myQPatientList = "select * from patients";

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
    public long insertPatientAddress(ContentValues patientAddress) {
       return database.insert( "contacts", null, patientAddress);
    }
    public List<ContentValues> getPatients()  {
       List<ContentValues> listPatient = new ArrayList<ContentValues>();
       if(database!=null && database.isOpen()){
           close();
           Log.e("blabla","ERROR");
           return null;
       } else {
           open();
           Cursor result = database.rawQuery(myQPatientList, null);
           ContentValues temp;
           result.moveToFirst();
           int id;
           String name;
           String surname;
           Integer pesel;
           Log.v("tag","startLoop");
           if(result.moveToFirst() && result.getCount() >= 1){
               do{
                   temp = new ContentValues();
                   id = result.getInt(0);
                   name = result.getString(1);
                   surname = result.getString(2);
                   pesel = result.getInt(5);
                   temp.put("id",id);
                   temp.put("name",name);
                   temp.put("surname",surname);
                   temp.put("pesel",String.valueOf(pesel));
                   listPatient.add(temp);
               }while(result.moveToNext());
           }

           result.close();
           close();
       }
        return listPatient;
    }
    public long insertPayer(ContentValues payer) {
        return database.insert( "payers", null, payer);
    }

    public long insertVisit(ContentValues visit){
        return database.insert("visits",null,visit);
    }

}