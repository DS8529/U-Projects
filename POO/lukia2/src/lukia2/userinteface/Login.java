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

import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame {

    public final JPanel contentPane;
    public JTextField username;
    public JPasswordField password;

    Connection con = null;
    PreparedStatement pst = null;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 459, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel loginUsername = new JLabel("Celular");
        loginUsername.setForeground(Color.BLACK);
        loginUsername.setFont(new Font("Cocogoose", Font.BOLD, 14));

        JLabel loginPassword = new JLabel("Contraseña");
        loginPassword.setForeground(Color.BLACK);
        loginPassword.setFont(new Font("Cocogoose", Font.BOLD, 14));

        username = new JTextField();
        username.setColumns(10);

        password = new JPasswordField();

        JButton login = new JButton("Login");
        login.setForeground(Color.BLACK);
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    String u = username.getText();
                    String p = password.getText();

                    String SQL = "SELECT phone_number, password FROM `user`" + "WHERE phone_number=? AND password=?";
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema_1", "root", "");

                    PreparedStatement pstmt = con.prepareStatement(SQL);
                    pstmt.setString(1, u);
                    pstmt.setString(2, p);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Bienvenido a lukia2:(");
                        //conexión con el menú account
                        MenuA menuAccount = new MenuA();
                        menuAccount.show();
                        dispose();

                    } else if (username.getText().isEmpty() || password.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa el número de celular y la contraseña:(");
                    } else if (username.getText().equals("admin") && password.getText().equals("admin")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido a lukia2:(");
                        //conexión con el menú admin
                        Menu menuPage = new Menu();
                        menuPage.show();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Este no es tu celular, o no es tu contraseña :(");
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

        login.setFont(new Font("Cocogoose", Font.BOLD, 14));

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(203, 205, 205));

        JButton btnRegstrate = new JButton("Regístrate");
        btnRegstrate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                User userDetails = new User();
                userDetails.show();
                dispose();
            }

        });
        btnRegstrate.setForeground(Color.BLACK);
        btnRegstrate.setFont(new Font("Cocogoose", Font.BOLD, 14));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(74, Short.MAX_VALUE)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(loginPassword)
                                        .addComponent(loginUsername))
                                .addGap(18)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(password)
                                        .addComponent(username, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                .addContainerGap(64, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
                                .addGap(16))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(131)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnRegstrate, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(login, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(153, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                .addGap(47)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(loginUsername)
                                        .addComponent(username, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(41)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(loginPassword)
                                        .addComponent(password, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(37)
                                .addComponent(login, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(btnRegstrate, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );

        JLabel loginpage = new JLabel("Login");
        loginpage.setForeground(Color.BLACK);
        loginpage.setBounds(165, 47, 75, 27);
        desktopPane.add(loginpage);
        loginpage.setFont(new Font("Cocogoose", Font.BOLD, 22));
        contentPane.setLayout(gl_contentPane);

    }
}
