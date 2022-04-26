package br.edu.utfpr.marcadortruco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btPlusOneUm;
    private Button btPlusThreeUm;
    private Button btPlusSixUm;
    private Button btPlusNineUm;
    private Button btPlusTwelveUm;

    private Button btPlusOneDois;
    private Button btPlusThreeDois;
    private Button btPlusSixDois;
    private Button btPlusNineDois;
    private Button btPlusTwelveDois;

    private Button btHistJogadas;
    private Button btZerarHist;

    private TextView tvNomeUm;
    private TextView tvNomeDois;

    private TextView tvPontosUm;
    private TextView tvPontosDois;

    private int pontosUm = 0;
    private int pontosDois = 0;

    private int vitoriasUm = 0;
    private int vitoriasDois = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPlusOneUm = findViewById(R.id.btPlusOneUm);
        btPlusThreeUm = findViewById(R.id.btPlusThreeUm);
        btPlusSixUm = findViewById(R.id.btPlusSixUm);
        btPlusNineUm = findViewById(R.id.btPlusNineUm);
        btPlusTwelveUm = findViewById(R.id.btPlusTwelveUm);

        btPlusOneDois = findViewById(R.id.btPlusOneDois);
        btPlusThreeDois = findViewById(R.id.btPlusThreeDois);
        btPlusSixDois = findViewById(R.id.btPlusSixDois);
        btPlusNineDois = findViewById(R.id.btPlusNineDois);
        btPlusTwelveDois = findViewById(R.id.btPlusTwelveDois);

        btHistJogadas = findViewById(R.id.btHistJogadas);
        btZerarHist = findViewById(R.id.btZerarHist);

        tvNomeUm = findViewById(R.id.tvNomeUm);
        tvNomeDois = findViewById(R.id.tvNomeDois);

        tvPontosUm = findViewById(R.id.tvPontosUm);
        tvPontosDois = findViewById(R.id.tvPontosDois);

        btPlusOneUm.setOnClickListener(view -> btAddPointOnClick(1, 1));
        btPlusThreeUm.setOnClickListener(view -> btAddPointOnClick(1, 3));
        btPlusSixUm.setOnClickListener(view -> btAddPointOnClick(1, 6));
        btPlusNineUm.setOnClickListener(view -> btAddPointOnClick(1, 9));
        btPlusTwelveUm.setOnClickListener(view -> btAddPointOnClick(1, 12));

        btPlusOneDois.setOnClickListener(view -> btAddPointOnClick(2, 1));
        btPlusThreeDois.setOnClickListener(view -> btAddPointOnClick(2, 3));
        btPlusSixDois.setOnClickListener(view -> btAddPointOnClick(2, 6));
        btPlusNineDois.setOnClickListener(view -> btAddPointOnClick(2, 9));
        btPlusTwelveDois.setOnClickListener(view -> btAddPointOnClick(2, 12));

        btZerarHist.setOnClickListener(view -> btZerarOnClick());
        btHistJogadas.setOnClickListener(view -> btHistJogadasOnClick());

    }

    private void btHistJogadasOnClick() {
        Intent i = new Intent(this, HistoricoJogador.class);

        i.putExtra("vitoriasUm", vitoriasUm);
        i.putExtra("vitoriasDois", vitoriasDois);

        startActivity(i);
    }

    private void btZerarOnClick() {
        vitoriasUm = 0;
        vitoriasDois = 0;
        pontosUm = 0;
        pontosDois = 0;
        tvPontosUm.setText(String.valueOf(pontosUm));
        tvPontosDois.setText(String.valueOf(pontosDois));
    }

    private void btAddPointOnClick(int jogador, int add) {
        if (jogador == 1) {
            pontosUm += add;
            tvPontosUm.setText(String.valueOf(pontosUm));
        } else if (jogador == 2) {
            pontosDois += add;
            tvPontosDois.setText(String.valueOf(pontosDois));
        }
        
        if (jogador == 1 ? pontosUm >= 12 : pontosDois >= 12) {
            rodadaAcabada(jogador);
        }
    }

    private void rodadaAcabada(int jogador) {
        alertaVitoria(jogador == 1 ? tvNomeUm.getText().toString() : tvNomeDois.getText().toString());

        if (jogador == 1) {
            vitoriasUm++;
        } else if (jogador == 2) {
            vitoriasDois++;
        }

        pontosUm = 0;
        pontosDois = 0;
        tvPontosUm.setText(String.valueOf(pontosUm));
        tvPontosDois.setText(String.valueOf(pontosDois));
    }

    private void alertaVitoria(String nomeJogador) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Vitória");
        alerta.setMessage(nomeJogador + " ganhou de forma espetacular!");
        alerta.setCancelable(false);
        alerta.setNeutralButton("OK", null);

        alerta.show();
    }
}