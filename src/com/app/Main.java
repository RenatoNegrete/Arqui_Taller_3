package com.app;

import com.core.CoreSystem;

public class Main {
    public static void main(String[] args) {
        CoreSystem core = new CoreSystem();
        System.out.println("Resultado suma: " + core.ejecutarOperacion("+", 5, 3));
        System.out.println("Resultado multiplicación: " + core.ejecutarOperacion("*", 4, 2));
    }
}
