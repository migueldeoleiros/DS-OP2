package tpv;

import org.junit.jupiter.api.Test;
import tpv.estadosComanda.Cancelado;
import tpv.estadosComanda.Impagado;
import tpv.metodosPago.*;
import tpv.productos.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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

        despensa.decrementarProducto(agua, 1);
        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(agua))
                assertEquals(19, producto.getCantidad());
        }
        despensa.incrementarProducto(agua, 1);
        for (Producto producto : despensa.getProductos()) {
            if(producto.equals(agua))
                assertEquals(20, producto.getCantidad());
        }

        Ingrediente quesoB = new Ingrediente("queso", "queso en loncha", 7, 0);
        Ingrediente panB = new Ingrediente("pan", "pan para bocadillos", 1.50, 0);

        //comprueba que el equals de los productos es correcto
        for (Producto producto : despensa.getProductos()) {
            if(producto.getNombre().equals("pan")){
                assertEquals(panB, producto);
                assertEquals(5, producto.getCantidad());
            }
            else if(producto.getNombre().equals("queso"))
                assertEquals(quesoB, producto);
        }
    }

    @Test
    void testPedir() {
        testDespensa();
        Ingrediente quesoB = new Ingrediente("queso", "queso en loncha", 7, 0.2);
        Ingrediente panB = new Ingrediente("pan", "pan para bocadillos", 1.50, 1);

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
    void testCancelar() {
        testDespensa();
        Ingrediente quesoB = new Ingrediente("queso", "queso en loncha", 7, 0.2);
        Ingrediente panB = new Ingrediente("pan", "pan para bocadillos", 1.50, 1);

        List<Producto> listaBocadillo = new ArrayList<>();
        listaBocadillo.add(quesoB);
        listaBocadillo.add(panB);
        ProductoMultiple bocadillo = new ProductoMultiple("bocadillo", "bocadillo de queso", listaBocadillo, 3.50, 0.10, 1);

        Comanda comanda = new Comanda(1, despensa);
        restaurante.addComanda(comanda);

        assertTrue(comanda.pedir(bocadillo));
        comanda.setEstado(Cancelado.getInstance());
        assertNull(comanda.solicitarCuenta());
    }

    @Test
    void testCuenta() {
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
    }

    @Test
    void testImpagar() {
        testPedir();
        Comanda comanda = restaurante.getComandas().get(0);

        comanda.setEstado(Impagado.getInstance());

        ProductoIndividual agua = new ProductoIndividual("agua", "botella de 1 litro de agua", 1, 1);
        assertFalse(comanda.pedir(agua));

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
    }

    @Test
    void testTarjeta() {
        testCuenta();
        Comanda comanda = restaurante.getComandas().get(0);

        String s =
                        "# Factura simplificada numero " +
                        (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+ "\n" +
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
                        "# Forma de pago: Tarjeta" + "\n" +
                        "Descuento del 10.00%" + "\n" +
                        "Descuento 0.73" + "\n" +
                        "Total 6.61";
        assertEquals(s, comanda.pagar(new PagoTarjeta(), 0.10));
    }
    @Test
    void testEfectivo() {
        testCuenta();
        Comanda comanda = restaurante.getComandas().get(0);

        String s =
                        "# Factura simplificada numero " +
                        (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+ "\n" +
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
                        "# Forma de pago: Efectivo" + "\n" +
                        "Total 7.35" + "\n" +
                        "Entregado 10.00" + "\n" +
                        "Devolucion 2.65";
                assertEquals(s, comanda.pagar(10));
    }
    @Test
    void testEfectivoDescuento() {
        testCuenta();
        Comanda comanda = restaurante.getComandas().get(0);

        String s =
                        "# Factura simplificada numero " +
                        (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+ "\n" +
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
                        "# Forma de pago: Efectivo" + "\n" +
                        "Descuento del 10.00%" + "\n" +
                        "Descuento 0.73" + "\n" +
                        "Total 6.61" + "\n" +
                        "Entregado 10.00" + "\n" +
                        "Devolucion 3.39";
        assertEquals(s, comanda.pagar(10,0.10));
    }
    @Test
    void testCheque() {
        testCuenta();
        Comanda comanda = restaurante.getComandas().get(0);

        String s =
                        "# Factura simplificada numero " +
                        (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+ "\n" +
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
                        "# Forma de pago: Cheque regalo" + "\n" +
                        "Total 7.35";
        assertEquals(s, comanda.pagar(new PagoCheque()));
    }
    @Test
    void testInvitado() {
        testCuenta();
        Comanda comanda = restaurante.getComandas().get(0);

        String s =
                        "# Factura simplificada numero " +
                        (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+ "\n" +
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
                        "# Forma de pago: Invitado" + "\n" +
                        "Total 7.35";
        assertEquals(s, comanda.pagar(new PagoInvitacion()));
    }

    @Test
    void testCaja() {
        testTarjeta();
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
}