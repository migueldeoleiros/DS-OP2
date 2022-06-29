package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.MetodoPago;
import tpv.productos.ProductoVenta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Impagado implements Estado {
    private static final Impagado instancia = new Impagado();
    private Impagado(){}
    public static Impagado getInstance() {return instancia;}

    private MetodoPago metodoPago;

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String solicitarCuenta(Comanda c) {
        StringBuilder output = new StringBuilder();
        float total=0;
        float totalNoImpuestos=0;

        output.append("# Mesa numero ").append(c.getMesa()).append("\n");
        output.append("# ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n");
        output.append("Producto\t Cantidad \t Precio \t PVP unidad \t PVP total\n");
        output.append("==============================================================\n");
        for(ProductoVenta i : c.getPedidos()){
            output.append(i.toString()).append("\n");
            totalNoImpuestos += i.getPrecio();
            total += (i.getPrecio()+i.getPrecio()*i.getImpuestos());
        }
        output.append("\n# Pendiente de combro\n");
        output.append("Total sin impuestos ").append(String.format("%.2f",totalNoImpuestos)).append("\n");
        output.append("Total de impuestos ").append(String.format("%.2f",total-totalNoImpuestos)).append("\n");
        output.append("PVP total ").append(String.format("%.2f",total));

        c.setEstado(Cobro.getInstance());
        return output.toString();
    }

    @Override
    public String pagar(Comanda c, double descuento, double entregado) {
        c.setEstado(Cerrado.getInstance());
        return metodoPago.pagar(c, descuento, entregado);
    }
}
