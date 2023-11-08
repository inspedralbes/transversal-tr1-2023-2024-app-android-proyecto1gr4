package com.example.testconnection;

import java.util.List;

public class Comanda {
    int id_usuari;
    double preu;
    String data;
    String hora;

    private List<Producto> productes;

    public Comanda(int id_usuari, double preu, String data, String hora, List<Producto> productes) {
        this.id_usuari = id_usuari;
        this.preu = preu;
        this.data = data;
        this.hora = hora;
        this.productes = productes;
    }

    public int getId_usuari() {
        return id_usuari;
    }

    public void setId_usuari(int id_usuari) {
        this.id_usuari = id_usuari;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public List<Producto> getProductes() {
        return productes;
    }

    public void setProductes(List<Producto> productes) {
        this.productes = productes;
    }
}


