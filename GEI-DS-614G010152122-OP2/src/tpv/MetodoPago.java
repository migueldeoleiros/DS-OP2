package tpv;

public interface MetodoPago {

    default String pagar(Comanda c){return null;}
    default String pagar(Comanda c, double descuento){return null;}
}
