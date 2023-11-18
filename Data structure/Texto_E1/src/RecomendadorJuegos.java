import java.util.Scanner;

class NodoArbol {
    String pregunta;
    NodoArbol si;
    NodoArbol no;

    NodoArbol(String pregunta) {
        this.pregunta = pregunta;
        this.si = null;
        this.no = null;
    }
}

public class RecomendadorJuegos {

    public static void main(String[] args) {
        // Construir el árbol de decisiones
        NodoArbol raiz = construirArbol();

        // Iniciar el juego recomendador
        recomendarJuego(raiz);
    }

    private static NodoArbol construirArbol() {
        NodoArbol raiz = new NodoArbol("¿Te gusta jugar al aire libre?");
        raiz.si = new NodoArbol("¿Prefieres juegos con pelotas?");
        raiz.no = new NodoArbol("¿Te gustan los juegos de mesa?");
        raiz.si.si = new NodoArbol("Fútbol");
        raiz.si.no = new NodoArbol("Baloncesto");
        raiz.no.si = new NodoArbol("¿Prefieres juegos estratégicos?");
        raiz.no.no = new NodoArbol("¿Te gustan los juegos de cartas?");
        raiz.no.si.si = new NodoArbol("Ajedrez");
        raiz.no.si.no = new NodoArbol("Damas");
        raiz.no.no.si = new NodoArbol("Póker");
        raiz.no.no.no = new NodoArbol("UNO");

        return raiz;
    }

    private static void recomendarJuego(NodoArbol nodo) {
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
