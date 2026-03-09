package com.inventario.service;

import com.inventario.model.Producto;
import com.inventario.repository.IInventario;
import java.util.List;
import java.util.stream.Collectors;

public class InventarioService {
    private List<Producto> productos;
    private IInventario repository;

    public InventarioService(IInventario repository) {
        this.repository = repository;
        this.productos = repository.cargarProductos();
    }

    // 1. Agregar con validación de ID único
    public boolean agregarProducto(Producto nuevo) {
        // Buscamos si ya existe un producto con ese ID
        boolean existe = productos.stream().anyMatch(p -> p.getId() == nuevo.getId());
        
        if (existe) {
            System.out.println("Error: Ya existe un producto con el ID " + nuevo.getId());
            return false;
        }
        
        productos.add(nuevo);
        repository.guardarProductos(productos); // Guardar cambios en disco
        return true;
    }

    // 2. Eliminar producto por ID
    public boolean eliminarProducto(int id) {
        // removeIf devuelve true si encontró y borró algo
        boolean eliminado = productos.removeIf(p -> p.getId() == id);
        
        if (eliminado) {
            repository.guardarProductos(productos);
            System.out.println("Producto con ID " + id + " eliminado con éxito.");
        } else {
            System.out.println("No se encontró ningún producto con ese ID.");
        }
        return eliminado;
    }

    // 3. Buscar por nombre (puede haber varios, ej: "Teclado")
    public List<Producto> buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    // 4. Calcular el valor total del inventario (Precio * Stock de cada uno)
    public double calcularValorTotal() {
        return productos.stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())
                .sum();
    }

    public List<Producto> listarTodos() {
        return productos;
    }
}