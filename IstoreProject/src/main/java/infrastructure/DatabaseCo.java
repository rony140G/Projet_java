package infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCo {
    private static final String url = "jdbc:mysql://localhost/Istore?";
    private static final String utilisateur = "root";
    private static final String motDePasse = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, utilisateur, motDePasse);
    }
}
