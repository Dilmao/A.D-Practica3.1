package database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmfSingleton {
    //Instancia del singleton de la factoria
    private static final EmfSingleton emfSinstancia = new EmfSingleton();

    //Unidad de persistencia donde estan las entidades
    static private final String PERSISTENCE_UNIT_NAME = "default";

    //La factoria se define como privada
    private EntityManagerFactory emf  = null;

    //Metodo que devuelve la instancia del singleton que permite acceder a la factoria
    public static EmfSingleton getInstance() {
        return emfSinstancia;
    }

    private EmfSingleton() {
    }

    //Los entity manager se creatan a partir de la factoria que devuelve este metodo
    public EntityManagerFactory getEmf() {
        //La creacion de la factoria solo se realiza la primera vez que se llama al metodo
        if (this.emf == null)
            this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        return this.emf;
    }
}
