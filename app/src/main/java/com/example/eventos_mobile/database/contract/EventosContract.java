package com.example.eventos_mobile.database.contract;

import com.example.eventos_mobile.database.entity.EventosEntity;

public final class EventosContract {

    private EventosContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + EventosEntity.TABLE_NAME + " (" +
                EventosEntity._ID + " INTEGER PRIMARY KEY," +
                EventosEntity.COLUMN_NAME_NOME + " TEXT," +
                EventosEntity.COLUMN_NAME_DATA + " TEXT," +
                EventosEntity.COLUMN_NAME_LOCAL + " TEXT)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + EventosEntity.TABLE_NAME;
    }
}
