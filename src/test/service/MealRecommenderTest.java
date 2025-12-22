package service;

import data.MealData;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MealRecommenderTest {

    private MealRecommender recommender;
    private UserPreferences prefs;

    @BeforeEach
    void setUp() {
        recommender = new MealRecommender();
        prefs = new UserPreferences();
        prefs.category = Category.ANY;
        prefs.place = Place.ANY;
        prefs.maxPrepMinutes = 180;
    }

    @AfterEach
    void tearDown() {
        recommender = null;
        prefs = null;
    }

    @Test
    void budgetLowMedium_shouldNotReturnHighBudgetMeals() {
        prefs.allowedBudgets = EnumSet.of(BudgetLevel.LOW, BudgetLevel.MEDIUM);

        List<Meal> res = recommender.recommend(MealData.getDefaultMeals(), prefs);

        assertFalse(res.isEmpty(), "Expected at least one meal");
        assertTrue(res.stream().noneMatch(m -> m.getBudgetLevel() == BudgetLevel.HIGH),
                "Should not return HIGH budget meals");
    }

    @Test
    void veganRequired_shouldReturnOnlyVeganMeals() {
        prefs.requiredDietTags = EnumSet.of(DietTag.VEGAN);

        List<Meal> res = recommender.recommend(MealData.getDefaultMeals(), prefs);

        assertFalse(res.isEmpty(), "Expected at least one vegan meal in sample data");
        assertTrue(res.stream().allMatch(m -> m.getDietTags().contains(DietTag.VEGAN)),
                "All results must contain VEGAN tag");
    }

    @Test
    void spicyPreference_shouldReturnOnlySpicyMeals() {
        prefs.desiredTasteTags = EnumSet.of(TasteTag.SPICY);

        List<Meal> res = recommender.recommend(MealData.getDefaultMeals(), prefs);

        assertFalse(res.isEmpty(), "Expected at least one spicy meal (e.g., Spicy Nachos)");
        assertTrue(res.stream().allMatch(m -> m.getTasteTags().contains(TasteTag.SPICY)),
                "All results must contain SPICY tag");
    }

    @Test
    void maxPrepTime_shouldFilterOutLongerMeals() {
        prefs.maxPrepMinutes = 10;

        List<Meal> res = recommender.recommend(MealData.getDefaultMeals(), prefs);

        assertTrue(res.stream().allMatch(m -> m.getPrepMinutes() <= 10),
                "All results must have prepMinutes <= 10");
    }

    @Test
    void outsidePlace_shouldNotReturnHomeOnlyMeals() {
        prefs.place = Place.OUTSIDE;

        List<Meal> res = recommender.recommend(MealData.getDefaultMeals(), prefs);

        assertTrue(res.stream().allMatch(m ->
                        m.getPlace() == Place.OUTSIDE || m.getPlace() == Place.ANY),
                "Should not return HOME-only meals when OUTSIDE is selected");
    }
}
