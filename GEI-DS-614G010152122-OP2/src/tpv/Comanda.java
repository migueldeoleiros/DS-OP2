package tpv;

import tpv.estadosComanda.Pedir;

import java.util.HashMap;

public class Comanda {
    private int mesa;
    private Despensa despensa;
    private HashMap<Producto, Integer> pedidos = new HashMap<>();
    private Estado estado = Pedir.getInstance();

    public Comanda(int mesa, Despensa despensa) {
        this.mesa = mesa;
        this.despensa = despensa;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public void pedir(Producto producto, int cantidad){
        estado.pedir(this, producto, cantidad);
    }

    public String solicitarCuenta(){
        return this.estado.solicitarCuenta(this);
    }
    public String pagar(){
        return this.estado.pagar(this);
    }

    //getters y setters
    public int getMesa() {
        return mesa;
    }
    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
    public Despensa getDespensa() {
        return despensa;
    }
    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }
    public HashMap<Producto, Integer> getPedidos() {
        return pedidos;
    }
    public void setPedidos(HashMap<Producto, Integer> pedidos) {
        this.pedidos = pedidos;
    }
}
