package tpv.productos;

import tpv.Despensa;
import tpv.Producto;

import java.util.List;

public class ProductoMultiple extends ProductoVenta {
    private List<Producto> listaIngredientes;

    public ProductoMultiple(String nombre, String descripcion, List<Producto> listaIngredentes, double precio, double impuestos, double cantidad) {
        super(nombre, descripcion, precio, cantidad, impuestos);
        this.listaIngredientes = listaIngredentes;
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

    @Override
    public boolean decrementarProducto(Despensa despensa, double cantidad) {
        for(Producto i :   this.listaIngredientes)
            if(!i.decrementarProducto(despensa, i.getCantidad() * cantidad))
                return false;
        return true;
    }

    public List<Producto> getListaIngredientes() {
        return listaIngredientes;
    }
    public void setListaIngredientes(List<Producto> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }
}
