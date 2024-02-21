package infrastructure;

import java.sql.*;

public class ManageStoreDao {
    //Parties sur l' insertion dans le store.
    public static boolean insertStore(String name) {
        String insertInventorySQL = "INSERT INTO inventory (inventory_Name, store_ID) VALUES (?, ?)";
        String insertStoreSQL = "INSERT INTO store (store_Name) VALUES (?)";

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement insertInventoryStatement = connection.prepareStatement(insertInventorySQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertStoreStatement = connection.prepareStatement(insertStoreSQL)) {

            insertInventoryStatement.setString(1, name + "_Inventory");
            insertInventoryStatement.setInt(2, 0); // Placeholder for store_ID, to be updated later
            int rowsInserted = insertInventoryStatement.executeUpdate();

            ResultSet generatedKeys = insertInventoryStatement.getGeneratedKeys();
            int inventoryID = -1;
            if (generatedKeys.next()) {
                inventoryID = generatedKeys.getInt(1);
            }

            insertStoreStatement.setString(1, name);
            rowsInserted += insertStoreStatement.executeUpdate();

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

    public static void VerifyIfIsInStore() {

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

    // Parties sur les articles
    public static boolean deleteArticles(String articleName, int articleQuantity, int inventoryID) {
        if (articleQuantity <= 0) {
            System.out.println("Invalid article quantity.");
            return false;
        }

        String selectSql = "SELECT stock_disponible FROM article WHERE Nom_article = ? AND Inventory_ID = ?";
        String updateSql = "UPDATE article SET stock_disponible = stock_disponible - ? WHERE Nom_article = ? AND Inventory_ID = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
            // Vérifier le stock disponible
            selectStatement.setString(1, articleName);
            selectStatement.setInt(2, inventoryID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                int currentStock = resultSet.getInt("stock_disponible");
                if (currentStock < articleQuantity) {
                    System.out.println("Not enough stock available for deletion.");
                    return false;
                }
            } else {
                System.out.println("Article not found in inventory.");
                return false;
            }

            // Mettre à jour le stock
            updateStatement.setInt(1, articleQuantity);
            updateStatement.setString(2, articleName);
            updateStatement.setInt(3, inventoryID);
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Number of articles deleted successfully.");
                return true;
            } else {
                System.out.println("Failed to delete articles.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error updating articles: " + e.getMessage());
            return false;
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
    public int getUserID(String Mail) {
        int id = 0; // Valeur par défaut si aucun utilisateur n'est trouvé

        String sql = "SELECT id FROM user WHERE Email = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Mail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user ID: " + e.getMessage());
        }
        return id;
    }
    public static String getEmailFromUserID(int userID) {
        String email = null; // Valeur par défaut si aucun e-mail n'est trouvé

        String sql = "SELECT Email FROM user WHERE id = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString("Email");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving email from user ID: " + e.getMessage());
        }
        return email;
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
    // Partie sur les employées
    public static boolean addEmployeeToStore(int employeeID, int storeId) {

        String sql = "INSERT INTO Acces_Magasin (User_ID, store_ID) VALUES (?, ?)";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeID);
            statement.setInt(2, storeId);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error adding employee to store: " + e.getMessage());
            return false;
        }
    }
    public static void viewEmployeeStore(int Id_store) {
        String sql = "SELECT * FROM Acces_Magasin WHERE store_ID = ?";

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Id_store);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Employees with access to store " + Id_store + ":");
            while (resultSet.next()) {
                int userId = resultSet.getInt("User_ID");
                String userEmail = getEmailFromUserID(userId); // Appel à la méthode pour obtenir l'e-mail
                if (userEmail != null) {
                    System.out.println("User ID: " + userId + ", Email: " + userEmail);
                } else {
                    System.out.println("No email found for user ID: " + userId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error viewing employee store: " + e.getMessage());
        }
    }
    public static boolean deleteEmployeToStore(int employe_id, int store_ID) {
        String sql = "DELETE FROM Acces_Magasin WHERE User_ID = ? AND store_ID = ?";
        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employe_id);
            statement.setInt(2, store_ID);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting employee from store: " + e.getMessage());
            return false;
        }
    }

}