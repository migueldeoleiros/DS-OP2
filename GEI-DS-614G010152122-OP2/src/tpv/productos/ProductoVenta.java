package tpv.productos;

import tpv.Producto;

public abstract class ProductoVenta extends Producto {
    private double impuestos = 0.10;

    public ProductoVenta(String nombre, String descripcion, double precio, double cantidad) {
        super(nombre, descripcion, precio, cantidad);
    }
    public ProductoVenta(String nombre, String descripcion, double precio, double cantidad, double impuestos) {
        super(nombre, descripcion, precio, cantidad);
        this.impuestos = impuestos;
    }

    public double getImpuestos() {
        return impuestos;
    }
    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    @Override
    public String toString() {
        return getNombre() + "\t" +
                getCantidad() + "\t" +
                getPrecio()+getPrecio()*impuestos + "\t" +
                (getPrecio()+getPrecio()*impuestos)*getCantidad();
    }
}
