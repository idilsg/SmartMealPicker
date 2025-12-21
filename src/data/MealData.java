package data;

import model.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MealData {

    public static List<Meal> getDefaultMeals() {
        List<Meal> meals = new ArrayList<>();

        meals.add(new Meal(
                "Chicken Wrap",
                Category.MAIN_DISH,
                Place.ANY,
                20,
                BudgetLevel.MEDIUM,
                CalorieLevel.MEDIUM,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Veggie Stir Fry",
                Category.MAIN_DISH,
                Place.HOME,
                25,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Vegan Buddha Bowl",
                Category.MAIN_DISH,
                Place.HOME,
                30,
                BudgetLevel.MEDIUM,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGAN),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Cheeseburger",
                Category.MAIN_DISH,
                Place.OUTSIDE,
                15,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Pancakes",
                Category.DESSERT,
                Place.HOME,
                20,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Chocolate Cake",
                Category.DESSERT,
                Place.OUTSIDE,
                10,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Fruit Yogurt Bowl",
                Category.SNACK,
                Place.ANY,
                5,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        meals.add(new Meal(
                "Spicy Nachos",
                Category.SNACK,
                Place.OUTSIDE,
                10,
                BudgetLevel.MEDIUM,
                CalorieLevel.HIGH,
                EnumSet.noneOf(DietTag.class),
                EnumSet.of(TasteTag.SPICY, TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Lentil Soup",
                Category.MAIN_DISH,
                Place.HOME,
                35,
                BudgetLevel.LOW,
                CalorieLevel.LOW,
                EnumSet.of(DietTag.VEGAN, DietTag.GLUTEN_FREE),
                EnumSet.of(TasteTag.SAVORY)
        ));

        meals.add(new Meal(
                "Iced Coffee",
                Category.DRINK,
                Place.OUTSIDE,
                5,
                BudgetLevel.LOW,
                CalorieLevel.MEDIUM,
                EnumSet.of(DietTag.VEGETARIAN),
                EnumSet.of(TasteTag.SWEET)
        ));

        return meals;
    }
}
