package tpv;

import java.util.HashMap;

public class Despensa {
    HashMap<Producto, Integer> productos = new HashMap<>();

    public void addProducto(Producto producto, int cantidad){
        this.productos.put(producto,cantidad);
    }

    public boolean removeProducto(Producto producto, int cantidad){
        int oldCantidad = this.productos.get(producto);
        if(oldCantidad-cantidad > 0){
            this.productos.replace(producto, oldCantidad-cantidad);
            return true;
        }else return false;
    }

}
