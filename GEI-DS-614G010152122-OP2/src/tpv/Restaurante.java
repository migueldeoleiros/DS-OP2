package tpv;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    Despensa despensa;
    List<Comanda> comandas;

    public Restaurante(Despensa despensa) {
        this.despensa = despensa;
        this.comandas = new ArrayList<>();
    }

    public void addComanda(Comanda comanda) {
        this.comandas.add(comanda);
    }

    public String cerrarCaja() {
        return null;
    }
}
