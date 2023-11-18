package pk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class NodoArbol2 {
    String pregunta;
    NodoArbol2 si;
    NodoArbol2 no;

    NodoArbol2(String pregunta) {
        this.pregunta = pregunta;
        this.si = null;
        this.no = null;
    }
}

public class RecomendadorJuegos2 extends JFrame {

    private JPanel preguntaPanel;
    private JPanel recomendacionPanel;
    private JLabel preguntaLabel;
    private JTextArea recomendacionTextArea;
    private JButton siButton;
    private JButton noButton;

    private NodoArbol2 raiz;
    private NodoArbol2 nodoActual;

    public RecomendadorJuegos2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Recomendador de Juegos");
        setSize(400, 300);
        setLocationRelativeTo(null);

        raiz = construirArbol();
        nodoActual = raiz;

        preguntaPanel = new JPanel();
        recomendacionPanel = new JPanel();
        recomendacionPanel.setLayout(new BorderLayout());

        preguntaLabel = new JLabel();
        preguntaPanel.add(preguntaLabel);

        recomendacionTextArea = new JTextArea();
        recomendacionTextArea.setEditable(false);
        recomendacionPanel.add(recomendacionTextArea, BorderLayout.CENTER);

        CardLayout cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        getContentPane().add(preguntaPanel, "pregunta");
        
                siButton = new JButton("Sí");
                noButton = new JButton("No");
                
                        siButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (nodoActual.si != null) {
                                    nodoActual = nodoActual.si;
                                    mostrarPregunta();
                                } else {
                                    mostrarRecomendacion("Recomendación: " + nodoActual.pregunta);
                                }
                            }
                        });
                        
                                noButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (nodoActual.no != null) {
                                            nodoActual = nodoActual.no;
                                            mostrarPregunta();
                                        } else {
                                            mostrarRecomendacion("Recomendación: " + nodoActual.pregunta);
                                        }
                                    }
                                });
                                
                                        JPanel buttonPanel = new JPanel();
                                        preguntaPanel.add(buttonPanel);
                                        buttonPanel.add(siButton);
                                        buttonPanel.add(noButton);
        getContentPane().add(recomendacionPanel, "recomendacion");

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        preguntaLabel.setText(nodoActual.pregunta + " (S/N)");
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "pregunta");
    }

    private void mostrarRecomendacion(String recomendacion) {
        recomendacionTextArea.setText(recomendacion);
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "recomendacion");
    }

    private NodoArbol2 construirArbol() {
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RecomendadorJuegos2 frame = new RecomendadorJuegos2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
