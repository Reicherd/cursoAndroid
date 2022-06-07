package com.example.fotogaleria.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fotogaleria.entidade.Imagem;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bd";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "imagem";


    public DatabaseHandler(Context c ) {
        super( c, DATABASE_NAME, null, VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, foto BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE " + TABLE_NAME );
        onCreate( db );
    }

    public void incluir(Imagem cad ) {
        SQLiteDatabase bd = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put( "foto", cad.getFoto() );

        bd.insert( TABLE_NAME, null, registro );

    }

    public void alterar( Imagem cad ) {
        SQLiteDatabase bd = this.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put( "foto", cad.getFoto() );

        bd.update( TABLE_NAME, registro, "_id = " + cad.get_id(),
                null );
    }

    public void excluir( int cod ) {
        SQLiteDatabase bd = this.getWritableDatabase();

        bd.delete( TABLE_NAME, "_id = " + cod, null );
    }

    public Cursor listar() {
        SQLiteDatabase bd = this.getWritableDatabase();

        Cursor registros = bd.query( TABLE_NAME, null,
                null, null, null,
                null, null );

        return registros;
    }

}
