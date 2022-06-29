package tpv;

import tpv.estadosComanda.Pedir;
import tpv.metodosPago.PagoEfectivo;
import tpv.productos.ProductoVenta;

import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private int mesa;
    private final Despensa despensa;
    private final List<ProductoVenta> pedidos = new ArrayList<>();
    private Estado estado = Pedir.getInstance();

    public Comanda(int mesa, Despensa despensa) {
        this.mesa = mesa;
        this.despensa = despensa;
    }

    public void setEstado(Estado estado){
        this.estado = estado;
    }

    public boolean pedir(ProductoVenta producto){
        return estado.pedir(this, producto);
    }

    public String solicitarCuenta(){
        return this.estado.solicitarCuenta(this);
    }

    public String pagar(MetodoPago metodoPago){
        this.estado.setMetodoPago(metodoPago);
        return this.estado.pagar(this, 0,0);
    }
    public String pagar(MetodoPago metodoPago, double descuento){
        this.estado.setMetodoPago(metodoPago);
        return this.estado.pagar(this, descuento, 0);
    }
    public String pagar(double efectivo){
        this.estado.setMetodoPago(new PagoEfectivo());
        return this.estado.pagar(this,0, efectivo);
    }
    public String pagar(double efectivo, double descuento){
        this.estado.setMetodoPago(new PagoEfectivo());
        return this.estado.pagar(this,descuento, efectivo);
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
    public List<ProductoVenta> getPedidos() {
        return pedidos;
    }
}
