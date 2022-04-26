package br.edu.utfpr.cadastro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CadastroActivity extends AppCompatActivity {
    private static final int LISTAR_CODE = 0;

    private Button btConfirmar;
    private Button btListar;
    private EditText etCodigo;
    private EditText etQuantidade;
    private EditText etValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btListar = findViewById(R.id.listar);
        btConfirmar = findViewById(R.id.confirmar);
        etCodigo = findViewById(R.id.etCodigo);
        etQuantidade = findViewById(R.id.etQuantidade);
        etValor = findViewById(R.id.etValor);

        btListar.setOnClickListener(view -> btListarOnClick());

        btConfirmar.setOnClickListener(view -> btConfirmarOnClick());
    }

    private void btConfirmarOnClick() {
        Intent i = new Intent(this, ConfirmarActivity.class);

        i.putExtra("codigo", Integer.parseInt(etCodigo.getText().toString()));
        i.putExtra("quantidade", Double.parseDouble(etQuantidade.getText().toString()));
        i.putExtra("valor", Double.parseDouble(etValor.getText().toString()));

        startActivity(i);
    }

    private void btListarOnClick() {
        Intent i = new Intent(this, ListarActivity.class);
        startActivityForResult(i, LISTAR_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LISTAR_CODE && resultCode == ListarActivity.SUCCESS) {
            etCodigo.setText(data != null ? String.valueOf(data.getIntExtra("item", 0)) : "");
        }
    }
}