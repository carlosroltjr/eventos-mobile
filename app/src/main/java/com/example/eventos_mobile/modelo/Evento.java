package com.example.eventos_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nome;
    private String data;
    private Local local;

    public Evento(int id, String nome, String data, Local local) {
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

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + nome + " - " + data + " - " + local;
    }
}
