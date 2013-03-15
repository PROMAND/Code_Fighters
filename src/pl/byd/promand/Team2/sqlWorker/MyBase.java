package com.example.SqlDatabasePhyscomp;

import android.app.Activity;
import android.os.Bundle;

public class MyBase extends Activity {
    /**
     * Called when the activity is first created.
     */
    private DbData datasource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        datasource = new DbData(this);
        datasource.open();
    }
}


