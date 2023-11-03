package com.example.pruebalistaproductos;

import java.io.Serializable;

public class Producto implements Serializable {
    int id;
    String nombre;
    float precio;
    String descripcion;
    String fotos;
    int tipo_id;
    int tienda_id;
    int stock;
    int disponibilidad;
    int isActive;

public Producto(int id, String nombre, float precio, String descripcion, String fotos, int tipo_id, int tienda_id, int stock, int disponibilidad, int isActive) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.tipo_id = tipo_id;
        this.tienda_id = tienda_id;
        this.stock = stock;
        this.disponibilidad = disponibilidad;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public int getTienda_id() {
        return tienda_id;
    }

    public void setTienda_id(int tienda_id) {
        this.tienda_id = tienda_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
