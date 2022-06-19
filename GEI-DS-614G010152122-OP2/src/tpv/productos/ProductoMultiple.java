package tpv.productos;

import tpv.Producto;

import java.util.List;

public class ProductoMultiple extends ProductoVenta {
    List<Producto> listaIngredentes;
    public ProductoMultiple(String nombre, String descripcion, double precio, List<Producto> listaIngredentes, int cantidad) {
        super(nombre, descripcion, precio, cantidad);
        this.listaIngredentes = listaIngredentes;
    }

}
