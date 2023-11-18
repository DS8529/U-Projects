package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeactiveUser extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField deleteEntry;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeactiveUser frame = new DeactiveUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DeactiveUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 456, 526);
        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(249, 249, 255));

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(new Color(251, 252, 255));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(desktopPane, Alignment.LEADING)
                                        .addComponent(desktopPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                                .addContainerGap(28, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                .addContainerGap())
        );

        deleteEntry = new JTextField();
        deleteEntry.setBounds(111, 40, 206, 29);
        desktopPane_1.add(deleteEntry);
        deleteEntry.setColumns(10);

        JButton deleteData = new JButton("Eliminar");
        deleteData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String query = "DELETE FROM `user` WHERE phone_Number=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement(query);

                    String pid = deleteEntry.getText();

                    pst.setString(1, pid);

                    int k = pst.executeUpdate();

                    if (k == 1) {
                        JOptionPane.showMessageDialog(null, "Usuario Eliminado :)");
                        dispose();
                        Menu menu = new Menu();
                        menu.show();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
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
        deleteData.setForeground(Color.BLACK);
        deleteData.setBounds(130, 111, 167, 37);
        desktopPane_1.add(deleteData);
        deleteData.setFont(new Font("Cocogoose", Font.BOLD, 14));

        JButton btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Menu menu = new Menu();
                menu.show();
                dispose();

            }
        });
        btnNewButton_1.setFont(new Font("Cocogoose", Font.BOLD, 14));
        btnNewButton_1.setBounds(130, 171, 167, 37);
        desktopPane_1.add(btnNewButton_1);

        JLabel lblNewLabel = new JLabel("Ingresa el celular del usuario");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(66, 90, 326, 25);
        desktopPane.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        contentPane.setLayout(gl_contentPane);
    }
}
