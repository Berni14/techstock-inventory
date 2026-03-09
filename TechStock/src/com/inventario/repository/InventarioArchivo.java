package com.inventario.repository;

import com.inventario.model.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioArchivo implements IInventario {
    private final String RUTA_ARCHIVO = "inventario.dat";

    @Override
    public void guardarProductos(List<Producto> productos) {
        
        // try-with-resources: cierra el archivo automáticamente pase lo que pase
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) {
            oos.writeObject(productos);
            System.out.println("Sincronizando con el disco... ¡Datos guardados!");
        } catch (IOException e) {
            System.err.println("Error crítico al guardar: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("unchecked") // Para evitar el aviso amarillo al castear la lista
    public List<Producto> cargarProductos() {
        File archivo = new File(RUTA_ARCHIVO);
        
        // Si el archivo no existe (primera vez que abres la app), devolvemos lista vacía
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}