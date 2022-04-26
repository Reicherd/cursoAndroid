package br.edu.utfpr.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class ListarActivity extends AppCompatActivity {

    public static final int SUCCESS = 0;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lista = findViewById(R.id.listaProdutos);

        lista.setOnItemClickListener((adapterView, view, pos, l) -> itemListaOnClick(pos));
    }

    private void itemListaOnClick(int pos) {
//        Toast.makeText(this, String.valueOf(pos + 1), Toast.LENGTH_SHORT).show();
        Intent i = getIntent();
        i.putExtra("item", pos + 1);

        setResult(SUCCESS, i);

        finish();
    }
}