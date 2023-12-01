package code;

import database.EmfSingleton;
import entities.ClientesEntity;
import entities.VentaprodEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;

public class ConsultarClientes {
    public static void listarClientes() {
        EntityManager em = EmfSingleton.getInstance().getEmf().createEntityManager();
        try {
            Query listarClientes = em.createQuery("from ClientesEntity");
            ArrayList<ClientesEntity> listaClientes = (ArrayList<ClientesEntity>) listarClientes.getResultList();

            for (ClientesEntity e : listaClientes) {
                Query listarCompras = em.createQuery("from VentaprodEntity WHERE idCliente = ?1");
                listarCompras.setParameter(1, e.getId());
                ArrayList<VentaprodEntity> listaCompras = (ArrayList<VentaprodEntity>) listarCompras.getResultList();

                int articulosComprados = 0;
                for (VentaprodEntity v : listaCompras) {
                    articulosComprados += v.getUnidades();
                }

                System.out.println("******************************");
                System.out.println(e.getId() + ". " + e.getNombre());
                System.out.println("Productos comprados: " + articulosComprados);
                System.out.println("******************************\n");
            }
        } catch (Exception e) {
            System.err.println(">>> Error: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
