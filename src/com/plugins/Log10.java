package com.plugins;
import com.core.Operacion;

public class Log10 implements Operacion {
    public String getSymbol() { return "log"; }
    public int getArity() { return 1; }
    public int getPrecedence() { return 5; }
    public Associativity getAssociativity() { return Associativity.LEFT; }
    public boolean isFunction() { return true; }
    public double ejecutar(double... args) { return Math.log10(args[0]); }
}
