package br.edu.utfpr.screentouchevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout tela;
    private ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tela = findViewById(R.id.tela);
        scroll = findViewById(R.id.scroll);

        scroll.setOnTouchListener((view, motionEvent) -> tratarEventoToque(motionEvent));
    }

    private boolean tratarEventoToque(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
            TextView semNome = new TextView(this);
            String pontoClicado = motionEvent.getAxisValue(0) + " - " + motionEvent.getAxisValue(1);
            semNome.setText(pontoClicado);

            tela.addView(semNome);
        }

        return false;
    }
}