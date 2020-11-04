package com.example.eventos_mobile.database.entity;

import android.provider.BaseColumns;

public final class LocalEntity implements BaseColumns {

    private  LocalEntity() {}

    public static final String TABLE_NAME = "local";
    public  static final String COLUMN_NAME_NOME = "nome";
    public  static final String COLUMN_NAME_CIDADE = "cidade";
    public  static final String COLUMN_NAME_CAPACIDADE = "capacidade";
}
