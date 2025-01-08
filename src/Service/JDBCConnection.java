package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    public static Connection JDBCConnection() {
        final String url = "jdbc:mysql://localhost:3306/QLKHDT1";
        final String user = "root";
        final String password = "zZhoang030905Zz";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi Kết Nối");
        } catch (SQLException e) {
            System.out.println("Lỗi Kết Nối");
        }
        return null;
    }
}
