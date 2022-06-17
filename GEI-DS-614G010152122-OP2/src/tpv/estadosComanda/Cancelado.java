package tpv.estadosComanda;

import tpv.Estado;

public class Cancelado implements Estado {
    private static final Cancelado instancia = new Cancelado();
    private Cancelado(){}
    public static Cancelado getInstance() {return instancia;}

}
