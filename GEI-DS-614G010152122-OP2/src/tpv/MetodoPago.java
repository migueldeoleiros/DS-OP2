package tpv;

public interface MetodoPago {

    public default String pagar(Comanda c){return null;}
}
