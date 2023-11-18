package pk;

import java.util.Scanner;

class NodoArbol {
    String pregunta;
    NodoArbol2 si;
    NodoArbol2 no;

    NodoArbol(String pregunta) {
        this.pregunta = pregunta;
        this.si = null;
        this.no = null;
    }
}

public class RecomendadorJuegos {

    public static void main(String[] args) {
        // Construir el árbol de decisiones
        NodoArbol2 raiz = construirArbol();

        // Iniciar el juego recomendador
        recomendarJuego(raiz);
    }

    private static NodoArbol2 construirArbol() {
        NodoArbol2 raiz = new NodoArbol2("¿Te gusta jugar al aire libre?");
        raiz.si = new NodoArbol2("¿Prefieres juegos con pelotas?");
        raiz.no = new NodoArbol2("¿Te gustan los juegos de mesa?");
        raiz.si.si = new NodoArbol2("Fútbol");
        raiz.si.no = new NodoArbol2("Baloncesto");
        raiz.no.si = new NodoArbol2("¿Prefieres juegos estratégicos?");
        raiz.no.no = new NodoArbol2("¿Te gustan los juegos de cartas?");
        raiz.no.si.si = new NodoArbol2("Ajedrez");
        raiz.no.si.no = new NodoArbol2("Damas");
        raiz.no.no.si = new NodoArbol2("Póker");
        raiz.no.no.no = new NodoArbol2("UNO");

        return raiz;
    }

    private static void recomendarJuego(NodoArbol2 nodo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(nodo.pregunta + " (S/N)");
        String respuesta = scanner.nextLine().toUpperCase();

        if (respuesta.equals("S")) {
            if (nodo.si != null) {
                recomendarJuego(nodo.si);
            } else {
                System.out.println("Recomendación: " + nodo.pregunta);
            }
        } else if (respuesta.equals("N")) {
            if (nodo.no != null) {
                recomendarJuego(nodo.no);
            } else {
                System.out.println("Recomendación: " + nodo.pregunta);
            }
        } else {
            System.out.println("Respuesta no válida. Por favor, responde con S o N.");
            recomendarJuego(nodo);
        }
    }
}
