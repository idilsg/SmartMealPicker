package data;

import model.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MealData {

    public static List<Meal> getDefaultMeals() {
        try {
            return JdbcMealData.loadMealsFromDb();
        } catch (Exception e) {
            System.out.println("DB not available, using in-memory dataset: " + e.getMessage());
        }

        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal(
                "Menemen (fallback data)",
                Category.MAIN_DISH,
                Place.HOME,
                15,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Kuru Fasulye",
                Category.MAIN_DISH,
                Place.HOME,
                40,
                BudgetLevel.LOW,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Mercimek Çorbası",
                Category.MAIN_DISH,
                Place.ANY,
                30,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Adana Kebap",
                Category.MAIN_DISH,
                Place.OUTSIDE,
                20,
                BudgetLevel.HIGH,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SPICY, TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Lahmacun",
                Category.SNACK,
                Place.OUTSIDE,
                10,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SPICY, TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Peynirli Gözleme",
                Category.SNACK,
                Place.ANY,
                15,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Simit & Çay",
                Category.SNACK,
                Place.OUTSIDE,
                5,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Baklava",
                Category.DESSERT,
                Place.OUTSIDE,
                5,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Sütlaç",
                Category.DESSERT,
                Place.HOME,
                25,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Türk Kahvesi",
                Category.DRINK,
                Place.ANY,
                5,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "İmam Bayıldı",
                Category.MAIN_DISH,
                Place.HOME,
                45,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Mantı",
                Category.MAIN_DISH,
                Place.HOME,
                60,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Çiğ Köfte",
                Category.SNACK,
                Place.ANY,
                10,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SPICY)
        ));

        meals.add(new Meal(
                "Iskender Kebap",
                Category.MAIN_DISH,
                Place.OUTSIDE,
                20,
                BudgetLevel.HIGH,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Tost",
                Category.SNACK,
                Place.ANY,
                5,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Ayran",
                Category.DRINK,
                Place.ANY,
                2,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGETARIAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Künefe",
                Category.DESSERT,
                Place.OUTSIDE,
                15,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Zeytinyağlı Yaprak Sarma",
                Category.MAIN_DISH,
                Place.HOME,
                50,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        return meals;
    }
}
