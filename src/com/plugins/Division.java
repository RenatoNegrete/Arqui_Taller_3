package com.plugins;

import com.core.Operacion;

public class Division implements Operacion {
    @Override
    public double ejecutar(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("La operación división requiere 2 argumentos");
        }
        if (args[1] == 0) {
            throw new ArithmeticException("No se puede hacer división entre 0");
        }
        return args[0] / args[1];
    }
}
