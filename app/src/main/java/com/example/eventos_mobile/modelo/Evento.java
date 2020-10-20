package com.example.eventos_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;

    private String nome;

    private String data;

    private String local;

    public Evento(int id, String nome, String data, String local) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + data + " - " + local;
    }
}
