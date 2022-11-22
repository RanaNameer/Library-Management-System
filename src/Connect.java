import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LIBRARY", "root", "Naushair@21");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
