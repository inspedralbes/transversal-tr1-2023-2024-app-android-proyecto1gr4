package com.example.testconnection;


import java.io.Serializable;


public class User implements Serializable {

    private int id;
    private String nom_usuari;
    private String cognoms_usuari;

    private String contrasenya;
    private String correu_electronic;
    private String numero_targeta;
    private String data_caducitat;
    private String cvv;

    public User(int id_usuari, String nom_usuari, String cognoms_usuari, String contrasenya, String correu_electronic, String numero_targeta, String data_caducitat, String cvv) {
        this.id = id_usuari;
        this.nom_usuari = nom_usuari;
        this.cognoms_usuari = cognoms_usuari;
        this.contrasenya = contrasenya;
        this.correu_electronic = correu_electronic;
        this.numero_targeta = numero_targeta;
        this.data_caducitat = data_caducitat;
        this.cvv = cvv;
    }

    public int getId_usuari() {
        return id;
    }

    public void setId_usuari(int id_usuari) {
        this.id = id_usuari;
    }

    public String getNom_usuari() {
        return nom_usuari;
    }

    public void setNom_usuari(String nom_usuari) {
        this.nom_usuari = nom_usuari;
    }

    public String getCognoms_usuari() {
        return cognoms_usuari;
    }

    public void setCognoms_usuari(String cognoms_usuari) {
        this.cognoms_usuari = cognoms_usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCorreuElectronic() {
        return correu_electronic;
    }

    public void setCorreuElectronic(String correu_electronic) {
        this.correu_electronic = correu_electronic;
    }

    public String getNumeroTargeta() {
        return numero_targeta;
    }

    public void setNumeroTargeta(String numero_targeta) {
        this.numero_targeta = numero_targeta;
    }

    public String getDataCaducitat() {
        return data_caducitat;
    }

    public void setDataCaducitat(String data_caducitat) {
        this.data_caducitat = data_caducitat;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}