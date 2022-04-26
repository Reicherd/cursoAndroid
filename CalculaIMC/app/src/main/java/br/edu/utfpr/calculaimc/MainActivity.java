package br.edu.utfpr.calculaimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etPeso;
    private EditText etAltura;
    private TextView tvResultado;
    private Button btCalcular;
    private Button btLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        tvResultado = findViewById(R.id.tvResultado);
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLimparOnClick();
            }
        });

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btCalcularOnClick();
            }
        });
    }

    private void btCalcularOnClick() {
        System.out.println("TEEEEEEEEEESTE");
        if (etAltura.getText().toString().equals("")) {
            etAltura.setError(getString(R.string.campoAlturaNaoInformado));
            return;
        }
        if (etPeso.getText().toString().equals("")) {
            etPeso.setError(getString(R.string.campoPesoNaoInformado));
            return;
        }
        try {
            BigDecimal altura = BigDecimal.valueOf(Math.pow(Double.parseDouble(etAltura.getText().toString()), 2));
            BigDecimal peso;
            if (Locale.getDefault().getLanguage().equals("en")) {
                peso = calculaBmi();
            } else {
                peso = calculaImc();
            }

            BigDecimal imc = !peso.equals(BigDecimal.ZERO) && !altura.equals(BigDecimal.ZERO) ? peso.divide(altura, 2, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;

            tvResultado.setText(imc.toString());
        } catch (Exception e) {
            Log.e("Principal", "TEEESSTEEEE");
        }

    }

    private BigDecimal calculaImc() {
        return BigDecimal.valueOf(Double.parseDouble(etPeso.getText().toString()));

    }

    private BigDecimal calculaBmi() {
        return BigDecimal.valueOf(Double.parseDouble(etPeso.getText().toString())).multiply(BigDecimal.valueOf(703L));
    }

    private void btLimparOnClick() {
        etPeso.setText("");
        etAltura.setText("");
        tvResultado.setText(R.string.zero);
        etPeso.requestFocus();
    }


}