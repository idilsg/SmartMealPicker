package service;

import model.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MealRecommender {

    public List<Meal> recommend(List<Meal> allMeals, UserPreferences p) {
        List<Meal> results = new ArrayList<>();

        for (Meal meal : allMeals) {
            if (!matchesPlace(meal, p.place)) continue;
            if (!matchesCategory(meal, p.category)) continue;
            if (meal.getPrepMinutes() > p.maxPrepMinutes) continue;

            if (!p.allowedBudgets.isEmpty() && !p.allowedBudgets.contains(meal.getBudgetLevel())) continue;
            if (!p.allowedCalories.isEmpty() && !p.allowedCalories.contains(meal.getCalorieLevel())) continue;

            if (!p.requiredDietTags.isEmpty() && !meal.getDietTags().containsAll(p.requiredDietTags)) continue;

            if (!p.desiredTasteTags.isEmpty() && !hasAnyTaste(meal, p.desiredTasteTags)) continue;

            results.add(meal);
        }

        return results;
    }

    private boolean matchesPlace(Meal meal, Place selectedPlace) {
        if (selectedPlace == Place.ANY) return true;
        return meal.getPlace() == selectedPlace || meal.getPlace() == Place.ANY;
    }

    private boolean matchesCategory(Meal meal, Category selectedCategory) {
        if (selectedCategory == Category.ANY) return true;
        return meal.getCategory() == selectedCategory;
    }

    private boolean hasAnyTaste(Meal meal, EnumSet<TasteTag> desired) {
        for (TasteTag t : desired) {
            if (meal.getTasteTags().contains(t)) return true;
        }
        return false;
    }
}
