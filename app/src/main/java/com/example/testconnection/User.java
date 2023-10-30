package com.example.testconnection;


import java.io.Serializable;


public class User implements Serializable {
    private String username;
    private String cognoms;
    private String password;
    private String correu_electronic;
    private String numero_targeta;
    private String data_caducitat;
    private String cvv;

    public User(String username, String cognoms, String password, String correu_electronic, String numero_targeta, String data_caducitat, String cvv) {
        this.username = username;
        this.cognoms = cognoms;
        this.password = password;
        this.correu_electronic = correu_electronic;
        this.numero_targeta = numero_targeta;
        this.data_caducitat = data_caducitat;
        this.cvv = cvv;
    }

    // Getters y setters (opcional, puedes mantenerlos si son necesarios)

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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