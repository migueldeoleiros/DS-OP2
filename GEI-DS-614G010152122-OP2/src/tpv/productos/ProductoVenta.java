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
        return String.format("%-10s%10.0f%10.2f%15.2f%15.2f",
                getNombre(),
                getCantidad(),
                getPrecio(),
                getPrecio()+getPrecio()*impuestos,
                (getPrecio()+getPrecio()*impuestos)*getCantidad());
    }
}
