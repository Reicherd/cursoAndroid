package com.example.fotogaleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fotogaleria.model.DatabaseHandler;

public class ImageViewActivity extends AppCompatActivity {
    private ImageView ivFoto;
    private ImageButton ibExcluirUnica;
    private DatabaseHandler dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_list);
        dao = new DatabaseHandler( this );

        Intent i = getIntent();

        byte[] foto = i.getByteArrayExtra("foto");
        int cod = i.getIntExtra("cod", 0);

        ivFoto = findViewById(R.id.ivFoto);
        ibExcluirUnica = findViewById(R.id.ibExcluirUnica);

        ivFoto.setImageBitmap(BitmapFactory.decodeByteArray(foto, 0, foto.length));
        ibExcluirUnica.setOnClickListener(view -> btExcluirFotoUnicaClick(cod));
    }

    private void btExcluirFotoUnicaClick(int cod) {
        dao.excluir(cod);

        this.finish();
    }
}