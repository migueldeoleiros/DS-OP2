package tpv;

import tpv.productos.ProductoVenta;

public interface Estado {

    default boolean pedir(Comanda c, ProductoVenta pedido){ return false; }

    default String solicitarCuenta(Comanda c){ return null; }

    default String pagar(Comanda c, double descuento, double entregado) { return null; }

    default void setMetodoPago(MetodoPago metodoPago){}
}
