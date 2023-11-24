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
        setFont(new Font("COCOGOOSE ", Font.BOLD, 15));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(516, 416);
        setLocationRelativeTo(null);

        raiz = construirArbol();
        nodoActual = raiz;

        preguntaPanel = new JPanel();
        recomendacionPanel = new JPanel();
        recomendacionPanel.setLayout(new BorderLayout());

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

        preguntaPanel.setLayout(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(158, 300, 200, 50);
        preguntaPanel.add(buttonPanel);
        buttonPanel.add(siButton);
        buttonPanel.add(noButton);

        preguntaLabel = new JLabel();
        preguntaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        preguntaLabel.setBounds(1, 260, 514, 35);
        preguntaLabel.setFont(new Font("COCOGOOSE ", Font.PLAIN, 14));
        preguntaPanel.add(preguntaLabel);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("/Users/alejandro/Documents/GitHub/U-Projects/Data structure/Game_selector/game.png"));
        lblNewLabel.setBounds(1, 28, 514, 208);
        preguntaPanel.add(lblNewLabel);

        getContentPane().add(recomendacionPanel, "recomendacion");

        mostrarSaludo();
    }

    private void mostrarSaludo() {
        int opcionSaludo = JOptionPane.showOptionDialog(this,
                "¡Bienvenido al Recomendador de Juegos!\n¿Quieres comenzar el test?",
                "Inicio del Test de Juegos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Comenzar", "Cancelar"}, "Comenzar");

        if (opcionSaludo == JOptionPane.YES_OPTION) {
            mostrarPregunta();
        } else {
            System.exit(0);
        }
    }

    private void mostrarPregunta() {
        if (nodoActual.si == null && nodoActual.no == null) {
            mostrarMensajeFinal();
        } else {
            preguntaLabel.setText(nodoActual.pregunta + " (S/N)");
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "pregunta");
        }
    }

    private void mostrarRecomendacion(String recomendacion) {
        JOptionPane.showMessageDialog(this, recomendacion, "Recomendación Final", JOptionPane.INFORMATION_MESSAGE);

        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres volver a hacer el test?", "Test de Juegos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Sí", "No"}, "Sí");

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarTest();
        } else {
            System.exit(0);
        }
    }

    private void reiniciarTest() {
        nodoActual = raiz;
        mostrarPregunta();
    }

    private void mostrarMensajeFinal() {
        JOptionPane.showMessageDialog(this, "Llegaste al final del test. ¡Gracias por participar!\n" +
                "Tu recomendación final es: " + nodoActual.pregunta, "Fin del Test", JOptionPane.INFORMATION_MESSAGE);

        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres volver a hacer el test?", "Test de Juegos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Sí", "No"}, "Sí");

        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarTest();
        } else {
            System.exit(0);
        }
    }

    private NodoArbol2 construirArbol() {
        NodoArbol2 raiz = new NodoArbol2("¿Prefieres juegos de acción?");
        raiz.si = new NodoArbol2("¿Te interesan los juegos de disparos?");
        raiz.no = new NodoArbol2("¿Te gustan los juegos de estrategia?");
        raiz.si.si = new NodoArbol2("Te interesan los juegos de guerra");
        raiz.si.no = new NodoArbol2("¿Prefieres juegos de aventuras?");
        raiz.no.si = new NodoArbol2("¿Te gustan los juegos de rol?");
        raiz.no.no = new NodoArbol2("¿Te interesan los juegos de simulación?");
        raiz.si.si.si = new NodoArbol2("Call of Duty");
        raiz.si.si.no = new NodoArbol2("Counter-Strike");
        raiz.si.no.si = new NodoArbol2("Assassin's Creed");
        raiz.si.no.no = new NodoArbol2("The Legend of Zelda");
        raiz.no.si.si = new NodoArbol2("Final Fantasy");
        raiz.no.si.no = new NodoArbol2("World of Warcraft");
        raiz.no.no.si = new NodoArbol2("The Sims");
        raiz.no.no.no = new NodoArbol2("¿Te gustan los juegos de mundo abierto?");
        raiz.no.no.no.si = new NodoArbol2("Minecraft");
        raiz.no.no.no.no = new NodoArbol2("¿Prefieres juegos de ciencia ficción?");
        raiz.no.no.no.no.si = new NodoArbol2("Starfield");
        raiz.no.no.no.no.no = new NodoArbol2("No tengo juegos para recomendar con las respuestas que me has dado.");
        
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
