package br.edu.utfpr.cadastrobancodados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCod;
    private EditText etNome;
    private EditText etIdade;

    private Button btIncluir;
    private Button btAlterar;
    private Button btExcluir;
    private Button btPesquisar;
    private Button btListar;

    private SQLiteDatabase sqLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLite = this.openOrCreateDatabase("banquinho", Context.MODE_PRIVATE, null);
        sqLite.execSQL("CREATE TABLE IF NOT EXISTS aluno (codigo INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, idade NUMERIC)");

        etCod = findViewById(R.id.etCod);
        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);

        btIncluir = findViewById(R.id.btIncluir);
        btAlterar = findViewById(R.id.btAlterar);
        btExcluir = findViewById(R.id.btExcluir);
        btPesquisar = findViewById(R.id.btPesquisar);
        btListar = findViewById(R.id.btListar);

        btIncluir.setOnClickListener(view -> onClickIncluir());
        btAlterar.setOnClickListener(view -> onClickAlterar());
        btExcluir.setOnClickListener(view -> onClickExcluir());
        btPesquisar.setOnClickListener(view -> onClickPesquisar());
        btListar.setOnClickListener(view -> onClickListar());
    }

    private void onClickListar() {
        Cursor registros = sqLite.query("aluno", null, null, null, null, null, null);

        listaVarios(registros);
    }

    private void listaVarios(Cursor registros) {
        StringBuilder saida = new StringBuilder();

        while (registros.moveToNext()) {
            saida.append(registros.getString(registros.getColumnIndexOrThrow("codigo")));
            saida.append(" - ");
            saida.append(registros.getString(registros.getColumnIndexOrThrow("nome")));
            saida.append(" - ");
            saida.append(registros.getString(registros.getColumnIndexOrThrow("idade")));
            saida.append("\n");
        }

        Toast.makeText(this, saida.toString(), Toast.LENGTH_LONG).show();
    }

    private void onClickPesquisar() {
        etCod.getText().toString();
        Cursor registros = sqLite.query("aluno", null, "codigo = ? or nome like ?", new String[] {etCod.getText().toString(), "%" + etNome.getText().toString() + "%"}, null, null, null);

        listaVarios(registros);
    }

    private void onClickExcluir() {
        sqLite.delete("aluno", "codigo = " + etCod.getText().toString(), null);

        Toast.makeText(this, "Excluido", Toast.LENGTH_SHORT).show();
    }

    private void onClickAlterar() {
        ContentValues novoRegistro = new ContentValues();
        novoRegistro.put("nome", etNome.getText().toString());
        novoRegistro.put("idade", Integer.parseInt(etIdade.getText().toString()));

        sqLite.update("aluno", novoRegistro, "codigo = " + etCod.getText().toString(), null);
    }

    private void onClickIncluir() {
        ContentValues registro = new ContentValues();
        registro.put("nome", etNome.getText().toString());
        registro.put("idade", Integer.parseInt(etIdade.getText().toString()));

        sqLite.insert("aluno", null, registro);

        Toast.makeText(this, "Sucesso!", Toast.LENGTH_SHORT).show();
    }
}