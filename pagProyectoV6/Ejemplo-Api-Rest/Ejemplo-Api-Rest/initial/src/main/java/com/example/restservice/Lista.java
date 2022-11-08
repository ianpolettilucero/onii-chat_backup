package com.example.restservice;

import java.util.HashMap;

public class Lista {
    private int clavePrimaria;
    private String nombre;
    HashMap<Integer, HashMap<String, Object>> items;

    public Lista() {
        this.clavePrimaria = -1;
        this.items = new HashMap<>();
        this.nombre= null;
    }

    public Lista(int clavePrimaria, HashMap<Integer,HashMap<String, Object>>items, String nombre) {
        this.clavePrimaria = clavePrimaria;
        this.items = items;
        this.nombre=nombre;
    }

    public int getClavePrimaria() {
        return clavePrimaria;
    }

    public void setClavePrimaria(int clavePrimaria) {
        this.clavePrimaria = clavePrimaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<Integer, HashMap<String, Object>> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, HashMap<String, Object>> items) {
        this.items = items;
    }

}
