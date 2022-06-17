package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.MetodoPago;

public class Impagado implements Estado {
    private static final Impagado instancia = new Impagado();
    private Impagado(){}
    public static Impagado getInstance() {return instancia;}

    private MetodoPago metodoPago;

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String solicitarCuenta(Comanda c) {
        return null;
    }

    @Override
    public String pagar(Comanda c) {
        return metodoPago.pagar(c);
    }
}
