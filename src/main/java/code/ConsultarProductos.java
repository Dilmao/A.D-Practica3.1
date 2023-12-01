package code;

import database.EmfSingleton;
import entities.ProductosEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class ConsultarProductos {
    public static void listarProductos() {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        try {
            Query listarProductos = em.createQuery("from ProductosEntity");
            ArrayList<ProductosEntity> listaProductos = (ArrayList<ProductosEntity>) listarProductos.getResultList();

            for (ProductosEntity p : listaProductos) {
                Query listarUnidades = em.createQuery("from VentaprodEntity  WHERE idProducto = ?1");
                listarUnidades.setParameter(1, p.getId());
                ArrayList<VentaprodEntity> listaUnidades = (ArrayList<VentaprodEntity>) listarUnidades.getResultList();

                int unidadesCompradas = 0;
                for (VentaprodEntity v : listaUnidades) {
                    unidadesCompradas += v.getUnidades();
                }

                System.out.println("******************************");
                System.out.println(p.getId() + ". " + p.getDescripcion());
                System.out.println("Unidades vendidas: " + unidadesCompradas);
                System.out.println("******************************\n");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
