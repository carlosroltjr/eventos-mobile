package com.example.eventos_mobile.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Evento implements Serializable {

    private int id;
    private String nomeEvento;
    private String data;
    private Local local;

    public Evento(int id, String nomeEvento, String data, Local local) {
        this.id = id;
        this.nomeEvento = nomeEvento;
        this.data = data;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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
        return "Evento: " + this.nomeEvento +
                " \nData: " + this.data +
                " \nLocal: " + this.getLocal().getNome() + " \nCidade: " + this.getLocal().getCidade() +
                " \nCapacidade: " + this.getLocal().getCapacidade();
    }
}
