package tpv;

import tpv.productos.ProductoMultiple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Despensa {
    private List<Producto> productos = new ArrayList<>();

    public void addProducto(Producto producto){
        this.productos.add(producto);
    }

    public void removeProducto(Producto producto){
        this.productos.remove(producto);
    }

    public boolean incrementarProducto(Producto producto, double cantidad){
        for(Producto i : this.productos)
            if(Objects.equals(i, producto)){
                i.setCantidad(i.getCantidad()+cantidad);
                return true;
            }
        return false;
    }

    //TODO cambiar getClass
    public boolean decrementarProducto(Producto producto, double cantidad){
        if(producto.getClass() == ProductoMultiple.class){
            for(Producto i : ((ProductoMultiple) producto).getListaIngredientes())
                decrementarProducto(i,i.getCantidad()*producto.getCantidad());
        }
        for(Producto i : this.productos)
            if(Objects.equals(i, producto)) {
                if(i.getCantidad()-cantidad > 0){
                    i.setCantidad(i.getCantidad() - cantidad);
                    return true;
                }else break;
            }
        return false;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
