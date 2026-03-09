package com.inventario.repository;

import com.inventario.model.Producto;
import java.util.List;

public interface IInventario {
    void guardarProductos(List<Producto> productos);

    List<Producto> cargarProductos();
}