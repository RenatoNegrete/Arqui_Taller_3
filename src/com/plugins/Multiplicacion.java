package com.plugins;
import com.core.Operacion;

public class Multiplicacion implements Operacion {
    public String getSymbol() { return "*"; }
    public int getArity() { return 2; }
    public int getPrecedence() { return 2; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return false; }
    public double ejecutar(double... args) { return args[0] * args[1]; }
}
