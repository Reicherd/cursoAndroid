package br.edu.utfpr.relogio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvRelogio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRelogio = findViewById(R.id.tvRelogio);

        Timer agendador = new Timer();

        agendador.scheduleAtFixedRate(new AtualizarRelogioTimerTask(), 0, 1000);
    }

    private class AtualizarRelogioTimerTask extends TimerTask {
        public void run() {
            Date agora = new Date();

            SimpleDateFormat df = new SimpleDateFormat("HH:MM:ss");

            String horaFormatada = df.format(agora);

            MainActivity.this.runOnUiThread(new Thread() {
                @Override
                public void run() {
                    tvRelogio.setText(horaFormatada);
                }
            });
        }
    }
}