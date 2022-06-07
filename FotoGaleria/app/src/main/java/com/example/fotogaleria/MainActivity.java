package com.example.fotogaleria;

        import androidx.activity.result.ActivityResult;
        import androidx.activity.result.ActivityResultCallback;
        import androidx.activity.result.ActivityResultLauncher;
        import androidx.activity.result.contract.ActivityResultContracts;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;

        import com.example.fotogaleria.adapter.MeuAdapter;
        import com.example.fotogaleria.entidade.Imagem;
        import com.example.fotogaleria.model.DatabaseHandler;

        import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private Button btTirarFoto;
    private ListView ivFotos;

    private DatabaseHandler dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new DatabaseHandler( this );

//        ivFoto = findViewById(R.id.ivFoto);
        ivFotos = findViewById(R.id.ivFotos);
        btTirarFoto = findViewById(R.id.btTirarFoto);

        btTirarFoto.setOnClickListener((view -> someActivityResultLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))));

        listAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter();
    }

    public void listAdapter() {
        Cursor registros = dao.listar();

        MeuAdapter adapter = new MeuAdapter( this, registros, dao);

        ivFotos.setAdapter( adapter );

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();

                    if (result.getResultCode() == Activity.RESULT_OK && data != null) {
                        // There are no request codes

                        Bitmap foto = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        foto.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        foto.recycle();

                        Imagem cad = new Imagem();
                        cad.setFoto( byteArray );
                        dao.incluir( cad );
                        listAdapter();
//                        ivFoto.setImageBitmap(foto);
                    }
                }
            });
}