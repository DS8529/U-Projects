package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Balance extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField nameU;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Balance frame = new Balance();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Balance() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 356);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.WHITE);

        nameU = new JTextField();
        nameU.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Saldo");
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Cocogoose", Font.BOLD, 16));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(127)
                                .addComponent(lblNewLabel_1)
                                .addGap(144)
                                .addComponent(nameU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(95, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE)
                                .addGap(14))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
                                .addGap(27)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1)
                                        .addComponent(nameU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(318, Short.MAX_VALUE))
        );

        updateEntry = new JTextField();
        updateEntry.setBounds(200, 100, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JButton btnNewButton = new JButton("Ver saldo");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String str = updateEntry.getText();

                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement("SELECT saldo FROM user where phone_Number = ?");
                    pst.setString(1, str);
                    rs = pst.executeQuery();
                    if (rs.next() == true) {
                        String s = rs.getString(1);

                        nameU.setText(s);

                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    // Cerrar recursos de base de datos
                    try {

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
        btnNewButton.setBounds(344, 164, 149, 33);
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
        btnCancel.setBounds(153, 164, 149, 33);
        desktopPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Ingresa tu celular");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel.setBounds(223, 55, 197, 33);
        desktopPane.add(lblNewLabel);
        contentPane.setLayout(gl_contentPane);
    }
}
