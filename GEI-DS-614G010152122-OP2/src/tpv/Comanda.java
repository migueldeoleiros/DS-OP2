package tpv;

import tpv.estadosComanda.Pedir;
import tpv.productos.ProductoVenta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Comanda {
    private int mesa;
    private Despensa despensa;
    private List<ProductoVenta> pedidos = new ArrayList<>();
    private Estado estado = Pedir.getInstance();

    public Comanda(int mesa, Despensa despensa) {
        this.mesa = mesa;
        this.despensa = despensa;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public void pedir(ProductoVenta producto, int cantidad){
        estado.pedir(this, producto);
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
    public List<ProductoVenta> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<ProductoVenta> pedidos) {
        this.pedidos = pedidos;
    }
}
