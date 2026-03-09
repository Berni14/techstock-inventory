package com.inventario.main;

import com.inventario.model.Producto;
import com.inventario.repository.InventarioArchivo;
import com.inventario.service.InventarioService;
import java.util.Scanner;

public class main {
    private static final Scanner sc = new Scanner(System.in);
    // Inyectamos el repositorio en el servicio (Inyección de dependencias básica)
    private static final InventarioService service = new InventarioService(new InventarioArchivo());

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n========== TECHSTOCK PRO - GESTIÓN ==========");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar nuevo producto");
            System.out.println("3. Eliminar producto por ID");
            System.out.println("4. Calcular valor total del inventario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1 -> listar();
                case 2 -> agregar();
                case 3 -> eliminar();
                case 4 -> System.out.printf("Valor total en almacén: %.2f€\n", service.calcularValorTotal());
                case 5 -> System.out.println("Cerrando sistema... ¡Buen trabajo!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void listar() {
        System.out.println("\n--- INVENTARIO ACTUAL ---");
        if (service.listarTodos().isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            service.listarTodos().forEach(System.out::println);
        }
    }

    private static void agregar() {
        System.out.print("ID (numérico): ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Categoría: ");
        String cat = sc.nextLine();
        System.out.print("Stock inicial: ");
        int stock = sc.nextInt();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        Producto p = new Producto(id, nombre, cat, stock, precio);
        if (service.agregarProducto(p)) {
            System.out.println("¡Producto añadido con éxito!");
        }
    }

    private static void eliminar() {
        System.out.print("Introduce el ID del producto a borrar: ");
        int id = sc.nextInt();
        service.eliminarProducto(id);
    }
}