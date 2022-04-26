package br.edu.utfpr.marcadortruco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HistoricoJogador extends AppCompatActivity {
    private TextView tvVitoriasUm;
    private TextView tvVitoriasDois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_jogador);

        Intent i = getIntent();

        int vitoriasUm = i.getIntExtra("vitoriasUm", 0);
        int vitoriasDois = i.getIntExtra("vitoriasDois", 0);

        tvVitoriasUm = findViewById(R.id.tvVitoriasUm);
        tvVitoriasDois = findViewById(R.id.tvVitoriasDois);

        tvVitoriasUm.setText(String.valueOf(vitoriasUm));
        tvVitoriasDois.setText(String.valueOf(vitoriasDois));
    }
}