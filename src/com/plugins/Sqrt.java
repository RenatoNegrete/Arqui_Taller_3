package com.plugins;
import com.core.Operacion;

public class Sqrt implements Operacion {
    public String getSymbol() { return "sqrt"; }
    public int getArity() { return 1; }
    public int getPrecedence() { return 5; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return true; }
    public double ejecutar(double... args) { return Math.sqrt(args[0]); }
}
