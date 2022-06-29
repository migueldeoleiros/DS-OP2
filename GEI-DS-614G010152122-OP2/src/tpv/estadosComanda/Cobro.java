package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.MetodoPago;
import tpv.productos.ProductoVenta;

public class Cobro implements Estado {
    private static final Cobro instancia = new Cobro();
    private Cobro(){}
    public static Cobro getInstance() {return instancia;}

    private MetodoPago metodoPago;

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public boolean pedir(Comanda c, ProductoVenta pedido) {
        c.setEstado(Pedir.getInstance());
        return c.pedir(pedido);
    }

    @Override
    public String pagar(Comanda c, double descuento, double entregado) {
        c.setEstado(Cerrado.getInstance());
        return metodoPago.pagar(c, descuento, entregado);
    }
}
