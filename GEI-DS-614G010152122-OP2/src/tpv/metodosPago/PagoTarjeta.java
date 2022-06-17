package tpv.metodosPago;

import tpv.Comanda;
import tpv.MetodoPago;

public class PagoTarjeta implements MetodoPago {
    @Override
    public String pagar(Comanda c) {
        return null;
    }

    public String pagar(Comanda c, double descuento) {
        return null;
    }
}
