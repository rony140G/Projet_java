package infrastructure;
import java.sql.*;

public class WhiteListDao {
    public static boolean isWhitelisted(String email) {
        String sql = "SELECT * FROM listeblanche WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            // Si une ligne est retournée, cela signifie que l'e-mail est sur la liste blanche
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking whitelist: " + e.getMessage());
            return false;
        }
    }

    public void viewWhiteList() {
        String sql = "SELECT * FROM listeblanche";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Email: " + resultSet.getString("Email"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving whitelist: " + e.getMessage());
        }
    }

    public void deleteFromWhiteList(String email) {
        String sql = "DELETE FROM listeblanche WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Email deleted from whitelist successfully.");
            } else {
                System.out.println("Failed to delete email from whitelist.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting email: " + e.getMessage());
        }
    }

    public void insertIntoWhiteList(String email) {
        String sql = "INSERT INTO listeblanche (Email) VALUES (?)";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Email inserted into whitelist successfully.");
            } else {
                System.out.println("Failed to insert email into whitelist.");
            }
        } catch (SQLException e) {
            System.out.println("Error inserting email: " + e.getMessage());
        }
    }
}
