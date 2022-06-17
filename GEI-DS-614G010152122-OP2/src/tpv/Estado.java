package tpv;

public interface Estado {

    default String pedir(Comanda c, Producto pedido, int cantidad) {
        return null;
    }
    default String solicitarCuenta(Comanda c){
        return null;
    }
    default String pagar(Comanda c){
        return null;
    }
}
