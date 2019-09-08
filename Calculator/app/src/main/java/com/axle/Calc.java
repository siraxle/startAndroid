package com.axle;
import java.util.*;

class Calc {
    //test case
    public static void main(String[] args) {
        String[] expr = new String[] {"9","+","2","*","3"};
        System.out.println(new Calc().run(expr));
    }

    Stack<Character> operators = new Stack<>();
    Stack<Double> numbers = new Stack<>();
    HashMap<Character,Integer> priorities = 
        new HashMap<Character,Integer>() {{
        put('+', 1); put('-',1); put('*', 2); put('/', 2);
    }};
    List<Character> operator_signs = Arrays.asList('*','/','+','-');

    public void reduce_stacks() {
        Double a = this.numbers.pop();
        Double b = this.numbers.pop();
        char op = this.operators.pop();
        switch (op) {
            case '+':
                this.numbers.push(a+b); break;
            case '-':
                this.numbers.push(b-a); break;
            case '*':
                this.numbers.push(a*b); break;
            case '/':
                this.numbers.push(b/a); break;
        }
    }

    public Double run(String[] expr) {
        for (int i=0; i<expr.length; i++) {
            String c = expr[i];
            char c0 = c.charAt(0);
            if (!this.operator_signs.contains(c0)) {
                this.numbers.push(Double.parseDouble(c));
            } else {
                while (!this.operators.empty() && 
                        this.priorities.get(c0)<
                        this.priorities.get(operators.peek())) {
                    this.reduce_stacks();
                }
                this.operators.push(c0);
            }
        }
        while (!this.operators.empty()) {
            this.reduce_stacks();
        }
        return this.numbers.pop();
    }
}
