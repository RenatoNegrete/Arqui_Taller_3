package com.plugins;
import com.core.Operacion;

public class Tan implements Operacion {
    public String getSymbol() { return "tan"; }
    public int getArity() { return 1; }
    public int getPrecedence() { return 5; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return true; }
    public double ejecutar(double... args) { return Math.tan(args[0]); }
}
