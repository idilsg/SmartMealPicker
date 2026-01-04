package data;

import model.*;

import java.sql.*;
import java.util.*;

public class JdbcMealData {

    public static List<Meal> loadMealsFromDb() throws SQLException {
        List<Meal> meals = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DbConfig.DB_URL, DbConfig.USER, DbConfig.PASS);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM meals");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                Category category = Category.valueOf(rs.getString("category"));
                Place place = Place.valueOf(rs.getString("place"));
                int prep = rs.getInt("prep_minutes");
                BudgetLevel budget = BudgetLevel.valueOf(rs.getString("budget_level"));
                CalorieLevel cal = CalorieLevel.valueOf(rs.getString("calorie_level"));

                EnumSet<DietTag> diet = parseEnumSet(rs.getString("diet_tags"), DietTag.class);
                EnumSet<TasteTag> taste = parseEnumSet(rs.getString("taste_tags"), TasteTag.class);

                meals.add(new Meal(name, category, place, prep, budget, cal, diet, taste));
            }
        }

        return meals;
    }

    private static <E extends Enum<E>> EnumSet<E> parseEnumSet(String csv, Class<E> enumClass) {
        EnumSet<E> set = EnumSet.noneOf(enumClass);
        if (csv == null || csv.isBlank()) return set;

        for (String part : csv.split(",")) {
            String token = part.trim();
            if (!token.isEmpty()) set.add(Enum.valueOf(enumClass, token));
        }
        return set;
    }
}
