package com.core;

import com.plugins.*;

import java.util.*;

public class CoreSystem {
    private final Map<String, Operacion> operaciones = new HashMap<>();

    public CoreSystem() {
        // Operadores binarios
        registrarOperacion(new Suma());
        registrarOperacion(new Resta());
        registrarOperacion(new Multiplicacion());
        registrarOperacion(new Division());
        registrarOperacion(new Potencia());
        // Menos unario
        registrarOperacion(new Negacion());

        // Funciones
        registrarOperacion(new Sin());
        registrarOperacion(new Cos());
        registrarOperacion(new Tan());
        registrarOperacion(new Sqrt());
        registrarOperacion(new Log10());
        registrarOperacion(new Ln());
        registrarOperacion(new Exp());
        registrarOperacion(new Abs());

        alias("sen", "sin");
    }

    public void registrarOperacion(Operacion operacion) {
        operaciones.put(operacion.getSymbol(), operacion);
    }

    public boolean isRegistered(String nombre) {
        return operaciones.containsKey(nombre);
    }

    public Operacion getOperacion(String nombre) {
        Operacion op = operaciones.get(nombre);
        if (op == null) throw new IllegalArgumentException("Operación no soportada: " + nombre);
        return op;
    }

    public double ejecutarOperacion(String nombre, double... args) {
        return getOperacion(nombre).ejecutar(args);
    }

    public boolean isConstant(String token) {
        return token.equalsIgnoreCase("pi") || token.equals("π") || token.equalsIgnoreCase("e");
    }

    public double constantValue(String token) {
        switch (token.toLowerCase()) {
            case "pi":return Math.PI;
            case "π": return Math.PI;
            case "e": return Math.E;
            default: throw new IllegalArgumentException("Constante desconocida: " + token);
        }
    }

    private void alias(String alias, String original) {
        operaciones.put(alias, operaciones.get(original));
    }
}
