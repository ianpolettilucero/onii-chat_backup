package com.example.restservice;

public class Item {
    private int clavePrimaria;
    private String descripcion;
    private boolean estado;

    public Item() {
        this.clavePrimaria = -1;
        this.descripcion = " ";
        this.estado = true;
    }

    public Item(int clavePrimaria, String descripcion, boolean estado) {
        this.clavePrimaria = clavePrimaria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getClavePrimaria() {
        return clavePrimaria;
    }

    public void setClavePrimaria(int clavePrimaria) {
        this.clavePrimaria = clavePrimaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
