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
    private static final String myQPatientList = "select * from patients as p LEFT JOIN contacts as c ON p._id = c.patient_id";
    private static final String myQPayerList = "select * from payers";
    private static final String myQVisitsList = "select * from visits";
    private static final String myQMCList = "select * from medical_certifcates";

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
    public ContentValues getPatientById(int id){
        ContentValues temp = new ContentValues();

        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla", "ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery("SELECT * FROM patients WHERE _id=" + id + " LIMIT 1",null);
            result.moveToFirst();
            String name;
            String surname;

            name = result.getString(1);
            surname = result.getString(2);

            temp.put("name",name);
            temp.put("surname",surname);
            result.close();
        }

        close();
        return temp;
    }
    public List<ContentValues> getPatients()  {
       List<ContentValues> listPatient = new ArrayList<ContentValues>();
       if(database!=null && database.isOpen()){
           close();
           Log.e("blabla", "ERROR");
           return null;
       } else {
           open();
           Cursor result = database.rawQuery(myQPatientList, null);
           ContentValues temp;
           result.moveToFirst();
           int id;
           String name;
           String surname;
           String sex;
           String date_of_birth;
           Integer pesel;
           String additional_info;
           String country, state, town;
           Integer postal_code;
           String street, number_of_house, number_of_flat, phone, email;

           Log.v("tag","startLoop");
           if(result.moveToFirst() && result.getCount() >= 1){
               do{
                   temp = new ContentValues();
                   id = result.getInt(0);
                   name = result.getString(1);
                   surname = result.getString(2);
                   sex = result.getString(3);
                   date_of_birth = result.getString(4);
                   pesel = result.getInt(5);
                   additional_info = result.getString(6);
                   country = result.getString(9);
                   state = result.getString(10);
                   town = result.getString(11);
                   postal_code = result.getInt(12);
                   street = result.getString(13);
                   number_of_house = result.getString(14);
                   number_of_flat = result.getString(15);
                   phone = result.getString(16);
                   email = result.getString(17);

                   temp.put("id",id);
                   temp.put("name",name);
                   temp.put("surname",surname);
                   temp.put("sex",sex);
                   temp.put("date_of_birth",date_of_birth);
                   temp.put("pesel",String.valueOf(pesel));
                   temp.put("additional_info",additional_info);
                   temp.put("country",country);
                   temp.put("state",state);
                   temp.put("town",town);
                   temp.put("town",town);
                   temp.put("postal_code",String.valueOf(postal_code));
                   temp.put("street",street);
                   temp.put("number_of_house",number_of_house);
                   temp.put("number_of_flat",number_of_flat);
                   temp.put("phone",phone);
                   temp.put("email",email);

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

    public List<ContentValues> getPayers()  {
        List<ContentValues> listPayers = new ArrayList<ContentValues>();
        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla","ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery(myQPayerList, null);
            ContentValues temp;
            result.moveToFirst();
            int id;
            String name;
            String email;
            String phone;
            String address;
            String additional_info;


            Log.v("tag","startLoop");
            if(result.moveToFirst() && result.getCount() >= 1){
                do{
                    temp = new ContentValues();
                    id = result.getInt(0);
                    name = result.getString(1);
                    email = result.getString(2);
                    phone = result.getString(3);
                    address = result.getString(4);
                    additional_info = result.getString(5);

                    temp.put("id",id);
                    temp.put("name",name);
                    temp.put("email",email);
                    temp.put("phone",phone);
                    temp.put("address",address);
                    temp.put("additional_info",additional_info);

                    listPayers.add(temp);
                }while(result.moveToNext());
            }

            result.close();
            close();
        }
        return listPayers;
    }

    public long insertVisit(ContentValues visit){
        return database.insert("visits",null,visit);
    }

    public List<ContentValues> getVisits()  {
        List<ContentValues> listVisit = new ArrayList<ContentValues>();
        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla","ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery(myQVisitsList, null);
            ContentValues temp;
            result.moveToFirst();
            int id;
            int patient_id;
            String date;
            String time;
            int duration;
            String additional_info;


            Log.v("tag","startLoop");
            if(result.moveToFirst() && result.getCount() >= 1){
                do{
                    temp = new ContentValues();
                    id = result.getInt(0);
                    patient_id = result.getInt(1);
                    date = result.getString(2);
                    time = result.getString(3);
                    duration = result.getInt(4);
                    additional_info = result.getString(5);

                    temp.put("id",id);
                    temp.put("patient_id", patient_id);
                    temp.put("date", date);
                    temp.put("time", time);
                    temp.put("duration", duration);
                    temp.put("additional_info", additional_info);


                    listVisit.add(temp);
                }while(result.moveToNext());
            }

            result.close();
            close();
        }
        return listVisit;
    }

    public long insertMedicalCertificate(ContentValues mc){
        return database.insert("medical_certifcates", null, mc);
    }
    public List<ContentValues> getMedicalCertificate()  {
        List<ContentValues> listVisit = new ArrayList<ContentValues>();
        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla","ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery(myQMCList, null);
            ContentValues temp;
            result.moveToFirst();
            int id;
            int patient_id;
            int payer_id;
            String arrived;

            Log.v("tag","startLoop");
            if(result.moveToFirst() && result.getCount() >= 1){
                do{
                    temp = new ContentValues();
                    id = result.getInt(0);
                    patient_id = result.getInt(1);
                    payer_id = result.getInt(2);
                    arrived = result.getString(3);


                    temp.put("id",id);
                    temp.put("patient_id", patient_id);
                    temp.put("payer_id", payer_id);
                    temp.put("arrived", arrived);

                    listVisit.add(temp);
                }while(result.moveToNext());
            }

            result.close();
            close();
        }
        return listVisit;
    }
    public ContentValues getPayerById(int id){
        ContentValues temp = new ContentValues();

        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla", "ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery("SELECT * FROM payers WHERE _id=" + id + " LIMIT 1",null);
            result.moveToFirst();

            String name, email, phone, address, additional_info;

            name = result.getString(1);
            email = result.getString(2);
            phone = result.getString(3);
            address = result.getString(4);
            additional_info = result.getString(5);

            temp.put("name",name);
            temp.put("email",email);
            temp.put("phone",phone);
            temp.put("address",address);
            temp.put("additional_info",additional_info);
            result.close();
        }

        close();
        return temp;
    }
    public ContentValues getVisitById(int id){
        ContentValues temp = new ContentValues();

        if(database!=null && database.isOpen()){
            close();
            Log.e("blabla", "ERROR");
            return null;
        } else {
            open();
            Cursor result = database.rawQuery("SELECT * FROM visits WHERE _id=" + id + " LIMIT 1",null);
            result.moveToFirst();

            String  date, time, additional_info;
            int patient_id, duration;

            patient_id = result.getInt(1);
            date = result.getString(2);
            time = result.getString(3);
            duration = result.getInt(4);
            additional_info = result.getString(5);

            temp.put("patient_id",patient_id);
            temp.put("date",date);
            temp.put("time",time);
            temp.put("duration",duration);
            temp.put("additional_info",additional_info);
            result.close();
        }

        close();
        return temp;
    }
    public long deleteMedicalCertificate(int id){
        return database.delete("medical_certifcates", "_id="+ id, null);
    }
    public long deletePayer(int id){
        database.delete("medical_certifcates", "payer_id="+ id, null);
        return database.delete("payers", "_id="+ id, null);
    }
    public long deleteVisit(int id){
        database.delete("medical_certifcates", "patient_id="+id, null) ;
        return database.delete("visits", "_id="+ id, null);
    }
    public long deletePatient(int id){
        database.delete("contacts", "patient_id="+id, null) ;

        if(database.rawQuery("SELECT _id FROM visits WHERE patient_id=" + id, null)!=null){
            Cursor cursor = database.rawQuery("SELECT _id FROM visits WHERE patient_id=" + id, null);
            cursor.moveToFirst();
            do{
               if (database.rawQuery("SELECT * FROM medical_certifcates WHERE patient_id="+cursor.getInt(0), null)!=null)
                    {database.delete("medical_certifcates", "patient_id="+cursor.getInt(0), null);}
               if (database.rawQuery("SELECT * FROM visits WHERE _id="+cursor.getInt(0), null)!=null)
                    {database.delete("visits", "_id="+cursor.getInt(0), null);  }
            }while(cursor.moveToNext());
            cursor.close();
        }

        return database.delete("patients", "_id="+ id, null);
    }
}