package com.pipeline;

import com.core.*;
import java.util.*;

public class Evaluator {
    private final CoreSystem core;

    public Evaluator(CoreSystem core) { this.core = core; }

    public List<String> toRPN(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        String prevType = null;

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token); prevType = "number"; continue;
            }
            if (core.isConstant(token)) {
                output.add(Double.toString(core.constantValue(token))); prevType = "number"; continue;
            }
            if (token.equals("(")) { stack.push(token); prevType = "("; continue; }
            if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) output.add(stack.pop());
                stack.pop();
                if (!stack.isEmpty() && core.getOperacion(stack.peek()).isFunction()) output.add(stack.pop());
                prevType = ")"; continue;
            }

            // operador o función
            String opToken = token;
            if (token.equals("-") && (prevType == null || prevType.equals("operator") || prevType.equals("("))) {
                opToken = "neg";
            }

            if (!core.isRegistered(opToken)) throw new IllegalArgumentException("Operación desconocida: " + token);

            Operacion op1 = core.getOperacion(opToken);
            if (op1.isFunction()) { stack.push(opToken); prevType = "func"; continue; }

            while (!stack.isEmpty() && core.isRegistered(stack.peek())) {
                Operacion op2 = core.getOperacion(stack.peek());
                boolean left = op1.getAssociativity() == Operacion.Associativity.LEFT;
                if ((left && op1.getPrecedence() <= op2.getPrecedence()) ||
                    (!left && op1.getPrecedence() < op2.getPrecedence())) {
                    output.add(stack.pop());
                } else break;
            }
            stack.push(opToken);
            prevType = "operator";
        }
        while (!stack.isEmpty()) output.add(stack.pop());
        return output;
    }

    public double evaluateRPN(List<String> tokens) {
        Deque<Double> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (isNumber(token)) { stack.push(Double.parseDouble(token)); continue; }
            if (!core.isRegistered(token)) throw new IllegalArgumentException("Token desconocido: " + token);

            Operacion op = core.getOperacion(token);
            int n = op.getArity();
            double[] args = new double[n];
            for (int i = n - 1; i >= 0; i--) args[i] = stack.pop();
            stack.push(op.ejecutar(args));
        }
        return stack.pop();
    }

    private boolean isNumber(String s) {
        try { Double.parseDouble(s); return true; } catch (Exception e) { return false; }
    }
}
