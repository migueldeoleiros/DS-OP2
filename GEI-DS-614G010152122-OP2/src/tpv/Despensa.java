package tpv;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Despensa {
    private final List<Producto> productos = new ArrayList<>();

    /**
     * Introduce un producto nuevo a la lista
     * @param producto a introducir
     */
    public void addProducto(Producto producto){
        this.productos.add(producto);
    }

    /**
     * Elimina un producto de la lista
     * @param producto a eliminar
     */
    public void removeProducto(Producto producto){
        this.productos.remove(producto);
    }

    /**
     * Incrementa la cantidad de un producto
     * @param producto a incrementar
     * @param cantidad a incrementar
     * @return true si el producto existe
     */
    public boolean incrementarProducto(Producto producto, float cantidad){
        for(Producto i : this.productos)
            if(Objects.equals(i, producto)){
                i.setCantidad(i.getCantidad()+cantidad);
                return true;
            }
        return false;
    }

    /**
     * Decrementa la cantidad de un producto
     * @param producto a decrementar
     * @param cantidad a decrementar
     * @return true si el producto puede ser decrementado
     */
    public boolean decrementarProducto(Producto producto, double cantidad) {
        return producto.decrementarProducto(this, cantidad);
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
