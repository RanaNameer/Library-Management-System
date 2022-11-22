import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    public static void login() {
        JFrame f = new JFrame("Login");
        JLabel l1, l2;
        l1 = new JLabel("Username");
        l1.setBounds(30,15,100,30);
        l2 = new JLabel("Password");
        l2.setBounds(30,50,100,30);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(110,15,200,30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110,50,200,30);
        JButton login_button = new JButton("login");
        login_button.setBounds(130,90,80,25);
        login_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                if (username.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username");
                }
                else if (password.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter password");
                }
                else {
                    Connection connection = Connect.connect();
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate("USE LIBRARY");
                        String st = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'");
                        ResultSet rs = stmt.executeQuery(st);
                        if (rs.next()==false) {
                            System.out.println("No such user");
                            JOptionPane.showMessageDialog(null, "Wrong username or password");
                        } else {
                            f.dispose();
                            rs.beforeFirst();
                            while (rs.next()) {
                                String admin = rs.getString("ADMIN");
                                String UID = rs.getString("UID");
                                if (admin.equals("1")) {
                                    Menu.admin_menu();
                                } else {
                                    Menu.user_menu(UID);
                                }
                            }
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        f.add(passwordField); //add password
        f.add(login_button);//adding button in JFrame
        f.add(usernameField);  //add user
        f.add(l1);  // add label1 i.e. for username
        f.add(l2); // add label2 i.e. for password

        f.setSize(400,180);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);
    }
}
