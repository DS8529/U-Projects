package lukia2.userinteface;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuA extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuA frame = new MenuA();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MenuA() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 623);
        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(Color.LIGHT_GRAY);

        JDesktopPane desktopPane_1 = new JDesktopPane();
        desktopPane_1.setBackground(Color.WHITE);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(desktopPane_1, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(36)
                                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(desktopPane_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                .addGap(26)
                                .addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(37, Short.MAX_VALUE))
        );

        JButton btnNewButton = new JButton("Depositar");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Cocogoose", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Deposit depositA = new Deposit();
                depositA.show();
                dispose();

            }
        });
        btnNewButton.setBounds(32, 51, 287, 47);
        desktopPane.add(btnNewButton);

        JButton transfer = new JButton("Transferir");
        transfer.setForeground(Color.BLACK);
        transfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Transfer trans = new Transfer();
                trans.show();
                dispose();

            }
        });
        transfer.setFont(new Font("Cocogoose", Font.BOLD, 16));
        transfer.setBounds(32, 96, 287, 52);
        desktopPane.add(transfer);

        JButton withdrawals = new JButton("Retirar");
        withdrawals.setForeground(Color.BLACK);
        withdrawals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Withdrawals withd = new Withdrawals();
                withd.show();
                dispose();

            }
        });
        withdrawals.setFont(new Font("Cocogoose", Font.BOLD, 16));
        withdrawals.setBounds(32, 148, 287, 52);
        desktopPane.add(withdrawals);

        JButton balance = new JButton("Ver Saldo");
        balance.setForeground(Color.BLACK);
        balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Balance viewBalance = new Balance();
                viewBalance.show();
                dispose();

            }
        });
        balance.setFont(new Font("Cocogoose", Font.BOLD, 16));
        balance.setBounds(32, 255, 287, 52);
        desktopPane.add(balance);

        JButton btnNewButton_4 = new JButton("Salir");
        btnNewButton_4.setForeground(Color.BLACK);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Login logout = new Login();
                logout.show();
                dispose();
                JOptionPane.showMessageDialog(null, "Hasta pronto");
            }
        });
        btnNewButton_4.setFont(new Font("Cocogoose", Font.BOLD, 16));
        btnNewButton_4.setBounds(32, 348, 287, 47);
        desktopPane.add(btnNewButton_4);

        JButton balance_1 = new JButton("Pagar Facturas");
        balance_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Payments paym = new Payments();
                paym.show();
                dispose();

            }
        });

        balance_1.setForeground(Color.BLACK);
        balance_1.setFont(new Font("Cocogoose", Font.BOLD, 16));
        balance_1.setBounds(32, 201, 287, 52);
        desktopPane.add(balance_1);

        JLabel lblNewLabel = new JLabel("Bienvenido a Lukia2");
        lblNewLabel.setToolTipText("");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(86, 6, 254, 27);
        desktopPane_1.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Cocogoose Pro", Font.BOLD, 22));
        contentPane.setLayout(gl_contentPane);
    }
}
