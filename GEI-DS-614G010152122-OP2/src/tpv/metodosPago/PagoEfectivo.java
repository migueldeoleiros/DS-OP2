package tpv.metodosPago;

import tpv.Comanda;
import tpv.MetodoPago;
import tpv.productos.ProductoVenta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PagoEfectivo implements MetodoPago {
    @Override
    public String pagar(Comanda c) {
        return null;
    }

    public String pagar(Comanda c, double descuento) {
        StringBuilder output = new StringBuilder();
        float total=0;
        float totalNoImpuestos=0;

        output.append("# Factura simplificada numero ").append(c.hashCode()).append("\n");
        output.append("# Mesa numero ").append(c.getMesa()).append("\n");
        output.append("# ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n");
        output.append("Producto\t Cantidad \t Precio \t PVP unidad \t PVP total \n");
        output.append("===========================================================");
        for(ProductoVenta i : c.getPedidos()){
            output.append(i.toString()).append("\n");
            totalNoImpuestos += i.getPrecio();
            total += (i.getPrecio()+i.getPrecio()*i.getImpuestos());
        }
        output.append("# Total\n");
        output.append("Total sin impuestos ").append(totalNoImpuestos).append("\n");
        output.append("Total de impuestos ").append(total-totalNoImpuestos).append("\n");
        output.append("PVP impuestos ").append(total).append("\n");

        return output.toString();
    }
}
