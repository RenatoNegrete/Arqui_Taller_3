package com.plugins;
import com.core.Operacion;

public class Negacion implements Operacion {
    public String getSymbol() { return "neg"; }
    public int getArity() { return 1; }
    public int getPrecedence() { return 3; }
    public Associativity getAssociativity() { return Associativity.RIGHT; }
    public boolean isFunction() { return false; }
    public double ejecutar(double... args) { return -args[0]; }
}
