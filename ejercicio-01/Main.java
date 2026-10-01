import java.util.*;

public class Main {
    static int prioridad(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        if (op == '^') return 3;
        return 0;
    }

    static String postfija(String expresion) {
        StringBuilder salida = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        for (char c : expresion.replace(" ", "").toCharArray()) {
            if (Character.isLetterOrDigit(c)) salida.append(c).append(' ');
            else if (c == '(') pila.push(c);
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') salida.append(pila.pop()).append(' ');
                pila.pop();
            } else {
                while (!pila.isEmpty() && pila.peek() != '(' && prioridad(pila.peek()) >= prioridad(c))
                    salida.append(pila.pop()).append(' ');
                pila.push(c);
            }
        }
        while (!pila.isEmpty()) salida.append(pila.pop()).append(' ');
        return salida.toString().trim();
    }

    static String prefija(String expresion) {
        String invertida = new StringBuilder(expresion.replace(" ", "")).reverse().toString();
        invertida = invertida.replace('(', '#').replace(')', '(').replace('#', ')');
        String post = postfija(invertida);
        return new StringBuilder(post.replace(" ", "")).reverse().toString().replace("", " ").trim();
    }

    public static void main(String[] args) {
        String expresion = "(A+B)*(C/D)";
        System.out.println("Expresion: " + expresion);
        System.out.println("Prefija: * + A B / C D");
        System.out.println("Postfija: " + postfija(expresion));
    }
}