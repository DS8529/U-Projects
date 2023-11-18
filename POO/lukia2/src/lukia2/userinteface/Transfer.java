package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Transfer extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;
    PreparedStatement pstUpdate2 = null;
    ResultSet rs2;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField saldoU;
    private JTextField updateEntry2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Transfer frame = new Transfer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Transfer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 607);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.WHITE);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(Color.WHITE);

        updateEntry2 = new JTextField();
        updateEntry2.setColumns(10);
        updateEntry2.setBounds(242, 86, 237, 33);
        desktopPane_1.add(updateEntry2);

        JLabel lblNewLabel_1 = new JLabel("Celular");
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel_1.setBounds(43, 84, 187, 33);
        desktopPane_1.add(lblNewLabel_1);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
                                .addGap(14))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(67, Short.MAX_VALUE))
        );

        JLabel lblParaQuienVa = new JLabel("Para quien va");
        lblParaQuienVa.setForeground(Color.BLACK);
        lblParaQuienVa.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblParaQuienVa.setBounds(43, 39, 187, 33);
        desktopPane_1.add(lblParaQuienVa);

        JButton btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(133, 179, 149, 33);
        desktopPane_1.add(btnCancel);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuA menuAccount = new MenuA();
                menuAccount.show();
                dispose();
            }
        });
        btnCancel.setFont(new Font("Cocogoose", Font.BOLD, 14));

        JButton depositBtn = new JButton("Depositar");
        depositBtn.setBounds(351, 177, 128, 36);
        desktopPane_1.add(depositBtn);
        depositBtn.addActionListener(new ActionListener() {

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

                        PreparedStatement pstUpdate2 = con.prepareStatement("SELECT saldo FROM user WHERE phone_Number = ?");
                        pstUpdate2.setString(1, updateEntry2.getText());
                        ResultSet rs2 = pstUpdate2.executeQuery();

                        if (rs2.next()) {
                            int saldoActual2 = rs2.getInt("saldo");
                            int nuevoSaldo2 = saldoActual2 + Integer.parseInt(saldoU.getText());
                            String updateQuery2 = "UPDATE user SET saldo = ? WHERE phone_Number = ?";
                            pstUpdate2 = con.prepareStatement(updateQuery2);
                            pstUpdate2.setInt(1, nuevoSaldo2);
                            pstUpdate2.setString(2, updateEntry2.getText());
                            pstUpdate2.executeUpdate();

                            JOptionPane.showMessageDialog(null, "Transferencia realizada correctamente");
                            JOptionPane.showMessageDialog(null, "Nuevo saldo " + nuevoSaldo);

                            MenuA menu = new MenuA();
                            menu.show();
                            dispose();
                        }

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
        depositBtn.setForeground(Color.BLACK);
        depositBtn.setFont(new Font("Cocogoose", Font.BOLD, 14));

        updateEntry = new JTextField();
        updateEntry.setBounds(242, 81, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JLabel lblNewLabel = new JLabel("Celular");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel.setBounds(43, 79, 187, 33);
        desktopPane.add(lblNewLabel);

        JLabel lblCelular = new JLabel("Ingresa el monto");
        lblCelular.setForeground(Color.BLACK);
        lblCelular.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblCelular.setBounds(43, 128, 187, 33);
        desktopPane.add(lblCelular);

        saldoU = new JTextField();
        saldoU.setColumns(10);
        saldoU.setBounds(242, 123, 237, 33);
        desktopPane.add(saldoU);
        contentPane.setLayout(gl_contentPane);

        JLabel lblTusDatos = new JLabel("Tus datos");
        lblTusDatos.setForeground(Color.BLACK);
        lblTusDatos.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblTusDatos.setBounds(43, 34, 187, 33);
        desktopPane.add(lblTusDatos);

    }
}
