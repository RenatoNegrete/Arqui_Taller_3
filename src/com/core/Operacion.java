package com.core;

public interface Operacion {
    enum Associativity { LEFT, RIGHT }

    String getSymbol();
    int getArity();
    int getPrecedence();
    Associativity getAssociativity();
    boolean isFunction();

    double ejecutar(double... args);
}
