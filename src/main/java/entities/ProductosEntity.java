package entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTOS", schema = "ROOT", catalog = "")
public class ProductosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;
    @Basic
    @Column(name = "COD_PRODUCTO", nullable = false, precision = 0)
    private Short codProducto;
    @Basic
    @Column(name = "DESCRIPCION", nullable = false, length = 20)
    private String descripcion;
    @Basic
    @Column(name = "LINEA_PRODUCTO", nullable = true, length = 10)
    private String lineaProducto;
    @Basic
    @Column(name = "PRECIO", nullable = true, precision = 0)
    private Short precio;
    @OneToMany(mappedBy = "productosByIdProducto")
    private Collection<VentaprodEntity> ventaprodsById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Short codProducto) {
        this.codProducto = codProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLineaProducto() {
        return lineaProducto;
    }

    public void setLineaProducto(String lineaProducto) {
        this.lineaProducto = lineaProducto;
    }

    public Short getPrecio() {
        return precio;
    }

    public void setPrecio(Short precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosEntity that = (ProductosEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(codProducto, that.codProducto) && Objects.equals(descripcion, that.descripcion) && Objects.equals(lineaProducto, that.lineaProducto) && Objects.equals(precio, that.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codProducto, descripcion, lineaProducto, precio);
    }

    public Collection<VentaprodEntity> getVentaprodsById() {
        return ventaprodsById;
    }

    public void setVentaprodsById(Collection<VentaprodEntity> ventaprodsById) {
        this.ventaprodsById = ventaprodsById;
    }
}
