package com.plugins;
import com.core.Operacion;

public class Cos implements Operacion {
    public String getSymbol() { return "cos"; }
    public int getArity() { return 1; }
    public int getPrecedence() { return 5; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return true; }
    public double ejecutar(double... args) { return Math.cos(args[0]); }
}
