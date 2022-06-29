package tpv;

import org.junit.jupiter.api.Test;
import tpv.metodosPago.PagoTarjeta;
import tpv.productos.Ingrediente;
import tpv.productos.ProductoIndividual;
import tpv.productos.ProductoMultiple;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class pruebaGlobal {
    Despensa despensa = new Despensa();
    Restaurante restaurante = new Restaurante(despensa);

    void llenarDespensa() {
        ProductoIndividual agua = new ProductoIndividual("agua", "botella de 1 litro de agua", 1, 20);
        despensa.addProducto(agua);
        Ingrediente queso = new Ingrediente("queso", "queso en loncha", 7, 5);
        despensa.addProducto(queso);
        Ingrediente pan = new Ingrediente("pan", "pan para bocadillos", 1.50, 5);
        despensa.addProducto(pan);
    }

    void pedir() {
        Ingrediente quesoB = new Ingrediente("queso", "queso en loncha", 7, 0.2);
        Ingrediente panB = new Ingrediente("pan", "pan para bocadillos", 1.50, 1);

        List<Producto> listaBocadillo = new ArrayList<>();
        listaBocadillo.add(quesoB);
        listaBocadillo.add(panB);
        ProductoMultiple bocadillo = new ProductoMultiple("bocadillo", "bocadillo de queso", listaBocadillo, 3.50, 0.10, 1);

        ProductoIndividual agua = new ProductoIndividual("agua", "botella de 1 litro de agua", 1, 1);
        List<Producto> listaMenu = new ArrayList<>();
        listaMenu.add(bocadillo);
        listaMenu.add(agua);
        ProductoMultiple menu = new ProductoMultiple("menu", "menu de bocadillo con agua", listaMenu, 0.15, 2);

        Comanda comanda1 = new Comanda(1, despensa);
        restaurante.addComanda(comanda1);

        assertTrue(comanda1.pedir(bocadillo));
        assertTrue(comanda1.pedir(menu));

        Comanda comanda2 = new Comanda(2, despensa);
        restaurante.addComanda(comanda2);

        assertTrue(comanda2.pedir(agua));
        assertTrue(comanda2.pedir(bocadillo));
    }

    void comanda1() {
        Comanda comanda1 = restaurante.getComandas().get(0);
        System.out.println(comanda1.solicitarCuenta());
        System.out.println("\n--------------------------------\n");

        System.out.println(comanda1.pagar(new PagoTarjeta(), 0.10));
        System.out.println("\n--------------------------------\n");
    }

    void comanda2() {
        Comanda comanda2 = restaurante.getComandas().get(1);
        System.out.println(comanda2.solicitarCuenta());
        System.out.println("\n--------------------------------\n");

        System.out.println(comanda2.pagar(10));
        System.out.println("\n--------------------------------\n");
    }

    void cierreCaja() {
        System.out.println(restaurante.cerrarCaja());
        System.out.println("\n--------------------------------\n");
    }

    @Test
    void testGlobal() throws InterruptedException {
        llenarDespensa();
        pedir();
        Thread.sleep(1000);
        comanda1();
        Thread.sleep(1000);
        comanda2();
        Thread.sleep(1000);
        cierreCaja();
    }
}
