package com.inventario.model;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private String categoria;
    private int stock;
    private double precio;

    // Constructor, Getters y Setters
    public Producto(int id, String nombre, String categoria, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return String.format("ID: %03d | %-15s | Cat: %-10s | Stock: %3d | Precio: %.2f€",
                id, nombre, categoria, stock, precio);
    }

}
