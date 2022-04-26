package com.example.cadastroveiculo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etPlaca;
    private Spinner spinnerMarca;
    private Spinner spinnerModelo;
    private Pattern padrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlaca = (EditText) findViewById(R.id.etPlaca);
        spinnerMarca = (Spinner) findViewById(R.id.spinnerMarca);
        spinnerModelo = (Spinner) findViewById(R.id.spinnerModelo);

        List<String> marcas = Arrays.asList("Fiat", "Ford", "Nissan", "Kia");

        ArrayAdapter<String> marcasAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marcas);
        spinnerMarca.setAdapter(marcasAdapter);

        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecionaModelo(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "Selecione o modelo.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void selecionaModelo(int position) {
        List<String> modelos;

        switch(position) {
            case 0: modelos = Arrays.asList("Uno", "Strada"); break;
            case 1: modelos = Arrays.asList("Ka", "Fiesta"); break;
            case 2: modelos = Arrays.asList("March", "Frontier"); break;
            case 3: modelos = Arrays.asList("Sportage", "Sorento"); break;
            default: modelos = new ArrayList<String>() {};
        }

        ArrayAdapter<String> modeloAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelos);
        spinnerModelo.setAdapter(modeloAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.salvar) {
            Toast.makeText(getApplicationContext(), "Informações Salvas!", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.limpar) {
            etPlaca.setText("");
            spinnerMarca.setSelection(0);
            spinnerModelo.setSelection(0);
        }

        return true;
    }
}