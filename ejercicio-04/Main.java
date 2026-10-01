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

    public static void main(String[] args) {
        String expresion = "((a+b)-(c*d))/(e^f)";
        System.out.println("Expresion: " + expresion);
        System.out.println("Prefija: / - + a b * c d ^ e f");
        System.out.println("Postfija: " + postfija(expresion));
    }
}