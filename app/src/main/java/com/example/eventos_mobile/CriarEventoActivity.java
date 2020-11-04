package com.example.eventos_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eventos_mobile.database.EventosDAO;
import com.example.eventos_mobile.database.LocalDAO;
import com.example.eventos_mobile.modelo.Evento;
import com.example.eventos_mobile.modelo.Local;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriarEventoActivity extends AppCompatActivity {

    private int id = 0;
    private Spinner spinnerLocais;
    private ArrayAdapter<Local> locaisAdapter;
    EditText editTextNome;
    EditText editTextData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);
        setTitle("Criar Evento");

        spinnerLocais = findViewById(R.id.spinner_local);
        editTextNome = findViewById(R.id.editText_nome);
        editTextData = findViewById(R.id.editText_data);

        carregarLocais();
        carregarEvento();
    }

    private void carregarLocais() {
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        locaisAdapter = new ArrayAdapter<Local>(CriarEventoActivity.this,
                android.R.layout.simple_spinner_item,
                localDAO.listar());
        spinnerLocais.setAdapter(locaisAdapter);
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("eventoEdicao") != null) {

            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            editTextNome.setText(evento.getNome());
            editTextData.setText(String.valueOf(evento.getData()));

            int posicaoLocal = obterPosicaoLocal(evento.getLocal());
            spinnerLocais.setSelection(posicaoLocal);

            id = evento.getId();
        }
    }

    public int obterPosicaoLocal(Local local) {
        for (int posicao = 0; posicao < locaisAdapter.getCount(); posicao++) {
            if (locaisAdapter.getItem(posicao).getId() == local.getId()) {
                return posicao;
            }
        }

        return 0;
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        Local local = (Local) spinnerLocais.getSelectedItem();

        String expressaoRegularData = "^\\d{2}/\\d{2}/\\d{4}$";
        Pattern pattern = Pattern.compile(expressaoRegularData);
        Matcher matcher = pattern.matcher(data);

        if (nome.length() < 3) {
            Toast.makeText(CriarEventoActivity.this, "Mínimo de três letras para o nome", Toast.LENGTH_LONG).show();
        } else if (!matcher.find()) {
            Toast.makeText(CriarEventoActivity.this, "Formato de data aceito 12/34/5678", Toast.LENGTH_LONG).show();
        } else if (local == null) {
            Toast.makeText(CriarEventoActivity.this, "Campo Local obrigatório", Toast.LENGTH_LONG).show();
        } else {
            Evento evento = new Evento(id, nome, data, local);
            EventosDAO eventosDAO = new EventosDAO(getBaseContext());

            boolean salvou = eventosDAO.salvar(evento);

            if (salvou) {
                finish();
            } else {
                Toast.makeText(CriarEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
            }
        }
    }
}
