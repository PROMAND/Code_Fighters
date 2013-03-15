package com.example.SqlDatabasePhyscomp;

public class DbCore {
    private long id;
    private String string;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return string;
    }

    public void setValue(String comment) {
        this.string = string;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return string;
    }
}