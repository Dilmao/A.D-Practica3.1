import database.EmfSingleton;
import jakarta.persistence.EntityManagerFactory;

public class Menu {
    public static void main(String[] args) {
        boolean salir = false;
        String opcion;
        do {
            System.out.println("******************************************************");
            System.out.println("0. Salir");
            System.out.println("1. Listar clientes y numero de productos que ha comprado");
            System.out.println("2. Listar los productos y numero de unidades vendidas");
            System.out.println("3. Insertar una venta");
            System.out.println("4. Obtener un listado de ventas de un cliente");
            System.out.println("******************************************************");
            opcion = libs.Leer.pedirCadena("Introduce una opcion");

            switch (opcion) {
                case "0": {
                    desconectar();
                    salir = true;
                    break;
                }
                case "1": {
                    code.ConsultarClientes.listarClientes();
                    break;
                }
                case "2": {
                    code.ConsultarProductos.listarProductos();
                    break;
                }
                case "3": {
                    code.ConsultarVentas.insertarVenta();
                    break;
                }
                case "4": {//Actualmente haciendo este
                    code.ConsultarVentas.listarVentasCliente();
                    break;
                }
                default: System.err.println("La opción introducida no es valida");
            }
        } while(!salir);
    }

    private static void desconectar(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        emf.close();
    }
}
