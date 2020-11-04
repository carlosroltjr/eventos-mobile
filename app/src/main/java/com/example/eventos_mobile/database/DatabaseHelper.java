package com.example.eventos_mobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eventos_mobile.database.contract.EventosContract;
import com.example.eventos_mobile.database.contract.LocalContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "db.eventos";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(EventosContract.criarTabela());
        sqLiteDatabase.execSQL(LocalContract.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(EventosContract.removerTabela());
        sqLiteDatabase.execSQL(LocalContract.removerTabela());
        sqLiteDatabase.execSQL(LocalContract.criarTabela());
        sqLiteDatabase.execSQL(EventosContract.criarTabela());
    }
}
