package com.example.fotogaleria.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fotogaleria.ImageViewActivity;
import com.example.fotogaleria.MainActivity;
import com.example.fotogaleria.R;
import com.example.fotogaleria.model.DatabaseHandler;

import java.io.ByteArrayOutputStream;

public class MeuAdapter extends BaseAdapter {

    private final MainActivity c;
    private final Cursor registros;
    private final DatabaseHandler dao;

    public MeuAdapter(MainActivity c, Cursor registros, DatabaseHandler dao ) {
        this.c = c;
        this.registros = registros;
        this.dao = dao;
    }

    @Override
    public int getCount() {
        return registros.getCount();
    }

    @Override
    public Object getItem(int position) {

        registros.moveToPosition( position );
        return registros;

    }



    @Override
    public long getItemId(int position) {
        registros.moveToPosition( position );
        return registros.getInt( registros.getColumnIndex( "_id" ) );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate( R.layout.elemento_lista, null );

        ImageButton ibExcluir = v.findViewById( R.id.ibExcluir);
        ImageButton ibFoto  = v.findViewById( R.id.ibFoto);

        registros.moveToPosition( registros.getCount() - position - 1 );

        final int cod = registros.getInt( registros.getColumnIndex( "_id" ) );
        byte[] fotoBlob = registros.getBlob(registros.getColumnIndex("foto"));
        Bitmap foto = BitmapFactory.decodeByteArray(fotoBlob, 0 ,fotoBlob.length);

        ibFoto.setImageBitmap(foto);

        ibExcluir.setOnClickListener(view -> btExcluirFotoClick(cod, position));
        ibFoto.setOnClickListener(view -> btVerFotoClick(cod, foto));

        return v;
    }

    private void btVerFotoClick(int cod, Bitmap foto) {
        Intent i = new Intent(c, ImageViewActivity.class);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        foto.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        i.putExtra("foto", byteArray);
        i.putExtra("cod", cod);

        c.startActivity(i);
    }

    private void btExcluirFotoClick(int cod, int position) {
        dao.excluir(cod);
        c.listAdapter();

        Toast.makeText( c.getBaseContext(), "Sucesso!!! " + cod, Toast.LENGTH_LONG ).show();
    }
}
