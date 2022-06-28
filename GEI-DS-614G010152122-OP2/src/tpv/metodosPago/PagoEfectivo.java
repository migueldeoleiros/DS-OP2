package tpv.metodosPago;

import tpv.Comanda;
import tpv.MetodoPago;
import tpv.estadosComanda.Cobro;
import tpv.productos.ProductoVenta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PagoEfectivo implements MetodoPago {
    @Override
    public String pagar(Comanda c) {
        return null;
    }

    @Override
    public String pagar(Comanda c, double descuento) {
        StringBuilder output = new StringBuilder();
        float total=0;
        float totalNoImpuestos=0;

        output.append("# Factura simplificada numero ").append(c.hashCode()).append("\n");
        output.append("# Mesa numero ").append(c.getMesa()).append("\n");
        output.append("# ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n");
        output.append("Producto\t Cantidad \t Precio \t PVP unidad \t PVP total\n");
        output.append("==============================================================\n");
        for(ProductoVenta i : c.getPedidos()){
            output.append(i.toString()).append("\n");
            totalNoImpuestos += i.getPrecio();
            total += (i.getPrecio()+i.getPrecio()*i.getImpuestos());
        }
        output.append("\n# Total\n");
        output.append("Total sin impuestos ").append(String.format("%.2f",totalNoImpuestos)).append("\n");
        output.append("Total de impuestos ").append(String.format("%.2f",total-totalNoImpuestos)).append("\n");
        output.append("PVP impuestos ").append(String.format("%.2f",total)).append("\n");

        output.append("\n# Forma de pago:\n");
        output.append("Descuento del ").append(String.format("%.2f%%",descuento*100)).append("\n");
        output.append("Descuento ").append(String.format("%.2f",total*descuento)).append("\n");
        output.append("Total ").append(String.format("%.2f",total - total*descuento));
        //TODO Entrega y devolucion

        return output.toString();
    }
}
