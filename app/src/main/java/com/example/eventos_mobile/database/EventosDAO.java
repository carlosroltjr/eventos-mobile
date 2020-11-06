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

public class EventosDAO {

    private String COMANDO_SQL;
    private DatabaseGateway databaseGateway;
    private final String SQL_LISTAR_TODOS = "SELECT eventos._id, nomeEvento, data, " +
            "idlocal, local.nome, cidade, capacidade FROM " + EventosEntity.TABLE_NAME +
            " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + EventosEntity.COLUMN_NAME_ID_LOCAL +
            " = " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID;

    public EventosDAO(Context context) {
        databaseGateway = DatabaseGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(EventosEntity.COLUMN_NAME_NOME, evento.getNomeEvento());
        contentValues.put(EventosEntity.COLUMN_NAME_DATA, evento.getData());
        contentValues.put(EventosEntity.COLUMN_NAME_ID_LOCAL, evento.getLocal().getId());

        if (evento.getId() > 0) {
            return databaseGateway.getSqLiteDatabase().update(EventosEntity.TABLE_NAME,
                    contentValues,
                    EventosEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }

        return databaseGateway.getSqLiteDatabase()
                .insert(EventosEntity.TABLE_NAME, null, contentValues) > 0;
    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = databaseGateway.getSqLiteDatabase().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventosEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_DATA));

            int idLocal = cursor.getInt(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String cidadeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            int capacidadeLocal = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));

            Local local = new Local(idLocal, nomeLocal, cidadeLocal, capacidadeLocal);

            eventos.add(new Evento(id, nomeEvento, data, local));
        }

        cursor.close();

        return eventos;
    }

    public void excluir(Evento evento) {
        databaseGateway.getSqLiteDatabase().delete(EventosEntity.TABLE_NAME,
                EventosEntity._ID + "=?", new String[]{String.valueOf(evento.getId())});
    }

    public List<Evento> filtrar(String consulta, Boolean ordem) {

        String SQL_FILTRAR = "SELECT eventos._id, nomeEvento, data, idlocal, local.nome, cidade, capacidade FROM " +
                EventosEntity.TABLE_NAME +
                " INNER JOIN " + LocalEntity.TABLE_NAME + " ON " + LocalEntity.TABLE_NAME + "." + LocalEntity._ID +
                " = " + EventosEntity.COLUMN_NAME_ID_LOCAL  + " where "  + EventosEntity.COLUMN_NAME_NOME + " like '%" + consulta + "%'" +
                " OR " + LocalEntity.COLUMN_NAME_CIDADE + " like '%" + consulta + "%'" +
                " ORDER BY "  + EventosEntity.COLUMN_NAME_NOME;

        if ( (consulta == null || consulta.trim().equals("")) &&  !ordem){
            COMANDO_SQL = SQL_LISTAR_TODOS;
        }else{
            COMANDO_SQL = SQL_FILTRAR;
        }

        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = databaseGateway.getSqLiteDatabase().rawQuery(COMANDO_SQL, null);
        while (cursor.moveToNext()){
            int idEvento = cursor.getInt(cursor.getColumnIndex(EventosEntity._ID));
            String nomeEvento = cursor.getString(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_DATA));

            int idlocal = cursor.getInt(cursor.getColumnIndex(EventosEntity.COLUMN_NAME_ID_LOCAL));
            String nomeLocal = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_NOME));
            String cidade = cursor.getString(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CIDADE));
            int capacidade = cursor.getInt(cursor.getColumnIndex(LocalEntity.COLUMN_NAME_CAPACIDADE));

            Local local = new Local(idlocal, nomeLocal, cidade, capacidade);
            eventos.add(new Evento(idEvento, nomeEvento, data, local));
        }

        cursor.close();

        return eventos;
    }
}
