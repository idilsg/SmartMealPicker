package model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Meal {

    private final String name;
    private final Category category;
    private final Place place;                 // HOME / OUTSIDE / ANY (means works for both)
    private final int prepMinutes;             // preparation time in minutes
    private final BudgetLevel budgetLevel;     // LOW/MEDIUM/HIGH
    private final CalorieLevel calorieLevel;   // LOW/MEDIUM/HIGH
    private final EnumSet<DietTag> dietTags;   // meal supports these diet tags
    private final EnumSet<TasteTag> tasteTags; // taste profile tags

    public Meal(
            String name,
            Category category,
            Place place,
            int prepMinutes,
            BudgetLevel budgetLevel,
            CalorieLevel calorieLevel,
            Set<DietTag> dietTags,
            Set<TasteTag> tasteTags
    ) {
        this.name = Objects.requireNonNull(name, "name");
        this.category = Objects.requireNonNull(category, "category");
        this.place = Objects.requireNonNull(place, "place");

        if (prepMinutes <= 0) {
            throw new IllegalArgumentException("prepMinutes must be > 0");
        }
        this.prepMinutes = prepMinutes;

        this.budgetLevel = Objects.requireNonNull(budgetLevel, "budgetLevel");
        this.calorieLevel = Objects.requireNonNull(calorieLevel, "calorieLevel");

        // store as EnumSet for speed + clean logic
        this.dietTags = (dietTags == null || dietTags.isEmpty())
                ? EnumSet.noneOf(DietTag.class)
                : EnumSet.copyOf(dietTags);

        this.tasteTags = (tasteTags == null || tasteTags.isEmpty())
                ? EnumSet.noneOf(TasteTag.class)
                : EnumSet.copyOf(tasteTags);
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public Place getPlace() { return place; }
    public int getPrepMinutes() { return prepMinutes; }
    public BudgetLevel getBudgetLevel() { return budgetLevel; }
    public CalorieLevel getCalorieLevel() { return calorieLevel; }

    public Set<DietTag> getDietTags() {
        return Collections.unmodifiableSet(dietTags);
    }

    public Set<TasteTag> getTasteTags() {
        return Collections.unmodifiableSet(tasteTags);
    }

    /**
     * Nice display string for the results list.
     */
    public String toDisplayString() {
        return String.format(
                "%s (%s, %s, %d min, %s budget, %s cal)",
                name,
                pretty(category),
                pretty(place),
                prepMinutes,
                pretty(budgetLevel),
                pretty(calorieLevel)
        );
    }

    private String pretty(Enum<?> e) {
        // MAIN_DISH -> Main dish
        String s = e.name().toLowerCase().replace('_', ' ');
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    @Override
    public String toString() {
        return toDisplayString();
    }
}
