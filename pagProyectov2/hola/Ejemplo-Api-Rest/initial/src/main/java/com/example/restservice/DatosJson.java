package com.example.restservice;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Service
public class DatosJson extends Acceso {
    public DatosJson() {
        super();
    }

    public List<Lista> obtenerListasUsuario(HashMap<String, Object> valoresRequeridos) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        List<Lista> listasUsuario = new ArrayList<>();
        Bson requisitosACumplir = null;

        for (Map.Entry<String, Object> elemento : valoresRequeridos.entrySet()) {
            requisitosACumplir = eq(elemento.getKey(), elemento.getValue());
        }
        ;


        FindIterable resultado = this.getColeccion().find(requisitosACumplir);
        MongoCursor iterador = resultado.iterator();

        while (iterador.hasNext()) {
            Document documento = (Document) iterador.next();

            List<Document> listas = (List<Document>) documento.get("listaDeCompras");
            for (Document document : listas) {
                int idAutoIncrementable = 0;
                HashMap<Integer, HashMap<String, Object>> items = new HashMap<>();
                int id = document.getInteger("idLista");
                String nombre = document.getString("nombreLista");
                List<Document> itemsListas = (List<Document>) document.get("items");
                if (itemsListas != null) {
                    for (Document document2 : itemsListas) {
                        HashMap<String, Object> auxiliar = new HashMap<>();
                        String descripcion = document2.getString("descripcion");
                        Boolean estado = document2.getBoolean("estado");
                        int clavePrimaria = document2.getInteger("id");
                        auxiliar.put("id", clavePrimaria);
                        auxiliar.put("estado", estado);
                        auxiliar.put("descripcion", descripcion);
                        items.put(idAutoIncrementable, auxiliar);
                        idAutoIncrementable++;
                    }
                }
                Lista lista = new Lista(id, items, nombre);
                listasUsuario.add(lista);
            }
        }
        return listasUsuario;
    }

    public void agregarItem(HashMap<String, Object> valoresRequeridos, String nombreItem, boolean estado) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        HashMap<String, Object> auxiliar = new HashMap<>();
        HashMap<String, Object> infoItem = new HashMap<>();
        auxiliar.put("idUsuario", (Integer) valoresRequeridos.get("idUsuario"));
        infoItem.put("id", this.obtenerCantidadItems(auxiliar, (Integer) valoresRequeridos.get("listaDeCompras.idLista")));
        infoItem.put("descripcion", nombreItem);
        infoItem.put("estado", estado);
        String rutaDelElemento = "listaDeCompras.$.items";
        Document infoConRutaYObjetoAInsertar = new Document(rutaDelElemento, infoItem);
        Document filtro = new Document(valoresRequeridos);
        Document operacionConInfo = new Document("$push", infoConRutaYObjetoAInsertar);
        UpdateResult resultado = this.getColeccion().updateOne(filtro, operacionConInfo);
    }

    public int obtenerCantidadItems(HashMap<String, Object> valoresRequeridos, int idLista) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        List<Lista> listas = this.obtenerListasUsuario(valoresRequeridos);
        int contador = 0;
        for (Lista listaActual : listas) {
            if (listaActual.getClavePrimaria() == idLista) {
                for (Map.Entry<Integer, HashMap<String, Object>> itemActual : listaActual.getItems().entrySet()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public boolean verificarUsuario(HashMap<String, Object> valoresRequeridos) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");

        List<Bson> filtros = new ArrayList<>();
        for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
            Bson equivalencia = eq(valor.getKey(), valor.getValue());
            filtros.add(equivalencia);
        }
        Bson requisitosACumplir = Filters.and(filtros);
        FindIterable resultado = this.getColeccion().find(requisitosACumplir);
        MongoCursor iterador = resultado.iterator();

        if (iterador.hasNext()) {
            Document documento = (Document) iterador.next();
            int idUsuario = documento.getInteger("idUsuario");
            return true;
        }
        return false;
    }

    public int obtenerIdUsuario(HashMap<String, Object> valoresRequeridos) {
        if (this.verificarUsuario(valoresRequeridos)) {
            this.conectarABaseDeDatos("proyectoIntegrador");
            this.conectarAColeccion("usuario");
            List<Bson> filtros = new ArrayList<>();
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            FindIterable resultado = this.getColeccion().find(requisitosACumplir);
            MongoCursor iterador = resultado.iterator();

            Document documento = (Document) iterador.next();
            int idUsuario = documento.getInteger("idUsuario");
            return idUsuario;

        }
        return -1;
    }


    public String obtenerInfoUsuario(HashMap<String, Object> valoresRequeridos) {
        if (this.verificarUsuario(valoresRequeridos)) {
            this.conectarABaseDeDatos("proyectoIntegrador");
            this.conectarAColeccion("usuario");

            List<Bson> filtros = new ArrayList<>();
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            FindIterable resultado = this.getColeccion().find(requisitosACumplir);
            MongoCursor iterador = resultado.iterator();

            Document documento = (Document) iterador.next();
            String nombreUsuario = documento.getString("nombre");
            return nombreUsuario;

        }
        return null;
    }

    public int insertarUsuario(HashMap<String, Object> datosUsuario) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        int idUsuario = this.contarElementos(null, "usuario");
        Document documento = new Document().append("idUsuario", idUsuario).append("nombre", datosUsuario.get("nombre")).append("mail", datosUsuario.get("mail")).append("clave", datosUsuario.get("clave")).append("despensa", new ArrayList<>()).append("listaDeCompras", new ArrayList<>()).append("recetasGuardadas", new ArrayList<>()).append("menuSemanal", new ArrayList<>()).append("recetasCalificadas", new ArrayList<>());

        this.getColeccion().insertOne(documento);
        return idUsuario;
    }


    public int contarElementos(HashMap<String, Object> valoresRequeridos, String coleccion) {
        int ultimoId = 0;
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion(coleccion);
        FindIterable resultado;
        List<Bson> filtros = new ArrayList<>();
        if (valoresRequeridos != null) {
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            resultado = this.getColeccion().find(requisitosACumplir);
        } else {
            resultado = this.getColeccion().find();
        }

        MongoCursor iterador = resultado.iterator();

        while (iterador.hasNext()) {
            Document documento = (Document) iterador.next();
            ultimoId++;
        }
        return ultimoId;
    }







}
