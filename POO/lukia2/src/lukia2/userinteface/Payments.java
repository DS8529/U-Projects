package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Payments extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField valP;
    private JTextField saldoU;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Payments frame = new Payments();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Payments() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 356);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.WHITE);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        updateEntry = new JTextField();
        updateEntry.setBounds(285, 57, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JButton btnNewButton = new JButton("Pagar");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String str = updateEntry.getText();

                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement("SELECT saldo FROM user WHERE phone_Number = ?");
                    pst.setString(1, updateEntry.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        int saldoActual = rs.getInt("saldo");
                        int nuevoSaldo = saldoActual - Integer.parseInt(valP.getText());
                        String updateQuery = "UPDATE user SET saldo = ? WHERE phone_Number = ?";
                        pst = con.prepareStatement(updateQuery);
                        pst.setInt(1, nuevoSaldo);
                        pst.setString(2, updateEntry.getText());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Pago realizado correctamente ");
                        JOptionPane.showMessageDialog(null, "Nuevo saldo " + nuevoSaldo);
                        MenuA menu = new MenuA();
                        menu.show();
                        dispose();

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al realizar el pago");
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (pst != null) {
                            pst.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });
        btnNewButton.setFont(new Font("Cocogoose", Font.BOLD, 14));
        btnNewButton.setBounds(333, 220, 149, 33);
        desktopPane.add(btnNewButton);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuA menu = new MenuA();
                menu.show();
                dispose();
            }
        });
        btnCancel.setFont(new Font("Cocogoose", Font.BOLD, 14));
        btnCancel.setBounds(142, 220, 149, 33);
        desktopPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Ingresa tu celular");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel.setBounds(59, 57, 197, 33);
        desktopPane.add(lblNewLabel);

        JLabel lblServicioAPagar = new JLabel("Servicio a pagar");
        lblServicioAPagar.setForeground(Color.BLACK);
        lblServicioAPagar.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblServicioAPagar.setBounds(59, 96, 197, 33);
        desktopPane.add(lblServicioAPagar);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Chec", "Aguas de Manizales", "Claro", "Netflix", "HBO", "Star +"}));
        comboBox.setToolTipText("");
        comboBox.setBounds(285, 102, 237, 27);
        desktopPane.add(comboBox);

        JLabel lblValor = new JLabel("Valor");
        lblValor.setForeground(Color.BLACK);
        lblValor.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblValor.setBounds(59, 141, 59, 28);
        desktopPane.add(lblValor);

        valP = new JTextField();
        valP.setColumns(10);
        valP.setBounds(285, 141, 237, 33);
        desktopPane.add(valP);
        contentPane.setLayout(gl_contentPane);
    }
}
