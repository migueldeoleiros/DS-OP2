package tpv;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tpv.productos.Ingrediente;
import tpv.productos.ProductoIndividual;
import tpv.productos.ProductoMultiple;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class tpvTest {
    Despensa despensa = new Despensa();
    Restaurante restaurante = new Restaurante(despensa);

    @Test
    void testDespensa() {
        ProductoIndividual agua = new ProductoIndividual("agua", "botella de 1 litro de agua", 1, 20);
        despensa.addProducto(agua);
        Ingrediente queso = new Ingrediente("queso", "queso en loncha", 7, 5);
        despensa.addProducto(queso);
        Ingrediente pan = new Ingrediente("pan", "pan para bocadillos", 1.50, 5);
        despensa.addProducto(pan);

        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(agua))
                assertEquals(20, producto.getCantidad());
            else if(producto.equals(queso))
                assertEquals(7, producto.getPrecio());
            else if(producto.equals(pan))
                assertEquals("pan para bocadillos", producto.getDescripcion());
        }
    }

    @Test
    void testPedir() {
        testDespensa();
        Ingrediente quesoB = new Ingrediente("queso", "queso en loncha", 7, 0.2);
        Ingrediente panB = new Ingrediente("pan", "pan para bocadillos", 1.50, 1);

        //comprueba que el equals de los productos es correcto
        for (Producto producto : despensa.getProductos()) {
            if(producto.getNombre().equals("pan")){
                assertEquals(panB, producto);
                assertEquals(5, producto.getCantidad());
            }
            else if(producto.getNombre().equals("queso"))
                assertEquals(quesoB, producto);
        }

        List<Producto> listaBocadillo = new ArrayList<>();
        listaBocadillo.add(quesoB);
        listaBocadillo.add(panB);
        ProductoMultiple bocadillo = new ProductoMultiple("bocadillo", "bocadillo de queso", listaBocadillo, 3.50, 0.10, 1);

        ProductoIndividual aguaM = new ProductoIndividual("agua", "botella de 1 litro de agua", 1, 1);
        List<Producto> listaMenu = new ArrayList<>();
        listaMenu.add(bocadillo);
        listaMenu.add(aguaM);
        ProductoMultiple menu = new ProductoMultiple("menu", "menu de bocadillo con agua", listaMenu, 0.85, 1);

        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(aguaM))
                assertEquals(20, producto.getCantidad());
            else if(producto.equals(panB))
                assertEquals(5, producto.getCantidad());
            else if(producto.equals(quesoB))
                assertEquals(5, producto.getCantidad());
        }

        Comanda comanda = new Comanda(1, despensa);
        restaurante.addComanda(comanda);
        assertTrue(comanda.pedir(bocadillo));
        // comprobar que el stock baja
        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(aguaM))
                assertEquals(20, producto.getCantidad());
            else if(producto.equals(panB))
                assertEquals(4, producto.getCantidad());
            else if(producto.equals(quesoB))
                assertEquals(4.8, producto.getCantidad());
        }

        assertTrue(comanda.pedir(menu));
        // comprobar que el stock baja
        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(aguaM))
                assertEquals(19, producto.getCantidad());
            else if(producto.equals(panB))
                assertEquals(3, producto.getCantidad());
            else if(producto.equals(quesoB))
                assertEquals(4.6, producto.getCantidad());
        }

    }
    @Test
    void testCuenta() {
        testPedir();
        assertEquals("", restaurante.getComandas().get(0).solicitarCuenta());
    }
}