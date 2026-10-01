import java.util.*;

public class Main {
    static int prioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }

    static String postfija(String e) {
        StringBuilder s = new StringBuilder();
        Stack<Character> p = new Stack<>();
        for (char c : e.replace(" ", "").toCharArray()) {
            if (Character.isLetterOrDigit(c)) s.append(c).append(' ');
            else if (c == '(') p.push(c);
            else if (c == ')') {
                while (p.peek() != '(') s.append(p.pop()).append(' ');
                p.pop();
            } else {
                while (!p.isEmpty() && p.peek() != '(' && prioridad(p.peek()) >= prioridad(c))
                    s.append(p.pop()).append(' ');
                p.push(c);
            }
        }
        while (!p.isEmpty()) s.append(p.pop()).append(' ');
        return s.toString().trim();
    }

    static double valor(char c) {
        Map<Character, Double> v = Map.of('A', 6.0, 'B', 4.0, 'C', 8.0, 'D', 12.0, 'E', 3.0);
        return v.get(c);
    }

    static double evaluar(String post) {
        Stack<Double> p = new Stack<>();
        for (String t : post.split(" ")) {
            if (Character.isLetter(t.charAt(0))) p.push(valor(t.charAt(0)));
            else {
                double b = p.pop(), a = p.pop();
                switch (t) {
                    case "+": p.push(a + b); break;
                    case "-": p.push(a - b); break;
                    case "*": p.push(a * b); break;
                    case "/": p.push(a / b); break;
                    case "^": p.push(Math.pow(a, b)); break;
                }
            }
        }
        return p.pop();
    }

    public static void main(String[] args) {
        String expresion = "(A+B)*(C-D/E)";
        String post = postfija(expresion);
        System.out.println("Expresion: " + expresion);
        System.out.println("Postfija: " + post);
        System.out.println("Resultado: " + evaluar(post));
    }
}