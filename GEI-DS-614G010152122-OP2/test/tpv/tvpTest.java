package tpv;

import org.junit.jupiter.api.Test;
import tpv.productos.Ingrediente;
import tpv.productos.ProductoMultiple;

import java.util.*;

class tpvTest {
    Despensa despensa = new Despensa();
    Restaurante restaurante = new Restaurante(despensa);
    @Test
    void TestlistaProductos() {
        Ingrediente huevo = new Ingrediente("huevo", "huevo de gallina", 0.25);
        Ingrediente lechuga = new Ingrediente("lechuga", "lechuga iceberg", 0.30);
        Ingrediente aceituna = new Ingrediente("aceituna", "aceituna espanola", 0.05);
        Map<Producto,Integer> listaIngredientes= new HashMap<>();
        listaIngredientes.put(huevo, 2);
        listaIngredientes.put(lechuga, 1);
        listaIngredientes.put(aceituna, 6);
        Producto ensalada = new ProductoMultiple("ensalada", "ensalada de huevo y aceituna", 5, listaIngredientes) {
        };

    }
}