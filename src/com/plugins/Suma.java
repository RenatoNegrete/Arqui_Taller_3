package com.plugins;

import com.core.Operacion;

public class Suma implements Operacion {
    @Override
    public double ejecutar(double... args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("La operación división requiere 2 argumentos");
        }
        return args[0] + args[1];
    }
}
