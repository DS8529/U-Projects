package text_editor_e1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.undo.UndoManager;

public class TextEditor2 extends JFrame {

    private File file;
    private FileInputStream inputStream;
    private FileOutputStream outputStream;

    private List<String> textHistory = new ArrayList<>();
    private int currentIndex = -1;

    private JButton openButton;
    private JButton saveButton;
    private JButton redoButton;
    private JButton undoButton;
    private JPanel mainPanel;
    private JTextArea textArea;

    public TextEditor2() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Editor de Texto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(477, 600);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        openButton = new JButton("Abrir Documento");
        saveButton = new JButton("Guardar Documento");
        undoButton = new JButton("Deshacer");
        redoButton = new JButton("Rehacer");

        textArea = new JTextArea(20, 20);
        JScrollPane scrollPane = new JScrollPane(textArea);

        openButton.addActionListener(e -> openButtonActionPerformed(e));
        saveButton.addActionListener(e -> saveButtonActionPerformed(e));
        undoButton.addActionListener(e -> undoButtonActionPerformed(e));
        redoButton.addActionListener(e -> redoButtonActionPerformed(e));

        mainPanel.add(openButton);
        mainPanel.add(saveButton);
        mainPanel.add(scrollPane);
        mainPanel.add(undoButton);
        mainPanel.add(redoButton);

        getContentPane().add(mainPanel);
    }

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            if (file.canRead()) {
                if (file.getName().endsWith("txt")) {
                    String document = openFile(file);
                    textArea.setText(document);
                    addToHistory(document);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible");
                }
            }
        }
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            if (file.getName().endsWith("txt")) {
                String document = textArea.getText();
                String message = saveFile(file, document);
                if (message != null) {
                    JOptionPane.showMessageDialog(null, message);
                    addToHistory(document);
                } else {
                    JOptionPane.showMessageDialog(null, "Archivo no compatible");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Guardar Documento de Texto");
            }
        }
    }

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentIndex > 0) {
            currentIndex--;
            String previousText = textHistory.get(currentIndex);
            textArea.setText(previousText);
        }
    }

    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentIndex < textHistory.size() - 1) {
            currentIndex++;
            String nextText = textHistory.get(currentIndex);
            textArea.setText(nextText);
        }
    }

    public String openFile(File file) {
        String document = "";
        try {
            inputStream = new FileInputStream(file);
            int ascii;
            while ((ascii = inputStream.read()) != -1) {
                char character = (char) ascii;
                document += character;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public String saveFile(File file, String document) {
        String message = null;
        try {
            outputStream = new FileOutputStream(file);
            byte[] bytes = document.getBytes();
            outputStream.write(bytes);
            message = "Archivo Guardado";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private void addToHistory(String text) {
        if (currentIndex < textHistory.size() - 1) {
            // Remove all entries after the current index if branching from an undo point
            textHistory.subList(currentIndex + 1, textHistory.size()).clear();
        }
        textHistory.add(text);
        currentIndex++;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextEditor().setVisible(true);
            }
        });
    }
}
