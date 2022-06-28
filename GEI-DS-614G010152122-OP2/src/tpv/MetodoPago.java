package tpv;

public interface MetodoPago {
    String pagar(Comanda c, double descuento);
    default String pagar(Comanda c, double descuento, double entregado){
        return pagar(c,descuento);
    }
}
