package data;

import java.sql.*;

public class DbInitializer {
    public static void init() {
        createDatabaseIfMissing();
        createTableIfMissing();
        seedIfEmpty();
    }

    private static void createDatabaseIfMissing() {
        String sql = "CREATE DATABASE IF NOT EXISTS mealdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
        try (Connection conn = DriverManager.getConnection(DbConfig.HOST_URL, DbConfig.USER, DbConfig.PASS);
             Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("DB create failed", e);
        }
    }

    private static void createTableIfMissing() {
        String sql = """
                CREATE TABLE IF NOT EXISTS meals (
                  id INT AUTO_INCREMENT PRIMARY KEY,
                  name VARCHAR(120) NOT NULL,
                  category VARCHAR(30) NOT NULL,
                  place VARCHAR(30) NOT NULL,
                  prep_minutes INT NOT NULL,
                  budget_level VARCHAR(30) NOT NULL,
                  calorie_level VARCHAR(30) NOT NULL,
                  diet_tags VARCHAR(200),
                  taste_tags VARCHAR(200)
                )
                """;
        try (Connection conn = DriverManager.getConnection(DbConfig.DB_URL, DbConfig.USER, DbConfig.PASS);
             Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Table create failed", e);
        }
    }

    private static void seedIfEmpty() {
        String countSql = "SELECT COUNT(*) FROM meals";
        try (Connection conn = DriverManager.getConnection(DbConfig.DB_URL, DbConfig.USER, DbConfig.PASS);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(countSql)) {

            rs.next();
            int count = rs.getInt(1);
            if (count > 0) return;

            seedMeals(conn);

        } catch (SQLException e) {
            throw new RuntimeException("Seed check/insert failed", e);
        }
    }

    private static void seedMeals(Connection conn) throws SQLException {
        String insert = """
                INSERT INTO meals (name, category, place, prep_minutes, budget_level, calorie_level, diet_tags, taste_tags)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(insert)) {
            add(ps, "Menemen", "MAIN_DISH", "HOME", 15, "LOW", "MEDIUM", "VEGETARIAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Kuru Fasulye", "MAIN_DISH", "HOME", 40, "LOW", "HIGH", "", "SAVORY");
            add(ps, "Mercimek Çorbası", "MAIN_DISH", "ANY", 30, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Adana Kebap", "MAIN_DISH", "OUTSIDE", 20, "HIGH", "HIGH", "", "SPICY,SAVORY");
            add(ps, "Lahmacun", "SNACK", "OUTSIDE", 10, "LOW", "MEDIUM", "", "SPICY,SAVORY");
            add(ps, "Peynirli Gözleme", "SNACK", "ANY", 15, "LOW", "MEDIUM", "VEGETARIAN", "SAVORY");
            add(ps, "Simit & Çay", "SNACK", "OUTSIDE", 5, "LOW", "LOW", "VEGETARIAN", "SAVORY");
            add(ps, "Baklava", "DESSERT", "OUTSIDE", 5, "MEDIUM", "HIGH", "VEGETARIAN", "SWEET");
            add(ps, "Sütlaç", "DESSERT", "HOME", 25, "LOW", "MEDIUM", "VEGETARIAN,GLUTEN_FREE", "SWEET");
            add(ps, "Türk Kahvesi", "DRINK", "ANY", 5, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SAVORY");
            add(ps, "İmam Bayıldı", "MAIN_DISH", "HOME", 45, "LOW", "MEDIUM", "VEGAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Mantı", "MAIN_DISH", "HOME", 60, "MEDIUM", "HIGH", "", "SAVORY");
            add(ps, "Çiğ Köfte", "SNACK", "ANY", 10, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SPICY");
            add(ps, "Iskender Kebap", "MAIN_DISH", "OUTSIDE", 20, "HIGH", "HIGH", "", "SAVORY");
            add(ps, "Tost", "SNACK", "ANY", 5, "LOW", "MEDIUM", "VEGETARIAN", "SAVORY");
            add(ps, "Ayran", "DRINK", "ANY", 2, "LOW", "LOW", "VEGETARIAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Künefe", "DESSERT", "OUTSIDE", 15, "MEDIUM", "HIGH", "VEGETARIAN", "SWEET");
            add(ps, "Zeytinyağlı Yaprak Sarma", "MAIN_DISH", "HOME", 50, "LOW", "MEDIUM", "VEGAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Ezogelin Çorbası", "MAIN_DISH", "HOME", 25, "LOW", "LOW", "VEGETARIAN,GLUTEN_FREE", "SAVORY,SOUR");
            add(ps, "Tavuk Döner", "MAIN_DISH", "OUTSIDE", 10, "MEDIUM", "HIGH", "", "SAVORY");
            add(ps, "Falafel Wrap", "MAIN_DISH", "OUTSIDE", 15, "MEDIUM", "MEDIUM", "VEGAN", "SAVORY,SPICY");
            add(ps, "Mevsim Salata", "SNACK", "ANY", 10, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SOUR,SAVORY");
            add(ps, "Sushi", "MAIN_DISH", "OUTSIDE", 25, "HIGH", "MEDIUM", "GLUTEN_FREE", "SAVORY");
            add(ps, "Dana Bonfile", "MAIN_DISH", "OUTSIDE", 30, "HIGH", "HIGH", "", "SAVORY");
            add(ps, "Patates Kızartması", "SNACK", "ANY", 15, "LOW", "MEDIUM", "VEGAN,GLUTEN_FREE", "SAVORY");
            add(ps, "Cheesecake", "DESSERT", "OUTSIDE", 10, "HIGH", "HIGH", "VEGETARIAN", "SWEET");
            add(ps, "Revani", "DESSERT", "HOME", 30, "LOW", "HIGH", "VEGETARIAN", "SWEET");
            add(ps, "Meyve Tabağı", "SNACK", "HOME", 5, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SWEET");
            add(ps, "Latte", "DRINK", "OUTSIDE", 5, "HIGH", "MEDIUM", "VEGETARIAN", "SWEET");
            add(ps, "Bitki Çayı", "DRINK", "ANY", 5, "LOW", "LOW", "VEGAN,GLUTEN_FREE", "SAVORY");
        }
    }

    private static void add(PreparedStatement ps,
                            String name, String category, String place, int prep,
                            String budget, String cal, String dietTags, String tasteTags) throws SQLException {
        ps.setString(1, name);
        ps.setString(2, category);
        ps.setString(3, place);
        ps.setInt(4, prep);
        ps.setString(5, budget);
        ps.setString(6, cal);
        ps.setString(7, (dietTags == null) ? "" : dietTags);
        ps.setString(8, (tasteTags == null) ? "" : tasteTags);
        ps.executeUpdate();
    }
}
