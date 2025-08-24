package com.core;

import com.plugins.Division;
import com.plugins.Multiplicacion;
import com.plugins.Resta;
import com.plugins.Suma;

import java.util.HashMap;
import java.util.Map;

public class CoreSystem {
    private Map<String, Operacion> operaciones = new HashMap<>();

    public CoreSystem() {
        this.registrarOperacion("+", new Suma());
        this.registrarOperacion("-", new Resta());
        this.registrarOperacion("*", new Multiplicacion());
        this.registrarOperacion("/", new Division());
    }

    public void registrarOperacion(String nombre, Operacion operacion) {
        operaciones.put(nombre, operacion);
    }

    public double ejecutarOperacion(String nombre, double... args) {
        if (!operaciones.containsKey(nombre)) {
            throw new IllegalArgumentException("Operación no soportada: " + nombre);
        }
        return operaciones.get(nombre).ejecutar(args);
    }
}
