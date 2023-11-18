package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModifyUser extends JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs;

    private JPanel contentPane;
    private JTextField updateEntry;
    private JTextField nameU;
    private JTextField last_NameU;
    private JTextField emailU;
    private JTextField phone_NumberU;
    private JTextField addressU;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModifyUser frame = new ModifyUser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ModifyUser() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 657, 656);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.LIGHT_GRAY);

        nameU = new JTextField();
        nameU.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setFont(new Font("Cocogoose", Font.BOLD, 16));

        JLabel lblNewLabel_1_1 = new JLabel("Apellido");
        lblNewLabel_1_1.setForeground(Color.BLACK);
        lblNewLabel_1_1.setFont(new Font("Cocogoose", Font.BOLD, 16));

        JLabel lblNewLabel_1_2 = new JLabel("Email");
        lblNewLabel_1_2.setForeground(Color.BLACK);
        lblNewLabel_1_2.setFont(new Font("Cocogoose", Font.BOLD, 16));

        JLabel lblNewLabel_1_3 = new JLabel("Celular");
        lblNewLabel_1_3.setForeground(Color.BLACK);
        lblNewLabel_1_3.setFont(new Font("Cocogoose", Font.BOLD, 16));

        JLabel lblNewLabel_1_4 = new JLabel("Dirección");
        lblNewLabel_1_4.setForeground(Color.BLACK);
        lblNewLabel_1_4.setFont(new Font("Cocogoose", Font.BOLD, 16));

        last_NameU = new JTextField();
        last_NameU.setColumns(10);

        emailU = new JTextField();
        emailU.setColumns(10);

        phone_NumberU = new JTextField();
        phone_NumberU.setColumns(10);

        addressU = new JTextField();
        addressU.setColumns(10);

        JButton updateBtn = new JButton("Actualizar");
        updateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String query = "UPDATE `user` SET name=?, last_name=?, email=?, phone_Number=?, address=? WHERE phone_Number=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement(query);

                    String pid = updateEntry.getText();
                    pst.setString(1, nameU.getText());
                    pst.setString(2, last_NameU.getText());
                    pst.setString(3, emailU.getText());
                    pst.setString(4, phone_NumberU.getText());
                    pst.setString(5, addressU.getText());
                    pst.setString(6, pid);
                    if (nameU.getText().equals("") || last_NameU.getText().equals("") || emailU.getText().equals("") || phone_NumberU.getText().equals("") || addressU.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Fill all the details :(");
                    } else {
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Usuario Modificado :)");
                        dispose();
                        Menu menu = new Menu();
                        menu.show();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }

            }
        });
        updateBtn.setForeground(Color.BLACK);
        updateBtn.setFont(new Font("Cocogoose", Font.BOLD, 14));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(127)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel_1)
                                                        .addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
                                                .addGap(78)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(last_NameU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emailU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(phone_NumberU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addressU, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(261)
                                                .addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
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
                                .addGap(27)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(last_NameU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(29)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(lblNewLabel_1_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phone_NumberU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(27)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(addressU, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1_4, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(updateBtn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        updateEntry = new JTextField();
        updateEntry.setBounds(200, 100, 237, 33);
        desktopPane.add(updateEntry);
        updateEntry.setColumns(10);

        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String str = updateEntry.getText();

                try {
                    con = DriverManager.getConnection("jdbc:mysql://localhost/schema_1", "root", "");
                    pst = con.prepareStatement("SELECT * FROM user where phone_Number = ?");
                    pst.setString(1, str);
                    rs = pst.executeQuery();
                    if (rs.next() == true) {
                        String s = rs.getString(1);
                        String s1 = rs.getString(2);
                        String s2 = rs.getString(3);
                        String s3 = rs.getString(4);
                        String s4 = rs.getString(5);

                        nameU.setText(s);
                        last_NameU.setText(s1);
                        emailU.setText(s2);
                        phone_NumberU.setText(s3);
                        addressU.setText(s4);
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
                Menu menu = new Menu();
                menu.show();
                dispose();
            }
        });
        btnCancel.setFont(new Font("Cocogoose", Font.BOLD, 14));
        btnCancel.setBounds(153, 164, 149, 33);
        desktopPane.add(btnCancel);

        JLabel lblNewLabel = new JLabel("Buscar por número de celular");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Cocogoose", Font.BOLD, 20));
        lblNewLabel.setBounds(153, 54, 334, 33);
        desktopPane.add(lblNewLabel);
        contentPane.setLayout(gl_contentPane);
    }
}
