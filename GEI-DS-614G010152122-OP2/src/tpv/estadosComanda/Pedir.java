package tpv.estadosComanda;

import tpv.Comanda;
import tpv.Estado;
import tpv.productos.ProductoVenta;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedir implements Estado {
    private static final Pedir instancia = new Pedir();
    private Pedir(){}
    public static Pedir getInstance() {return instancia;}

    @Override
    public boolean pedir(Comanda c, ProductoVenta pedido) {
        if(c.getDespensa().decrementarProducto(pedido, pedido.getCantidad())){
            c.getPedidos().add(pedido);
            return true;
        }
        return false;
    }

    @Override
    public String solicitarCuenta(Comanda c) {
        StringBuilder output = new StringBuilder();
        float total=0;
        float totalNoImpuestos=0;

        output.append("# Mesa numero ").append(c.getMesa()).append("\n");
        output.append("# ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).append("\n");
        output.append("Producto\t Cantidad \t Precio \t PVP unidad \t PVP total \n");
        output.append("===========================================================");
        for(ProductoVenta i : c.getPedidos()){
            output.append(i.toString()).append("\n");
            totalNoImpuestos += i.getPrecio();
            total += (i.getPrecio()+i.getPrecio()*i.getImpuestos());
        }
        output.append("# Pendiente de combro\n");
        output.append("Total sin impuestos ").append(totalNoImpuestos).append("\n");
        output.append("Total de impuestos ").append(total-totalNoImpuestos).append("\n");
        output.append("PVP impuestos ").append(total).append("\n");

        c.setEstado(Cobro.getInstance());
        return output.toString();
    }
}
