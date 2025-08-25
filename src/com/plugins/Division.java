package com.plugins;
import com.core.Operacion;

public class Division implements Operacion {
    public String getSymbol() { return "/"; }
    public int getArity() { return 2; }
    public int getPrecedence() { return 2; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return false; }
    public double ejecutar(double... args) {
        if (args[1] == 0) throw new ArithmeticException("No se puede dividir entre 0");
        return args[0] / args[1];
    }
}
