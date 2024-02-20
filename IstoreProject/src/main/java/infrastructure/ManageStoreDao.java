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

    public static void DelArticle(String nomArticle, int nbrArticle, int inventoryID) {
        String sql = "UPDATE article SET stock_disponible = stock_disponible - ? WHERE Nom_article = ? AND Inventory_ID = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nbrArticle);
            statement.setString(2, nomArticle);
            statement.setInt(3, inventoryID);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Number of articles deleted successfully.");
            } else {
                System.out.println("Failed to add articles.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating articles: " + e.getMessage());
        }
    }

    public void ViewStore() {
        String sql = "SELECT * FROM store";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Id : " + resultSet.getString("store_ID") + " ;Nom de la boutique : " + resultSet.getString("store_Name"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving store: " + e.getMessage());
        }
    }

    public static boolean InsertArticle(String article,int price, int stock_dispo, int ivent_id) {
        String sql = "INSERT INTO Article (Nom_article, Prix, stock_disponible, Inventory_ID) VALUES (?, ?, ?, ?)";
        try (Connection connexion = DatabaseCo.getConnection();
             PreparedStatement statement = connexion.prepareStatement(sql)) {

            statement.setString(1, article);
            statement.setInt(2, price);
            statement.setInt(3, stock_dispo);
            statement.setInt(4, ivent_id);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            return false;
        }

    }

    public static void ViewArticleInInventory(String storeName) {
        String sql = "SELECT * FROM Article " +
                "JOIN Inventory ON Article.Inventory_ID = Inventory.Inventory_ID " +
                "JOIN Store ON Inventory.Store_ID = Store.Store_ID " +
                "WHERE Store.Store_Name = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storeName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Article ID: " + resultSet.getString("article_ID") +
                        "; Nom de l'article: " + resultSet.getString("Nom_article") +
                        "; Prix : " + resultSet.getString("Prix") +
                        "; Stock disponible : " + resultSet.getString("stock_disponible"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving Inventory: " + e.getMessage());
        }
    }

    public void manageinventory() {
    }

    public int getInventoryId(String storeName) {
        String sql = "SELECT Inventory_ID FROM inventory " +
                "JOIN store ON inventory.Store_ID = store.Store_ID " +
                "WHERE store.Store_Name = ?";

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storeName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("Inventory_ID");
                } else {
                    System.out.println("Inventory not found for store: " + storeName);
                    return -1; // ou un autre valeur pour indiquer une absence d'inventaire
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving inventory ID: " + e.getMessage());
            return -1;
        }
    }


    public static void addArticle(String nomArticle, int nbrArticle, int inventoryID) {
        String sql = "UPDATE article SET stock_disponible = stock_disponible + ? WHERE Nom_article = ? AND Inventory_ID = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nbrArticle);
            statement.setString(2, nomArticle);
            statement.setInt(3, inventoryID);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Number of articles added successfully.");
            } else {
                System.out.println("Failed to add articles.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating articles: " + e.getMessage());
        }
    }

}
