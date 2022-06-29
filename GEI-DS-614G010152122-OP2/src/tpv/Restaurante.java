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

    /**
     * Introduce una nueva comanda a la lista de comandas
     * @param comanda que introducir en la lista
     */
    public void addComanda(Comanda comanda) {
        this.comandas.add(comanda);
    }

    /**
     * Cuenta cuantas veces se ha pedido un producto entre todas las comandas
     * @param producto a contar
     * @return cantidad de producto
     */
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

    /**
     * Borra de lista de comandas y retorna un informe de los productos
     * y ganancias desde el ultimo cierre
     * @return informe
     */
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

        this.comandas = new ArrayList<>();
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
}
