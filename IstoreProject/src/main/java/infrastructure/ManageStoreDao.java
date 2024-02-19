package infrastructure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageStoreDao {
    public static boolean insertStore(String Name) {
        String sql = "INSERT INTO store (store_Name) VALUES (?)";

        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, Name);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteStore(String name) {
        String sql = "DELETE FROM store WHERE store_Name = ?";

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting store: " + e.getMessage());
            return false;
        }
    }

    public void ViewStore() {
        String sql = "SELECT * FROM store";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println( "Id : " + resultSet.getString("store_ID") +" ;Nom de la boutique : " + resultSet.getString("store_Name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving store: " + e.getMessage());
        }
    }
}
