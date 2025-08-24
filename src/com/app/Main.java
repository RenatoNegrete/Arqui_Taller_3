package com.app;

import com.core.CoreSystem;
import com.pipeline.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String expresion = "5 + 3 * 10";

        // Construir pipeline
        Pipeline<String, List<String>> pipeline = new Pipeline<String, List<String>>()
                .add(new NormalizeFilter())
                .add(new TokenizeFilter());

        List<String> tokens = pipeline.run(expresion);
        System.out.println("Tokens (infix): " + tokens);

        // Microkernel
        CoreSystem core = new CoreSystem();
        Evaluator evaluator = new Evaluator(core);

        // Convertir a RPN
        List<String> rpn = evaluator.toRPN(tokens);
        System.out.println("Tokens (RPN): " + rpn);

        // Evaluar
        double result = evaluator.evaluateRPN(rpn);
        System.out.println("Resultado: " + result);
    }
}
