package tpv;

import java.util.ArrayList;
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

    public String cerrarCaja() {
        return null;
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
