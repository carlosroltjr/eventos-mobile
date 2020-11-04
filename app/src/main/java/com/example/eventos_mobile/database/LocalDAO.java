package com.example.eventos_mobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.eventos_mobile.database.entity.EventosEntity;
import com.example.eventos_mobile.database.entity.LocalEntity;
import com.example.eventos_mobile.modelo.Evento;
import com.example.eventos_mobile.modelo.Local;

import java.util.ArrayList;
import java.util.List;

public class LocalDAO {

    private DatabaseGateway databaseGateway;
    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + LocalEntity.TABLE_NAME;

    public LocalDAO(Context context) {
        databaseGateway = DatabaseGateway.getInstance(context);
    }

    public boolean salvar(Local local) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(LocalEntity.COLUMN_NAME_NOME, local.getNome());
        contentValues.put(LocalEntity.COLUMN_NAME_CIDADE, local.getCidade());
        contentValues.put(LocalEntity.COLUMN_NAME_CAPACIDADE, local.getCapacidade());

        if (local.getId() > 0) {
            return databaseGateway.getSqLiteDatabase().update(LocalEntity.TABLE_NAME,
                    contentValues,
                    LocalEntity._ID + "=?",
                    new String[]{String.valueOf(local.getId())}) > 0;
        }

        return databaseGateway.getSqLiteDatabase()
                .insert(LocalEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public List<Local> listar() {
        List<Local> locais = new ArrayList<>();

        Cursor cursor = databaseGateway.getSqLiteDatabase().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(LocalEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            int capacidade = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));
            locais.add(new Local(id, nome, cidade, capacidade));
        }

        cursor.close();

        return locais;
    }
}
