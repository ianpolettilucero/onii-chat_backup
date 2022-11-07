package com.example.restservice;

//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoIterable;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Acceso {

    private MongoDatabase baseDeDatos;
    private MongoCollection coleccion;
    private String host;
    private int puerto;

    public Acceso() {
        this.host = "localhost";
        this.puerto = 27017;
    }

    public MongoDatabase getBaseDeDatos() {
        return baseDeDatos;
    }

    public void setBaseDeDatos(MongoDatabase baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public MongoCollection getColeccion() {
        return coleccion;
    }

    public void setColeccion(MongoCollection coleccion) {
        this.coleccion = coleccion;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void conectarABaseDeDatos(String nombreBaseDeDatos) {
        MongoClient mongo = new MongoClient(host, puerto);
        this.baseDeDatos = mongo.getDatabase(nombreBaseDeDatos);
    }

    public boolean existeLaColeccion(String nombreDeColeccion) {
        MongoIterable<String> nombreDeColecciones = baseDeDatos.listCollectionNames();
        boolean existe = false;
        for (String nombre : nombreDeColecciones) {
            if (nombre.equals(nombreDeColeccion)) {
                existe = true;
            }
        }
        return existe;
    }

    public void conectarAColeccion(String nombreDeColeccion) {
        if (existeLaColeccion(nombreDeColeccion)) {
            this.coleccion = baseDeDatos.getCollection(nombreDeColeccion);
        } else {
            baseDeDatos.createCollection(nombreDeColeccion);
            this.coleccion = baseDeDatos.getCollection(nombreDeColeccion);
        }
    }
}
