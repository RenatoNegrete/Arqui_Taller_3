package com.plugins;
import com.core.Operacion;

public class Potencia implements Operacion {
    public String getSymbol() { return "^"; }
    public int getArity() { return 2; }
    public int getPrecedence() { return 4; }
    public Associativity getAssociativity() { return Associativity.RIGHT; }
    public boolean isFunction() { return false; }
    public double ejecutar(double... args) { return Math.pow(args[0], args[1]); }
}
