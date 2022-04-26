package br.edu.utfpr.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btLancamento;
    private Button btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLancamento = findViewById(R.id.lancamento);
        btSair = findViewById(R.id.sair);

        btSair.setOnClickListener(view -> btSairOnClick());

        btLancamento.setOnClickListener(view -> btLancamentoOnClick());
    }

    private void btLancamentoOnClick() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void btSairOnClick() {
        finish();
    }
}