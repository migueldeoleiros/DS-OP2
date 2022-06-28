package tpv;

import org.junit.jupiter.api.Test;
import tpv.metodosPago.PagoEfectivo;
import tpv.productos.Ingrediente;
import tpv.productos.ProductoIndividual;
import tpv.productos.ProductoMultiple;

import java.text.SimpleDateFormat;
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
        ProductoMultiple menu = new ProductoMultiple("menu", "menu de bocadillo con agua", listaMenu, 0.15, 2);

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
                assertEquals(4.80, Math.round(producto.getCantidad() * 100.0) / 100.0);
        }

        assertTrue(comanda.pedir(menu));
        // comprobar que el stock baja
        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(aguaM))
                assertEquals(18, producto.getCantidad());
            else if(producto.equals(panB))
                assertEquals(2, producto.getCantidad());
            else if(producto.equals(quesoB))
                assertEquals(4.40, Math.round(producto.getCantidad() * 100.0) / 100.0);
        }

    }
    @Test
    void testCuentaPagar() {
        testPedir();
        Comanda comanda = restaurante.getComandas().get(0);
        String s =
                "# Mesa numero 1" + "\n" +
                "# " + (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()))+ "\n" +
                "Producto	 Cantidad 	 Precio 	 PVP unidad 	 PVP total" + "\n" +
                "==============================================================" + "\n" +
                "bocadillo          1      3.15           3.47           3.47" + "\n" +
                "menu               2      3.53           3.88           7.76" + "\n" +
                "\n" +
                "# Pendiente de combro" + "\n" +
                "Total sin impuestos 6.68" + "\n" +
                "Total de impuestos 0.67" + "\n" +
                "PVP impuestos 7.35";
        assertEquals(s, comanda.solicitarCuenta());

        s =
                "# Factura simplificada numero 0" + "\n" +
                "# Mesa numero 1" + "\n" +
                "# " + (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()))+ "\n" +
                "Producto	 Cantidad 	 Precio 	 PVP unidad 	 PVP total" + "\n" +
                "==============================================================" + "\n" +
                "bocadillo          1      3.15           3.47           3.47" + "\n" +
                "menu               2      3.53           3.88           7.76" + "\n" +
                "\n" +
                "# Total" + "\n" +
                "Total sin impuestos 6.68" + "\n" +
                "Total de impuestos 0.67" + "\n" +
                "PVP impuestos 7.35" + "\n" +
                "\n" +
                "# Forma de pago:" + "\n" +
                "Descuento del 10.00%" + "\n" +
                "Descuento 0.73" + "\n" +
                "Total 6.61";
        assertEquals(s, comanda.pagar(new PagoEfectivo(), 0.10));
    }

    @Test
    void testCaja() {
        testCuentaPagar();
        String s =
                "# Cierre de caja" + "\n" +
                "# " + (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()))+ "\n" +
                "Producto            Cantidad" + "\n" +
                "============================" + "\n" +
                "bocadillo               1" + "\n" +
                "menu                    2" + "\n" +
                "\n" +
                "# Total    6.68" + "\n" +
                "Impuestos  0.67" + "\n" +
                "Ingresos   6.01";
        assertEquals(s, restaurante.cerrarCaja());
    }

    @Test
    void testGlobal() {
        //mostrar por pantalla los outputs
    }
}