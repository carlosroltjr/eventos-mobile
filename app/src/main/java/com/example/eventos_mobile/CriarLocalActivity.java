package com.example.eventos_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventos_mobile.database.LocalDAO;
import com.example.eventos_mobile.modelo.Local;

public class CriarLocalActivity extends AppCompatActivity {

    private int id = 0;
    private EditText editTextNome;
    private EditText editTextCidade;
    private EditText editTextCapacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_local);
        setTitle("Cadastro de Local");

        editTextNome = findViewById(R.id.editText_nome_local);
        editTextCidade = findViewById(R.id.editText_cidade_local);
        editTextCapacidade = findViewById(R.id.editTextNumber_capacidade_local);

        carregarLocal();
    }

    public void carregarLocal() {
        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("categoriaEdicao") != null) {
            Local local = (Local) intent.getExtras().get("localEdicao");
            editTextNome.setText(local.getNome());
            editTextCidade.setText(local.getCidade());
            editTextCapacidade.setText(local.getCapacidade());
            id = local.getId();
        }
    }

    public  void onClickVoltar(View v) { finish(); }

    public void onClickSalvar(View v) {
        String nome = editTextNome.getText().toString();
        String cidade = editTextCidade.getText().toString();
        int capacidade = Integer.parseInt(editTextCapacidade.getText().toString());

        Local local = new Local(id, nome, cidade, capacidade);
        LocalDAO localDAO = new LocalDAO(getBaseContext());
        boolean salvou = localDAO.salvar(local);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CriarLocalActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }
}