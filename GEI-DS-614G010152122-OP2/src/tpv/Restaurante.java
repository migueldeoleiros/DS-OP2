package tpv;

import tpv.productos.ProductoVenta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurante {
    private Despensa despensa;
    private List<Comanda> comandas;

    public Restaurante(Despensa despensa) {
        this.despensa = despensa;
        this.comandas = new ArrayList<>();
    }

    public void addComanda(Comanda comanda) {
        this.comandas.add(comanda);
    }

    private int getCantidad(String producto){
        int cantidad=0;
        for (Comanda comanda : this.comandas) {
            for (ProductoVenta pedido : comanda.getPedidos()) {
                if(pedido.getNombre().equals(producto))
                    cantidad += pedido.getCantidad();
            }
        }
        return cantidad;
    }

    public String cerrarCaja() {
        StringBuilder output = new StringBuilder();
        float total=0;
        float impuestos=0;
        List<ProductoVenta> elementos = new ArrayList<>();

        output.append("# Cierre de caja").append("\n");
        output.append("# ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n");
        output.append("Producto            Cantidad\n");
        output.append("============================\n");
        for (Comanda comanda : this.comandas) {
            for (ProductoVenta pedido : comanda.getPedidos()) {
                if(!elementos.contains(pedido)) {
                    elementos.add(pedido);
                    output.append(String.format("%-20s%5d\n",
                            pedido.getNombre(),
                            getCantidad(pedido.getNombre())));
                }
                total += pedido.getPrecio();
                impuestos += pedido.getPrecio()*pedido.getImpuestos();
            }
        }

        output.append(String.format("\n%-10s%5.2f\n%-10s%5.2f\n%-10s%5.2f",
                "# Total ",total,
                "Impuestos ",impuestos,
                "Ingresos ",total-impuestos));

        return output.toString();
    }

    public Despensa getDespensa() {
        return despensa;
    }
    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }
    public List<Comanda> getComandas() {
        return comandas;
    }
    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
}
