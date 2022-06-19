package tpv.productos;

import tpv.Producto;

public abstract class ProductoVenta extends Producto {
    private double impuestos = 0.10;

    public ProductoVenta(String nombre, String descripcion, double precio, int cantidad) {
        super(nombre, descripcion, precio, cantidad);
    }

    public double getImpuestos() {
        return impuestos;
    }
    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

}
