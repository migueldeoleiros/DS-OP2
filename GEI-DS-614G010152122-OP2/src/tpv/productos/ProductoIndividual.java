package tpv.productos;

public class ProductoIndividual extends ProductoVenta {
    public ProductoIndividual(String nombre, String descripcion, double precio, double cantidad) {
        super(nombre, descripcion, precio, cantidad);
    }
    public ProductoIndividual(String nombre, String descripcion, double precio, double cantidad, double impuestos) {
        super(nombre, descripcion, precio, cantidad, impuestos);
    }
}
