package tpv.productos;

import tpv.Producto;

public class ProductoIndividual extends Producto {
    private double impuestos = 0.10;

    public ProductoIndividual(String nombre, String descripcion, double precio) {
        super(nombre, descripcion, precio);
    }

    public double getImpuestos() {
        return impuestos;
    }
    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }
}
