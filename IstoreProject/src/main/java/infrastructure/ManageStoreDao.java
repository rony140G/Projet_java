package infrastructure;

import java.sql.*;

public class ManageStoreDao {
    public static boolean insertStore(String name) {
        String insertInventorySQL = "INSERT INTO inventory (inventory_Name, store_ID) VALUES (?, ?)";
        String insertStoreSQL = "INSERT INTO store (store_Name) VALUES (?)";

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement insertInventoryStatement = connection.prepareStatement(insertInventorySQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertStoreStatement = connection.prepareStatement(insertStoreSQL)) {

            // Insérer un nouvel enregistrement dans la table des inventaires
            insertInventoryStatement.setString(1, name + "_Inventory");
            insertInventoryStatement.setInt(2, 0); // Placeholder for store_ID, to be updated later
            int rowsInserted = insertInventoryStatement.executeUpdate();

            // Récupérer l'inventoryID généré pour le nouvel inventaire
            ResultSet generatedKeys = insertInventoryStatement.getGeneratedKeys();
            int inventoryID = -1;
            if (generatedKeys.next()) {
                inventoryID = generatedKeys.getInt(1);
            }

            // Insérer un nouvel enregistrement dans la table des magasins
            insertStoreStatement.setString(1, name);
            rowsInserted += insertStoreStatement.executeUpdate();

            // Mettre à jour store_ID dans la table des inventaires
            if (inventoryID != -1) {
                String updateInventorySQL = "UPDATE inventory SET store_ID = ? WHERE Inventory_ID = ?";
                PreparedStatement updateInventoryStatement = connection.prepareStatement(updateInventorySQL);
                updateInventoryStatement.setInt(1, inventoryID);
                updateInventoryStatement.setInt(2, inventoryID);
                rowsInserted += updateInventoryStatement.executeUpdate();
            }

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting store: " + e.getMessage());
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
