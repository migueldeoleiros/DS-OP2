package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.MetodoPago;

public class Cobro implements Estado {
    private static final Cobro instancia = new Cobro();
    private Cobro(){}
    public static Cobro getInstance() {return instancia;}

    private MetodoPago metodoPago;

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String pagar(Comanda c) {
        return metodoPago.pagar(c, 0);
    }
    @Override
    public String pagar(Comanda c, double descuento) {
        return metodoPago.pagar(c, descuento);
    }
}
