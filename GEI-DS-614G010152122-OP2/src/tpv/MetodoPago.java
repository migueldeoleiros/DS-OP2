package tpv;

public interface MetodoPago {
    /**
     * Devuelve un factura simplificada del pago de la comanda
     * @param c, comanda a pagar
     * @param descuento que se quiere aplicar
     * @param entregado, dinero entregado para pagar (en caso de efectivo)
     * @return factura
     */
    String pagar(Comanda c, double descuento, double entregado);
}
