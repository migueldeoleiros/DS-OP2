package tpv.productos;

import tpv.Producto;

import java.util.HashMap;
import java.util.Map;

public class ProductoMultiple extends Producto {
    private double impuestos = 0.10;
    Map<Producto, Integer> listaIngredentes;
    public ProductoMultiple(String nombre, String descripcion, double precio, Map<Producto, Integer> listaIngredentes) {
        super(nombre, descripcion, precio);
        this.listaIngredentes = listaIngredentes;
    }

    public double getImpuestos() {
        return impuestos;
    }
    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }
}
