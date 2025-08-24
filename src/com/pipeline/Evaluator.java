package com.pipeline;

import com.core.CoreSystem;
import java.util.*;

public class Evaluator {
    private CoreSystem core;

    public Evaluator(CoreSystem core) {
        this.core = core;
    }

    // Algoritmo Shunting-yard: convierte de infix -> postfix (RPN)
    public List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                // es número
                output.add(token);
            } else if (precedence.containsKey(token)) {
                // es operador
                while (!operators.isEmpty() && precedence.containsKey(operators.peek()) &&
                        precedence.get(operators.peek()) >= precedence.get(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop(); // descartar paréntesis
                }
            }
        }

        // Vaciar operadores restantes
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }

        return output;
    }

    // Evalúa una expresión en RPN
    public double evaluateRPN(List<String> tokens) {
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                stack.push(core.ejecutarOperacion(token, a, b));
            }
        }
        return stack.pop();
    }
}
