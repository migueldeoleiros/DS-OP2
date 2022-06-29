package tpv.estadosComanda;

import tpv.Estado;

public class Cerrado implements Estado {
    private static final Cerrado instancia = new Cerrado();
    private Cerrado(){}
    public static Cerrado getInstance() {return instancia;}
}
