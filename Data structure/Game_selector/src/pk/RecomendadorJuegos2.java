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

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        if (nodoActual.si == null && nodoActual.no == null) {
            // Llegó al final del árbol, mostrar mensaje y preguntar si desea reiniciar
            mostrarMensajeFinal();
        } else {
            preguntaLabel.setText(nodoActual.pregunta + " (S/N)");
            CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
            cardLayout.show(getContentPane(), "pregunta");
        }
    }

    private void mostrarRecomendacion(String recomendacion) {
        // Mostrar ventana emergente con la recomendación final
        JOptionPane.showMessageDialog(this, recomendacion, "Recomendación Final", JOptionPane.INFORMATION_MESSAGE);

        // Preguntar al usuario si quiere volver a hacer el test
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres volver a hacer el test?", "Test de Juegos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Sí", "No"}, "Sí");

        if (opcion == JOptionPane.YES_OPTION) {
            // Reiniciar el test
            reiniciarTest();
        } else {
            // Cerrar la aplicación
            System.exit(0);
        }
    }

    private void reiniciarTest() {
        // Reiniciar el test según sea necesario
        // Aquí puedes reiniciar las variables y nodos necesarios
        // Por ejemplo, podrías reiniciar nodoActual a la raíz y llamar a mostrarPregunta() nuevamente.
        nodoActual = raiz;
        mostrarPregunta();
    }

    private void mostrarMensajeFinal() {
        // Mensaje final al llegar al final del árbol con la recomendación final
        JOptionPane.showMessageDialog(this, "Llegaste al final del test. ¡Gracias por participar!\n" +
                "Tu recomendación final es: " + nodoActual.pregunta, "Fin del Test", JOptionPane.INFORMATION_MESSAGE);

        // Preguntar al usuario si quiere volver a hacer el test
        int opcion = JOptionPane.showOptionDialog(this, "¿Quieres volver a hacer el test?", "Test de Juegos",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Sí", "No"}, "Sí");

        if (opcion == JOptionPane.YES_OPTION) {
            // Reiniciar el test
            reiniciarTest();
        } else {
            // Cerrar la aplicación
            System.exit(0);
        }
    }

    private NodoArbol2 construirArbol() {
        NodoArbol2 raiz = new NodoArbol2("¿Te gusta jugar videojuegos?");
        raiz.si = new NodoArbol2("¿Prefieres juegos de acción?");
        raiz.no = new NodoArbol2("¿Te gustan los juegos de estrategia?");
        raiz.si.si = new NodoArbol2("¿Te interesan los juegos de disparos?");
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
        raiz.no.no.no = new NodoArbol2("Stardew Valley");
        raiz.si.no.si.si = new NodoArbol2("¿Te gustan los juegos de mundo abierto?");
        raiz.si.no.si.no = new NodoArbol2("¿Prefieres juegos de ciencia ficción?");
        raiz.si.no.no = new NodoArbol2("¿Te interesan los juegos de puzzles?");
        raiz.si.no.no.si = new NodoArbol2("Portal");
        raiz.si.no.no.no = new NodoArbol2("Tetris");
        raiz.no.si.no = new NodoArbol2("¿Te interesan los juegos de gestión?");
        raiz.no.si.no.si = new NodoArbol2("¿Prefieres juegos de construcción?");
        raiz.no.si.no.no = new NodoArbol2("¿Te gustan los juegos de estrategia por turnos?");
        raiz.no.si.no.si.si = new NodoArbol2("SimCity");
        raiz.no.si.no.si.no = new NodoArbol2("Minecraft");
        raiz.no.si.no.no.si = new NodoArbol2("Civilization");
        raiz.si.no.si.si.no = new NodoArbol2("¿Te gustan los juegos de mundo abierto con elementos de ciencia ficción?");
        raiz.si.no.si.no.si = new NodoArbol2("¿Prefieres juegos de aventuras con elementos de fantasía?");
        raiz.si.no.si.no.no = new NodoArbol2("¿Te interesan los juegos que requieren estrategia táctica?");
        raiz.si.no.no.si.si = new NodoArbol2("¿Te gustan los juegos de aventuras gráficas?");
        raiz.si.no.no.si.no = new NodoArbol2("¿Prefieres juegos de rompecabezas más desafiantes?");
        raiz.no.si.no.si.si.no = new NodoArbol2("¿Te gustan los juegos de construcción con enfoque en la gestión económica?");
        raiz.no.si.no.si.no.si = new NodoArbol2("¿Prefieres juegos de construcción con temática histórica?");
        raiz.no.si.no.no.si = new NodoArbol2("¿Te interesan los juegos de estrategia por turnos más complejos?");
        raiz.no.si.no.no.no = new NodoArbol2("¿Prefieres juegos de construcción con enfoque en la creatividad libre?");

        //Siendo excentricos 
        raiz.si.no.si.si.si = new NodoArbol2("Battlefield");
        raiz.si.no.si.no.si = new NodoArbol2("Uncharted");
        raiz.si.no.no.no.no = new NodoArbol2("Farming Simulator");
        raiz.no.si.no.si.no.no = new NodoArbol2("Anno 2205");
        raiz.no.si.no.no.si.no.no = new NodoArbol2("Age of Mythology");
        raiz.no.si.no.no.no.si.no = new NodoArbol2("Factorio");
        raiz.no.si.no.no.no.no.si = new NodoArbol2("Minecraft: Dungeons");
        raiz.no.si.no.no.no.no.no = new NodoArbol2("Cities: Skylines - Green Cities");
        raiz.si.no.no.si.si.no.si = new NodoArbol2("Limbo");
        raiz.si.no.no.si.no.si.no = new NodoArbol2("Inside");
        raiz.si.no.no.no.si.si.no = new NodoArbol2("The Witness");
        raiz.si.no.no.no.no.no.si = new NodoArbol2("Subnautica");
        raiz.si.no.no.no.no.no.no = new NodoArbol2("Factorio");
        raiz.no.si.no.no.si.si.si = new NodoArbol2("StarCraft II");
        raiz.no.si.no.no.si.si.no = new NodoArbol2("Age of Empires II: Definitive Edition");
        raiz.no.si.no.no.no.si.si = new NodoArbol2("Europa Universalis IV");
        raiz.no.si.no.no.no.si.no = new NodoArbol2("Stellaris");
        raiz.no.si.no.no.no.no.si = new NodoArbol2("The Sims 4");
        raiz.no.si.no.no.no.no.no = new NodoArbol2("Stardew Valley");
        
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
