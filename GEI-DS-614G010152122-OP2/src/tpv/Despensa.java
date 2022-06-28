package tpv;

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

    public boolean incrementarProducto(Producto producto, float cantidad){
        for(Producto i : this.productos)
            if(Objects.equals(i, producto)){
                i.setCantidad(i.getCantidad()+cantidad);
                return true;
            }
        return false;
    }

    public boolean decrementarProducto(Producto producto, double cantidad) {
        return producto.decrementarProducto(this, cantidad);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
