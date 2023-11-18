package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Withdrawals extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField saldoU;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Withdrawals frame = new Withdrawals();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Withdrawals() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 288);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.WHITE);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
                                .addGap(14))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(376, Short.MAX_VALUE))
        );

        updateEntry = new JTextField();
        updateEntry.setBounds(242, 57, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuA menuAccount = new MenuA();
                menuAccount.show();
                dispose();
            }
        });
        btnCancel.setFont(new Font("Cocogoose", Font.BOLD, 14));
        btnCancel.setBounds(153, 164, 149, 33);
        desktopPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Celular");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel.setBounds(43, 55, 187, 33);
        desktopPane.add(lblNewLabel);

        JLabel lblCelular = new JLabel("Ingresa el monto");
        lblCelular.setForeground(Color.BLACK);
        lblCelular.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblCelular.setBounds(43, 104, 187, 33);
        desktopPane.add(lblCelular);

        saldoU = new JTextField();
        saldoU.setColumns(10);
        saldoU.setBounds(242, 99, 237, 33);
        desktopPane.add(saldoU);
        contentPane.setLayout(gl_contentPane);

        JButton withdrawalsBtn = new JButton("Retirar");
        withdrawalsBtn.setBounds(371, 162, 128, 36);
        desktopPane.add(withdrawalsBtn);
        withdrawalsBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement("SELECT saldo FROM user WHERE phone_Number = ?");
                    pst.setString(1, updateEntry.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        int saldoActual = rs.getInt("saldo");
                        int nuevoSaldo = saldoActual - Integer.parseInt(saldoU.getText());
                        String updateQuery = "UPDATE user SET saldo = ? WHERE phone_Number = ?";
                        pst = con.prepareStatement(updateQuery);
                        pst.setInt(1, nuevoSaldo);
                        pst.setString(2, updateEntry.getText());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Dinero retirado correctamente");
                        JOptionPane.showMessageDialog(null, "Nuevo saldo " + nuevoSaldo);
                        MenuA menu = new MenuA();
                        menu.show();
                        dispose();

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar el saldo");
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
        withdrawalsBtn.setForeground(Color.BLACK);
        withdrawalsBtn.setFont(new Font("Cocogoose", Font.BOLD, 14));

    }
}
