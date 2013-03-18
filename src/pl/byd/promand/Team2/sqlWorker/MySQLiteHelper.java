package pl.byd.promand.Team2.sqlWorker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {

    //main db definitions
    private static final String DATABASE_NAME = "physcomp.db";
    private static final int DATABASE_VERSION = 3;

    //table names definition
    public static final String TABLE_USERS = "user_doc";
    public static final String TABLE_PATIENTS = "patients";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_VISITS = "visits";
    public static final String TABLE_DRUGSLIST = "drugs_list";
    public static final String TABLE_DRUGCODES = "drug_codes";
    public static final String TABLE_PAYERS = "payers";
    public static final String TABLE_MEDICAL = "medical_certifcates";

    //columns names definition
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "parssword";
    public static final String DRUGCODE = "drugcode";
    public static final String PATIENT_ID = "patient_id";
    public static final String CODE = "code";
    public static final String SEX = "sex";
    public static final String DATEOFBIRTH = "date_of_birth";
    public static final String PESEL = "pesel";
    public static final String ADDITIONAL_INFO = "additional_info";
    public static final String DRUG_LIST_ID = "drug_list_id";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String TOWN = "town";
    public static final String POSTAL_CODE = "postal_code";
    public static final String STREET = "street";
    public static final String NUMBER_OF_HOUSE = "number_of_house";
    public static final String NUMBER_OF_FLAT = "number_of_flat";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String DURATION = "duration";
    public static final String PAYER_ID = "payer_id";
    public static final String ARRIVED = "arrived";
    public static final String ADDRESS = "address";

    // Database creation sql statement
    private static final String USERS_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_USERS + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + LOGIN  + " text, "
                + PASSWORD + " text, "
                + NAME_FIELD + " text, "
                + SURNAME_FIELD + " text  );";
    private static final String PATIENTS_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_PATIENTS + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + NAME_FIELD + " text, "
                + SURNAME_FIELD + " text, "
                + SEX + " text, "
                + DATEOFBIRTH + " text, "
                + PESEL + " integer unique not null, "
                + ADDITIONAL_INFO + " text, "
                + DRUG_LIST_ID + "text  );";
    private static final String CONTACTS_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_CONTACTS + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + COUNTRY + " text, "
                + STATE + " text, "
                + TOWN + " text, "
                + POSTAL_CODE + " integer, "
                + STREET + " text, "
                + NUMBER_OF_HOUSE + " text, "
                + NUMBER_OF_FLAT + " text, "
                + PHONE + " text, "
                + EMAIL + " text, "
                + PATIENT_ID + " integer );" ;
    private static final String VISITS_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_VISITS + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + PATIENT_ID + " integer, "
                + DATE + " text, "
                + TIME + " text, "
                + DURATION + " integer, "
                + ADDITIONAL_INFO + " text ); ";
    private static final String DRUGSLIST_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_DRUGSLIST + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + PATIENT_ID + " integer, "
                + DRUGCODE + " integer ); ";
    private static final String DRUGCODES_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_DRUGCODES + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + NAME_FIELD + " text, "
                + CODE + " text, "
                + ADDITIONAL_INFO + " text ); ";
    private static final String PAYERS_CREATE =
            "create table IF NOT EXISTS "
                + TABLE_PAYERS + "("
                + ID_FIELD + " integer primary key autoincrement, "
                + NAME_FIELD + " text, "
                + EMAIL + " text, "
                + PHONE + " text, "
                + ADDRESS + " text, "
                + ADDITIONAL_INFO + " text ); ";
    private static final String MEDICAL_CREATE =
            "create table IF NOT EXISTS "
                    + TABLE_MEDICAL + "("
                    + ID_FIELD + " integer primary key autoincrement, "
                    + PATIENT_ID + " integer, "
                    + PAYER_ID + " integer, "
                    + ARRIVED + " text, "
                    + DRUG_LIST_ID + " integer ); ";
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_CREATE);
        db.execSQL(PATIENTS_CREATE);
        db.execSQL(CONTACTS_CREATE);
        db.execSQL(VISITS_CREATE);
        db.execSQL(DRUGSLIST_CREATE);
        db.execSQL(DRUGCODES_CREATE);
        db.execSQL(PAYERS_CREATE);
        db.execSQL(MEDICAL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGSLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRUGCODES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICAL);
        onCreate(db);
    }

}