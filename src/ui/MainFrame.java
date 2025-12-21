package ui;

import data.*;
import model.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import java.util.EnumSet;
import java.util.List;

public class MainFrame extends JFrame {

    // ---- Right panel: results ----
    private DefaultListModel<Meal> resultsModel;
    private JList<Meal> resultsList;

    // ---- Filters (we will read these later) ----
    private JRadioButton rbHome;
    private JRadioButton rbOutside;
    private JRadioButton rbAnyPlace;

    private JComboBox<String> cbCategory;
    private JSpinner spMaxPrepMinutes;

    private JCheckBox cbBudgetLow, cbBudgetMed, cbBudgetHigh;
    private JCheckBox cbCalLow, cbCalMed, cbCalHigh;

    private JCheckBox cbVegan, cbVegetarian, cbGlutenFree;
    private JCheckBox cbSpicy, cbSweet, cbSour, cbSavory;

    public MainFrame() {
        setTitle("What Should I Eat?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);

        // Main layout
        setLayout(new BorderLayout(10, 10));

        // Optional: simple header
        add(createHeader(), BorderLayout.NORTH);

        // Split pane: left filters, right results
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                createFiltersPanel(),
                createResultsPanel()
        );
        splitPane.setResizeWeight(0.38); // left panel %38
        splitPane.setDividerSize(8);
        add(splitPane, BorderLayout.CENTER);
    }

    private JComponent createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("What Should I Eat?");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Select your preferences and click Suggest Meals.");
        subtitle.setForeground(Color.DARK_GRAY);
        header.add(subtitle, BorderLayout.EAST);

        return header;
    }

    // ---------------- LEFT: Filters ----------------
    private JComponent createFiltersPanel() {
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(createPlacePanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createCategoryPanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createTimePanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createBudgetPanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createCaloriesPanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createDietaryPanel());
        container.add(Box.createVerticalStrut(10));

        container.add(createTastePanel());
        container.add(Box.createVerticalStrut(12));

        JButton btnSuggest = new JButton("Suggest Meals");
        btnSuggest.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSuggest.addActionListener(e -> onSuggestMeals());
        container.add(btnSuggest);

        // Add filler so everything stays at top
        container.add(Box.createVerticalGlue());

        // Put it into a scroll pane in case screen is small
        JScrollPane scroll = new JScrollPane(container);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        return scroll;
    }

    private JPanel createPlacePanel() {
        JPanel p = titledPanel("Where will you eat?");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        rbHome = new JRadioButton("At home");
        rbOutside = new JRadioButton("Outside");
        rbAnyPlace = new JRadioButton("Any");

        ButtonGroup group = new ButtonGroup();
        group.add(rbHome);
        group.add(rbOutside);
        group.add(rbAnyPlace);

        rbAnyPlace.setSelected(true);

        p.add(rbHome);
        p.add(rbOutside);
        p.add(rbAnyPlace);

        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, p.getPreferredSize().height));

        return p;
    }


    private JPanel createCategoryPanel() {
        JPanel p = titledPanel("Category *");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        cbCategory = new JComboBox<>(new String[]{
                "Select...",
                "Main dish",
                "Dessert",
                "Snack",
                "Drink",
                "Any"
        });

        cbCategory.setMaximumSize(new Dimension(Integer.MAX_VALUE, cbCategory.getPreferredSize().height));
        p.add(cbCategory);

        JLabel hint = hintLabel("Required: choose a category (or Any).");
        p.add(Box.createVerticalStrut(6));
        p.add(hint);

        return p;
    }

    private JPanel createTimePanel() {
        JPanel p = titledPanel("Max preparation time");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // Spinner: 5..180 step 5, default 30
        SpinnerNumberModel model = new SpinnerNumberModel(30, 5, 180, 5);
        spMaxPrepMinutes = new JSpinner(model);

        // Make spinner look nicer in BoxLayout
        spMaxPrepMinutes.setMaximumSize(new Dimension(120, spMaxPrepMinutes.getPreferredSize().height));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.add(new JLabel("Minutes:"));
        row.add(spMaxPrepMinutes);

        p.add(row);
        p.add(Box.createVerticalStrut(6));
        p.add(hintLabel("Tip: Increase the max if you have no time preference."));

        return p;
    }

    private JPanel createBudgetPanel() {
        JPanel p = titledPanel("Budget level (allowed)");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        cbBudgetLow = new JCheckBox("Low");
        cbBudgetMed = new JCheckBox("Medium");
        cbBudgetHigh = new JCheckBox("High");

        p.add(checkboxWithHint(cbBudgetLow, "(< 150 TL)"));
        p.add(checkboxWithHint(cbBudgetMed, "(150–350 TL)"));
        p.add(checkboxWithHint(cbBudgetHigh, "(> 350 TL)"));

        p.add(Box.createVerticalStrut(6));
        p.add(hintLabel("Tip: If none selected, all budget levels are allowed."));

        return p;
    }

    private JPanel createCaloriesPanel() {
        JPanel p = titledPanel("Calories (allowed)");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        cbCalLow = new JCheckBox("Low");
        cbCalMed = new JCheckBox("Medium");
        cbCalHigh = new JCheckBox("High");

        p.add(checkboxWithHint(cbCalLow, "(< 400 kcal)"));
        p.add(checkboxWithHint(cbCalMed, "(400–700 kcal)"));
        p.add(checkboxWithHint(cbCalHigh, "(> 700 kcal)"));

        p.add(Box.createVerticalStrut(6));
        p.add(hintLabel("Tip: If none selected, all calorie levels are allowed."));

        return p;
    }

    private JPanel createDietaryPanel() {
        JPanel p = titledPanel("Dietary restrictions");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        cbVegan = new JCheckBox("Vegan");
        cbVegetarian = new JCheckBox("Vegetarian");
        cbGlutenFree = new JCheckBox("Gluten-free");

        p.add(cbVegan);
        p.add(cbVegetarian);
        p.add(cbGlutenFree);

        p.add(Box.createVerticalStrut(6));
        p.add(hintLabel("Tip: If none selected, no dietary restriction is applied."));

        return p;
    }

    private JPanel createTastePanel() {
        JPanel p = titledPanel("Taste profile");
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        cbSpicy = new JCheckBox("Spicy");
        cbSweet = new JCheckBox("Sweet");
        cbSour = new JCheckBox("Sour");
        cbSavory = new JCheckBox("Savory");

        p.add(cbSpicy);
        p.add(cbSweet);
        p.add(cbSour);
        p.add(cbSavory);

        p.add(Box.createVerticalStrut(6));
        p.add(hintLabel("Tip: If none selected, taste preference is ignored."));

        return p;
    }

    // ---------------- RIGHT: Results ----------------
    private JComponent createResultsPanel() {
        JPanel right = new JPanel(new BorderLayout(10, 10));
        right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Suggested Meals");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        right.add(title, BorderLayout.NORTH);

        resultsModel = new DefaultListModel<>();
        resultsList = new JList<>(resultsModel);

        resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(resultsList);
        right.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton btnAddFav = new JButton("Add to Favorites");
        btnAddFav.addActionListener(e -> onAddToFavorites());
        bottom.add(btnAddFav);

        right.add(bottom, BorderLayout.SOUTH);

        return right;
    }

    // ---------------- Actions (temporary placeholders) ----------------
    private void onSuggestMeals() {
        // 1) Validate category selection
        if (cbCategory.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a category (or choose Any).",
                    "Missing category",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 2) Read UI selections
        Place selectedPlace = getSelectedPlace();
        Category selectedCategory = getSelectedCategory();
        int maxMinutes = (Integer) spMaxPrepMinutes.getValue();

        EnumSet<BudgetLevel> allowedBudgets = getAllowedBudgetLevels();     // empty => any
        EnumSet<CalorieLevel> allowedCalories = getAllowedCalorieLevels();  // empty => any
        EnumSet<DietTag> requiredDietTags = getRequiredDietTags();          // empty => no restriction
        EnumSet<TasteTag> desiredTasteTags = getDesiredTasteTags();         // empty => ignore

        // 3) Filter meals
        List<Meal> allMeals = MealData.getDefaultMeals();

        resultsModel.clear();

        for (Meal meal : allMeals) {
            if (!matchesPlace(meal, selectedPlace)) continue;
            if (!matchesCategory(meal, selectedCategory)) continue;
            if (meal.getPrepMinutes() > maxMinutes) continue;

            if (!allowedBudgets.isEmpty() && !allowedBudgets.contains(meal.getBudgetLevel())) continue;
            if (!allowedCalories.isEmpty() && !allowedCalories.contains(meal.getCalorieLevel())) continue;

            // Dietary: if user selected restrictions, meal must support ALL of them
            if (!requiredDietTags.isEmpty() && !meal.getDietTags().containsAll(requiredDietTags)) continue;

            // Taste: if user selected taste tags, meal should match AT LEAST ONE
            if (!desiredTasteTags.isEmpty() && !hasAnyTaste(meal, desiredTasteTags)) continue;

            resultsModel.addElement(meal);
        }

        if (resultsModel.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No meals matched your preferences.\nTry relaxing some filters.",
                    "No results",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void onAddToFavorites() {
        Meal selected = resultsList.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a meal from the list first.",
                    "No selection",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Added to favorites (placeholder):\n" + selected,
                "Favorites",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ---------------- Small UI helpers ----------------
    private JPanel titledPanel(String title) {
        JPanel p = new JPanel();
        p.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                title,
                TitledBorder.LEFT,
                TitledBorder.TOP
        ));
        return p;
    }

    private JLabel hintLabel(String text) {
        JLabel hint = new JLabel(text);
        hint.setForeground(Color.GRAY);
        hint.setFont(hint.getFont().deriveFont(11f));
        hint.setAlignmentX(Component.LEFT_ALIGNMENT);
        return hint;
    }

    private JPanel checkboxWithHint(JCheckBox cb, String hintText) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel hint = new JLabel(hintText);
        hint.setForeground(Color.GRAY);
        hint.setFont(hint.getFont().deriveFont(11f));

        row.add(cb);
        row.add(hint);
        return row;
    }

    private Place getSelectedPlace() {
        if (rbHome.isSelected()) return Place.HOME;
        if (rbOutside.isSelected()) return Place.OUTSIDE;
        return Place.ANY;
    }

    private Category getSelectedCategory() {
        String s = (String) cbCategory.getSelectedItem();
        if (s == null) return Category.ANY;

        return switch (s) {
            case "Main dish" -> Category.MAIN_DISH;
            case "Dessert" -> Category.DESSERT;
            case "Snack" -> Category.SNACK;
            case "Drink" -> Category.DRINK;
            case "Any" -> Category.ANY;
            default -> Category.ANY;
        };
    }

    private EnumSet<BudgetLevel> getAllowedBudgetLevels() {
        EnumSet<BudgetLevel> set = EnumSet.noneOf(BudgetLevel.class);
        if (cbBudgetLow.isSelected()) set.add(BudgetLevel.LOW);
        if (cbBudgetMed.isSelected()) set.add(BudgetLevel.MEDIUM);
        if (cbBudgetHigh.isSelected()) set.add(BudgetLevel.HIGH);
        return set; // empty => any
    }

    private EnumSet<CalorieLevel> getAllowedCalorieLevels() {
        EnumSet<CalorieLevel> set = EnumSet.noneOf(CalorieLevel.class);
        if (cbCalLow.isSelected()) set.add(CalorieLevel.LOW);
        if (cbCalMed.isSelected()) set.add(CalorieLevel.MEDIUM);
        if (cbCalHigh.isSelected()) set.add(CalorieLevel.HIGH);
        return set; // empty => any
    }

    private EnumSet<DietTag> getRequiredDietTags() {
        EnumSet<DietTag> set = EnumSet.noneOf(DietTag.class);
        if (cbVegan.isSelected()) set.add(DietTag.VEGAN);
        if (cbVegetarian.isSelected()) set.add(DietTag.VEGETARIAN);
        if (cbGlutenFree.isSelected()) set.add(DietTag.GLUTEN_FREE);
        return set; // empty => no restriction
    }

    private EnumSet<TasteTag> getDesiredTasteTags() {
        EnumSet<TasteTag> set = EnumSet.noneOf(TasteTag.class);
        if (cbSpicy.isSelected()) set.add(TasteTag.SPICY);
        if (cbSweet.isSelected()) set.add(TasteTag.SWEET);
        if (cbSour.isSelected()) set.add(TasteTag.SOUR);
        if (cbSavory.isSelected()) set.add(TasteTag.SAVORY);
        return set; // empty => ignore
    }

    private boolean matchesPlace(Meal meal, Place selectedPlace) {
        if (selectedPlace == Place.ANY) return true;
        // meal.place == ANY means it works for both
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
