package com.app;

import com.core.CoreSystem;
import com.pipeline.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String expresion = "sin(pi/2) + sqrt(9) * 2 - 3^2 + -4";

        Pipeline<String, List<String>> pipeline = new Pipeline<String, List<String>>()
                .add(new NormalizeFilter())
                .add(new TokenizeFilter());

        List<String> tokens = pipeline.run(expresion);
        System.out.println("Tokens (infix): " + tokens);

        CoreSystem core = new CoreSystem();
        Evaluator evaluator = new Evaluator(core);

        List<String> rpn = evaluator.toRPN(tokens);
        System.out.println("Tokens (RPN): " + rpn);

        double result = evaluator.evaluateRPN(rpn);
        System.out.println("Resultado: " + result);

        // DEMOS
        demo(evaluator, pipeline, "5 + 3 * 10");
        demo(evaluator, pipeline, "-(2 + 3) * 4");
        demo(evaluator, pipeline, "2^3^2");
        demo(evaluator, pipeline, "sin(pi/6) + cos(pi/3)");
        demo(evaluator, pipeline, "ln(e)");
        demo(evaluator, pipeline, "log(1000)");
        demo(evaluator, pipeline, "abs(-5) + exp(0)");
    }

    private static void demo(Evaluator evaluator, Pipeline<String, List<String>> pipeline, String expr) {
        List<String> t = pipeline.run(expr);
        List<String> rpn = evaluator.toRPN(t);
        double val = evaluator.evaluateRPN(rpn);
        System.out.println("→ " + expr + " = " + val);
    }
}
