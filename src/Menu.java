import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
    public static void user_menu(String UID) {
        JFrame jFrame = new JFrame("Use Functions");
        JButton jButton = new JButton("View Books");
        jButton.setBounds(20,20,120,25);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jFrame1 = new JFrame("Books Available");
                Connection connection = Connect.connect();
                String sql = "SELECT * FROM BOOKS";
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE LIBRARY");
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable();
                    //book_list.setModel(DbUtils.resultSetToTableModel(rs));
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    jFrame1.add(scrollPane);
                    jFrame1.setSize(800,400);
                    jFrame1.setVisible(true);
                    jFrame1.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }

    public static void admin_menu() {
        JFrame frame = new JFrame("Admin Functions");
        JButton create_reset = new JButton("Create/Reset");
        create_reset.setBounds(450, 60, 120,25);
        create_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database.create();
                    JOptionPane.showMessageDialog(null, "Database Created/Reset");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton view_books = new JButton("View Books");
        view_books.setBounds(20,20,120,25);
        view_books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Books Available");
                Connection connection = Connect.connect();
                String sql = "SELECT * FROM BOOKS";
                try {
                    Statement stmt = connection.createStatement();
                    stmt.executeUpdate("USE LIBRARY");
                    stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    JTable book_list = new JTable();
                    JScrollPane scrollPane = new JScrollPane(book_list);
                    frame1.add(scrollPane);
                    frame1.setSize(800,400);
                    frame1.setVisible(true);
                    frame1.setLocationRelativeTo(null);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, e1);
                }
            }
        });
    }
}
