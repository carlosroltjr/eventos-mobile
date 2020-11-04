package com.example.eventos_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eventos_mobile.database.EventosDAO;
import com.example.eventos_mobile.modelo.Evento;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private  int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");

        listViewEventos = findViewById(R.id.listView_eventos);

        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventosDAO eventosDAO = new EventosDAO(getBaseContext());
        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1, eventosDAO.listar());

        listViewEventos.setAdapter(adapterEventos);
    }

    private void definirOnClickListenerListView() {
        listViewEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento eventoClicado = adapterEventos.getItem(i);
                Intent intent = new Intent(MainActivity.this, CriarEventoActivity.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    private void definirOnLongClickListenerListView() {
        listViewEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Evento eventoClicado = adapterEventos.getItem(i);
                EventosDAO eventosDAO = new EventosDAO(getBaseContext());

                new AlertDialog.Builder(MainActivity.this).setIcon((android.R.drawable.ic_delete))
                        .setTitle("Confirmar exclusão")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterEventos.remove(eventoClicado);
                                adapterEventos.notifyDataSetChanged();
                                eventosDAO.excluir(eventoClicado);
                                Toast.makeText(MainActivity.this, "Evento excluído", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", null).show();
                return true;
            }
        });
    }

    public void onClickNovoEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CriarEventoActivity.class);
        startActivity(intent);
    }

    public void onClickLocais(View v) {
        Intent intent = new Intent(MainActivity.this, ListarLocalActivity.class);
        startActivity(intent);
        finish();
    }
}
