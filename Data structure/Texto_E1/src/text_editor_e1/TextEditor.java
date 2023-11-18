	package text_editor_e1;
	
	import java.io.*;
	import java.util.Deque;
	import java.util.LinkedList;
	import java.util.Queue;
	import javax.swing.*;
	import javax.swing.undo.UndoManager;
	
	public class TextEditor extends JFrame {
	
		// Límites
	    private static final int UNDO_LIMIT = 5; 
	    private static final int REDO_LIMIT = 5; 
	
	    private File file; // Archivo con el que se está trabajando
	    private FileInputStream inputStream; // Flujo de entrada para leer archivos
	    private FileOutputStream outputStream; // Flujo de salida para guardar archivos
	
	    // Pilas y colas - hacer y deshacer
	    
	    private Deque<String> undoStack = new LinkedList<>(); 
	    private Deque<String> redoStack = new LinkedList<>(); 
	    private Queue<String> undoQueue = new LinkedList<>(); 
	    private Queue<String> redoQueue = new LinkedList<>();	
	    
	    // Frame
	    private JButton openButton; 
	    private JButton saveButton; 
	    private JButton redoButton; 
	    private JButton undoButton; 
	    private JPanel mainPanel; // Panel principal
	    private JTextArea textArea; 
	
	    public TextEditor() {
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
	    
	 // Botón abrir
	
	    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        redoQueue.clear();
	
	        JFileChooser chooser = new JFileChooser(); 
	        if (chooser.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION) {
	            file = chooser.getSelectedFile(); 
	            if (file.canRead()) { // 
	                if (file.getName().endsWith("txt")) { 
	                    String document = openFile(file); 
	                    textArea.setText(document); 
	                    undoStack.push(document); 
	                } else {
	                    JOptionPane.showMessageDialog(null, "Archivo no compatible"); 
	                }
	            }
	        }
	    }
	
	 // Botón guardar
	    
	    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        JFileChooser chooser = new JFileChooser(); 
	        if (chooser.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
	            file = chooser.getSelectedFile(); 
	            if (file.getName().endsWith("txt")) { 
	                String document = textArea.getText(); 
	                String message = saveFile(file, document); 
	                if (message != null) {
	                    JOptionPane.showMessageDialog(null, message); 
	                    undoStack.push(document); // Guarda el estado actual del texto en la pila de deshacer
	                } else {
	                    JOptionPane.showMessageDialog(null, "Archivo no compatible"); 
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Guardar Documento de Texto"); 
	            }
	        }
	
	       redoQueue.clear(); // Vaciar la cola de rehacer después de guardar
	    }
	
	    // Botón Deshacer
	    
	    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        try {
	            if (!undoStack.isEmpty()) {
	                redoQueue.offer(textArea.getText()); // Guardar el estado actual del texto en la cola de rehacer
	                textArea.setText(undoStack.pop()); // Obtener el último estado del texto de la pila de deshacer y establecerlo
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	    // Botón Rehacer
	    
	    private void redoButtonActionPerformed(java.awt.event.ActionEvent evt) {
	        try {
	            if (!redoQueue.isEmpty()) {
	                undoStack.push(textArea.getText()); // Guarda el estado actual del texto en la pila de deshacer
	                textArea.setText(redoQueue.poll()); // Obtiene el último estado del texto de la cola de rehacer y establecerlo
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    // Botón Abrir
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
	
	    public static void main(String args[]) {
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new TextEditor().setVisible(true);
	            }
	        });
	    }
	}
