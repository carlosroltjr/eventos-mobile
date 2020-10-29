package com.example.eventos_mobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseGateway {

    private static DatabaseGateway databaseGateway;
    private SQLiteDatabase sqLiteDatabase;

    private DatabaseGateway(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public static DatabaseGateway getInstance(Context context) {
        if (databaseGateway == null) {
            databaseGateway = new DatabaseGateway(context);
        }

        return databaseGateway;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }
}
