package code;

import database.EmfSingleton;
import entities.ClientesEntity;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultarVentas {
    public static void insertarVenta() {
        Long cliente = listarClientes("Introduzca el nombre del cliente que hara la compra");
        Long producto = listarProductos("Introduzca el nombre del producto que comprara");
        short unidades = (short) libs.Leer.pedirEntero("¿Cuantas unidades desea comprar?");

        // Comprobacion de la respuesta del usuario
        try {
            EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();

            VentaprodEntity venta = new VentaprodEntity();
            venta.setIdCliente(cliente);
            venta.setIdProducto(producto);
            venta.setUnidades(unidades);
            venta.setFecha(Date.valueOf(LocalDate.now()));

            em.persist(venta);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }

    public static void listarVentasCliente() {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        Long clienteId = listarClientes("Introduzca el nombre del cliente que quiere listar");

        try {

            // Se consiguen las ventas del cliente a base de su id
            Query listarVentas = em.createQuery("from VentaprodEntity where idCliente = ?1");
            listarVentas.setParameter(1, clienteId);
            ArrayList<VentaprodEntity> listaProductos = (ArrayList<VentaprodEntity>) listarVentas.getResultList();

            for (VentaprodEntity v : listaProductos) {
                System.out.println("******************************");
                System.out.println(v.getClientesByIdCliente().getNombre());
                System.out.println("Producto comprado: " + v.getProductosByIdProducto().getDescripcion());
                System.out.println("Unidades compradas: " + v.getUnidades());
                System.out.println("Fecha de compra: " + v.getFecha());
                System.out.println("******************************\n");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }


    //**************************************** Funciones para la pedida de datos ****************************************
    private static Long listarClientes(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String cliente;
        Long clienteId = null;
        boolean clienteValido = false;
        try {
            Query listarClientes = em.createQuery("from ClientesEntity");
            ArrayList<ClientesEntity> listaClientes = (ArrayList<ClientesEntity>) listarClientes.getResultList();

            System.out.println("******************************");
            for (ClientesEntity c : listaClientes) {
                System.out.println(c.getId() + ". " + c.getNombre());
            }
            System.out.println("******************************");
            cliente = libs.Leer.pedirCadena(mensaje);

            for (ClientesEntity c : listaClientes) {
                if (c.getNombre().toLowerCase().contains(cliente.toLowerCase())) {
                    Query conseguirId = em.createQuery("select id from ClientesEntity where nombre like ?1");
                    conseguirId.setParameter(1, c.getNombre());
                    clienteId = (Long) conseguirId.getSingleResult();

                    clienteValido = true;
                }
            }

            if (!clienteValido) {
                System.out.println("El cliente introducido no existe");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }

        if (clienteValido) {
            return clienteId;
        } else {
            return null;
        }
    }

    private static Long listarProductos(String mensaje) {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        String producto = "";
        Long productoId = null;
        boolean productoValido = false;

        try {
            Query listarProductos = em.createQuery("from ProductosEntity ");
            ArrayList<ProductosEntity> listaProductos = (ArrayList<ProductosEntity>) listarProductos.getResultList();

            System.out.println("******************************");
            for (ProductosEntity p : listaProductos) {
                System.out.println(p.getId() + ". " + p.getDescripcion());
            }
            System.out.println("******************************");
            producto = libs.Leer.pedirCadena(mensaje);

            for (ProductosEntity p : listaProductos) {
                if (p.getDescripcion().toLowerCase().contains(producto.toLowerCase())) {
                    Query conseguirId = em.createQuery("select id from ProductosEntity where descripcion like ?1");
                    conseguirId.setParameter(1, p.getDescripcion());
                    productoId = (Long) conseguirId.getSingleResult();

                    productoValido = true;
                }
            }

            if (!productoValido) {
                System.out.println("El producto introducido no existe");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }

        if (productoValido) {
            return productoId;
        } else {
            return null;
        }
    }
}
