package br.edu.utfpr.cadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarActivity extends AppCompatActivity {
    private Button btConfirmar;
    private TextView tvCodigo;
    private TextView tvQuantidade;
    private TextView tvValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        Intent i = getIntent();

        int codigo = i.getIntExtra("codigo", 0);
        double quantidade = i.getDoubleExtra("quantidade", 0d);
        double valor = i.getDoubleExtra("valor", 0d);

        btConfirmar = findViewById(R.id.btConfirmar);
        tvCodigo = findViewById(R.id.tvCodigo);
        tvQuantidade = findViewById(R.id.tvQuantidade);
        tvValor = findViewById(R.id.tvValor);

        tvCodigo.setText(String.valueOf(codigo));
        tvQuantidade.setText(String.valueOf(quantidade));
        tvValor.setText(String.valueOf(valor));

        btConfirmar.setOnClickListener(view -> btConfirmarOnClick());
    }

    private void btConfirmarOnClick() {
//        Toast.makeText(this, "Rede Indisponível no momento", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Erro");
        alerta.setMessage("Rede Indisponível no momento");
        alerta.setCancelable(false);
        alerta.setNeutralButton("OK", null);

        alerta.show();
    }
}