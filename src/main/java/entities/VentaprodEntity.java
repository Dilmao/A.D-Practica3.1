package entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "VENTAPROD", schema = "ROOT", catalog = "")
public class VentaprodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;
    @Basic
    @Column(name = "ID_CLIENTE", nullable = false, precision = 0)
    private Long idCliente;
    @Basic
    @Column(name = "ID_PRODUCTO", nullable = false, precision = 0)
    private Long idProducto;
    @Basic
    @Column(name = "UNIDADES", nullable = false, precision = 0)
    private Short unidades;
    @Basic
    @Column(name = "FECHA", nullable = true)
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    private ClientesEntity clientesByIdCliente;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    private ProductosEntity productosByIdProducto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Short getUnidades() {
        return unidades;
    }

    public void setUnidades(Short unidades) {
        this.unidades = unidades;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VentaprodEntity that = (VentaprodEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(idCliente, that.idCliente) && Objects.equals(idProducto, that.idProducto) && Objects.equals(unidades, that.unidades) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCliente, idProducto, unidades, fecha);
    }

    public ClientesEntity getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(ClientesEntity clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    public ProductosEntity getProductosByIdProducto() {
        return productosByIdProducto;
    }

    public void setProductosByIdProducto(ProductosEntity productosByIdProducto) {
        this.productosByIdProducto = productosByIdProducto;
    }
}
