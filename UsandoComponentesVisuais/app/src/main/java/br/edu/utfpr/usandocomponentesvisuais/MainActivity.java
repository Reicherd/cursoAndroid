package br.edu.utfpr.usandocomponentesvisuais;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgSexo;
    RadioGroup estadoCivil;

    RadioButton radioMasculino;
    RadioButton radioFeminino;
    RadioButton radioSolteiro;
    RadioButton radioCasado;
    RadioButton radioDivorciado;
    Map<Integer, RadioButton> radios = new HashMap<>();

    CheckBox checkCaminhada;
    CheckBox checkCorrida;
    CheckBox checkNatacao;

    ProgressBar progresso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgSexo = findViewById(R.id.rgSexo);
        estadoCivil = findViewById(R.id.estadoCivil);

        radioMasculino = findViewById(R.id.radioMasculino);
        radioFeminino = findViewById(R.id.radioFeminino);
        radioSolteiro = findViewById(R.id.radioSolteiro);
        radioCasado = findViewById(R.id.radioCasado);
        radioDivorciado = findViewById(R.id.radioDivorciado);

        radios.put(radioMasculino.getId(), radioMasculino);
        radios.put(radioFeminino.getId(), radioFeminino);
        radios.put(radioSolteiro.getId(), radioSolteiro);
        radios.put(radioCasado.getId(), radioCasado);
        radios.put(radioDivorciado.getId(), radioDivorciado);

        checkCaminhada = findViewById(R.id.checkCaminhada);
        checkCorrida = findViewById(R.id.checkCorrida);
        checkNatacao = findViewById(R.id.checkNatacao);

        progresso = findViewById(R.id.progresso);
    }

    public void btTestarComponenteOnClick(View view) throws InterruptedException {
        new AtualizaTela().start();

    }

    class AtualizaTela extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 100; i++) {
                progresso.setProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}