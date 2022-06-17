package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.Producto;

public class Pedir implements Estado {
    private static final Pedir instancia = new Pedir();
    private Pedir(){}
    public static Pedir getInstance() {return instancia;}

    @Override
    public String pedir(Comanda c, Producto pedido, int cantidad) {
        if(c.getDespensa().removeProducto(pedido, cantidad))
            c.getPedidos().put(pedido, cantidad);
        return null;
    }

    @Override
    public String solicitarCuenta(Comanda c) {
        c.setEstado(Cobro.getInstance());
        return null;
    }
}
