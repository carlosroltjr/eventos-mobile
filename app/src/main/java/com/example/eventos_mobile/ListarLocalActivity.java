package com.example.eventos_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eventos_mobile.database.EventosDAO;
import com.example.eventos_mobile.database.LocalDAO;
import com.example.eventos_mobile.modelo.Evento;
import com.example.eventos_mobile.modelo.Local;

public class ListarLocalActivity extends AppCompatActivity {

    private ListView listViewLocais;
    private ArrayAdapter<Local> adapterLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_local);
        listViewLocais = findViewById(R.id.listView_locais);
        definirOnClickListenerListView();
        definirOnLongClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        adapterLocais = new ArrayAdapter<Local>(ListarLocalActivity.this,
                android.R.layout.simple_list_item_1,
                localDAO.listar());
        listViewLocais.setAdapter(adapterLocais);
    }

    private void definirOnClickListenerListView() {
        listViewLocais.setOnItemClickListener((adapterView, view, i, l) -> {
            Local localClicado = adapterLocais.getItem(i);
            Intent intent = new Intent(ListarLocalActivity.this, CriarLocalActivity.class);
            intent.putExtra("localEdicao", localClicado);
            startActivity(intent);
        });
    }

    private void definirOnLongClickListenerListView() {
        listViewLocais.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Local localClicado = adapterLocais.getItem(i);
                LocalDAO localDAO = new LocalDAO(getBaseContext());

                new AlertDialog.Builder(ListarLocalActivity.this).setIcon((android.R.drawable.ic_delete))
                        .setTitle("Confirmar exclusão")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapterLocais.remove(localClicado);
                                adapterLocais.notifyDataSetChanged();
                                localDAO.excluir(localClicado);
                                Toast.makeText(ListarLocalActivity.this, "Evento excluído", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", null).show();
                return true;
            }
        });
    }

    public void onClickNovoLocal(View v) {
        Intent intent = new Intent(ListarLocalActivity.this, CriarLocalActivity.class);
        startActivity(intent);
    }

    public void onClickEventos(View v) {
        Intent intent = new Intent(ListarLocalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
