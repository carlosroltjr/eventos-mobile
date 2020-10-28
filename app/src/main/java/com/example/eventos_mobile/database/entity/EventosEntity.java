package com.example.eventos_mobile.database.entity;

import android.provider.BaseColumns;

public final class EventosEntity implements BaseColumns {

    private EventosEntity() {}

    public static final String TABLE_NAME = "eventos";
    public static final String COLUMN_NAME_NOME = "evento";
    public static final String COLUMN_NAME_DATA = "data";
    public static final String COLUMN_NAME_LOCAL = "local";
}
