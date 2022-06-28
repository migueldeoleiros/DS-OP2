package tpv.productos;

import tpv.Producto;

import java.util.List;

public class ProductoMultiple extends ProductoVenta {
    private List<Producto> listaIngredientes;

    public ProductoMultiple(String nombre, String descripcion, List<Producto> listaIngredentes, double precio, double impuestos, double cantidad) {
        super(nombre, descripcion, precio, cantidad, impuestos);
        this.listaIngredientes = listaIngredentes;
        super.setPrecio(precio - (precio * super.getImpuestos()));
    }
    public ProductoMultiple(String nombre, String descripcion, List<Producto> listaIngredentes, double descuento, double cantidad) {
        super(nombre, descripcion, 0, cantidad, 0.10);
        double precio = 0;
        for(Producto listaIngredente : listaIngredentes) {
            precio += (listaIngredente.getPrecio()-listaIngredente.getPrecio()*descuento);
        }
        super.setPrecio(precio);
        this.listaIngredientes = listaIngredentes;
    }

    public List<Producto> getListaIngredientes() {
        return listaIngredientes;
    }
    public void setListaIngredientes(List<Producto> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
}
