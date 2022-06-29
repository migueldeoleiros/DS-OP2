package tpv;

import java.util.Objects;

public abstract class Producto {
    private final String nombre;
    private String descripcion;
    private double precio;
    private double cantidad;

    public Producto(String nombre, String descripcion, double precio, double cantidad) {
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Decrementa en la despensa dada una cantidad los ingredientes del producto
     * @param despensa
     * @param cantidad
     * @return true si se lleva a cabo correctamente
     */
    public boolean decrementarProducto(Despensa despensa, double cantidad) {
        for(Producto i : despensa.getProductos())
            if(i.equals(this)) {
                if(i.getCantidad() - cantidad > 0){
                    i.setCantidad(i.getCantidad() - cantidad);
                    return true;
                }else break;
            }
        return false;
    }

    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto producto)) return false;
        return Double.compare(producto.precio, precio) == 0 &&
                              nombre.equals(producto.nombre) &&
                              Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, precio);
    }
}
