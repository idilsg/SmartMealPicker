package service;

import model.*;

import java.util.EnumSet;

public class UserPreferences {
    public Place place = Place.ANY;
    public Category category = Category.ANY;
    public int maxPrepMinutes = 30;

    // empty => any
    public EnumSet<BudgetLevel> allowedBudgets = EnumSet.noneOf(BudgetLevel.class);
    public EnumSet<CalorieLevel> allowedCalories = EnumSet.noneOf(CalorieLevel.class);

    // empty => no restriction
    public EnumSet<DietTag> requiredDietTags = EnumSet.noneOf(DietTag.class);

    // empty => ignore
    public EnumSet<TasteTag> desiredTasteTags = EnumSet.noneOf(TasteTag.class);
}
