package com.example.eventos_mobile.database.contract;

import com.example.eventos_mobile.database.entity.EventosEntity;
import com.example.eventos_mobile.database.entity.LocalEntity;

public final class EventosContract {

    private EventosContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + EventosEntity.TABLE_NAME + " (" +
                EventosEntity._ID + " INTEGER PRIMARY KEY," +
                EventosEntity.COLUMN_NAME_NOME + " TEXT," +
                EventosEntity.COLUMN_NAME_DATA + " TEXT," +
                EventosEntity.COLUMN_NAME_ID_LOCAL + " INTEGER," +
                "FOREIGN KEY (" + EventosEntity.COLUMN_NAME_ID_LOCAL + ") REFERENCES " +
                LocalEntity.TABLE_NAME + "(" + LocalEntity._ID + "))";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + EventosEntity.TABLE_NAME;
    }
}
