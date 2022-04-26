package br.edu.utfpr.cityselector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner girador;
    private ImageView imagem;

    private static final List<Integer> imagens = Arrays.asList(0, R.mipmap.pato, R.mipmap.beltrao, R.mipmap.marmeleiro, R.mipmap.coronel, R.mipmap.vitorino, R.mipmap.mariopolis, R.mipmap.mangueirinha);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girador = findViewById(R.id.girador);
        imagem = findViewById(R.id.imagem);

        List<String> cidades = Arrays.asList("", "Pato Branco", "Francisco Beltrão", "Marmeleiro", "Coronel Vivida", "Vitorino", "Mariópolis", "Mangueirinha");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cidades);

        girador.setAdapter(adapter);

        girador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelecionado(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void itemSelecionado(int i) {
        imagem.setImageResource(imagens.get(i));
    }
}