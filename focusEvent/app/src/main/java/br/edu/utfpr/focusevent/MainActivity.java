package br.edu.utfpr.focusevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etTexto1;
    private EditText etTexto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTexto1 = findViewById(R.id.etTexto1);
        etTexto2 = findViewById(R.id.etTexto2);

        etTexto1.setOnFocusChangeListener(((view, b) -> focusChange(b)));
    }

    private void focusChange(boolean b) {
        Toast.makeText(this, "Boolean " + b, Toast.LENGTH_LONG).show();
    }
}