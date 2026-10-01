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
        Map<Character, Double> v = Map.of('a', 4.0, 'b', 5.0, 'c', 20.0, 'd', 10.0, 'e', 2.0, 'f', 6.0);
        return v.get(c);
    }

    static double evaluar(String post) {
        Stack<Double> p = new Stack<>();
        for (String t : post.split(" ")) {
            if (Character.isLetter(t.charAt(0))) p.push(valor(t.charAt(0)));
            else {
                double b = p.pop(), a = p.pop();
                if (t.equals("+")) p.push(a + b);
                else if (t.equals("-")) p.push(a - b);
                else if (t.equals("*")) p.push(a * b);
                else if (t.equals("/")) p.push(a / b);
                else if (t.equals("^")) p.push(Math.pow(a, b));
            }
        }
        return p.pop();
    }

    public static void main(String[] args) {
        String expresion = "(a*b)+(c/(d-e))-f";
        String post = postfija(expresion);
        System.out.println("Expresion: " + expresion);
        System.out.println("Postfija: " + post);
        System.out.println("Resultado: " + evaluar(post));
    }
}