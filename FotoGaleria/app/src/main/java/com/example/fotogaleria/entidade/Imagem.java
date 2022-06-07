package com.example.fotogaleria.entidade;

import java.io.Serializable;

public class Imagem implements Serializable {

    private int _id;
    private byte[] foto;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
