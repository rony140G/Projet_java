package infrastructure;

import java.sql.*;

public class StoreAccessResult {
    private final boolean isInStore;
    private final int storeId;

    public StoreAccessResult(boolean isInStore, int storeId) {
        this.isInStore = isInStore;
        this.storeId = storeId;
    }

    public boolean isInStore() {
        return isInStore;
    }

    public int getStoreId() {
        return storeId;
    }

    public static StoreAccessResult verifyIfIsInStore(int userId) {
        String sql = "SELECT store_ID FROM acces_magasin WHERE User_ID = ?";
        int storeId = -1; // Valeur par défaut
        boolean isInStore = false;

        try (Connection connection = DatabaseCo.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            // Vérifiez si l'utilisateur est associé à une boutique
            if (resultSet.next()) {
                isInStore = true;
                storeId = resultSet.getInt("store_ID");
            }
        } catch (SQLException e) {
            System.out.println("Error verifying user's store access: " + e.getMessage());
        }

        return new StoreAccessResult(isInStore, storeId);
    }
}
