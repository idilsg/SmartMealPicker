# 🍽️ Smart Meal Picker

Smart Meal Picker is a Java desktop application developed for the SE360 Software Engineering course.
The application helps users decide what to eat based on multiple preferences such as budget, calorie level, preparation time, dietary restrictions, and taste profile.

The project focuses on applying core Java concepts, object-oriented design, and JDBC-based data access in a simple and practical desktop application.

---

## ✨ Features

- Filter meals by eating place (home, outside, or any)
- Select meal category (main dish, snack, dessert, drink)
- Set a maximum preparation time
- Filter by budget level (low, medium, high)
- Filter by calorie level (low, medium, high)
- Support dietary preferences (vegan, vegetarian, gluten-free)
- Select taste preferences (spicy, sweet, sour, savory)
- View suggested meals in a table format
- Add meals to a favorites list
- Prevent duplicate favorites with user feedback
- Save and load favorite meals using Java serialization
- Meal data accessed via JDBC
- Automatic in-memory fallback dataset if the database is not available

---

## 🧱 Project Structure

```
src/
└── main/
    └── java/
        ├── ui/            # Java Swing UI (MainFrame)
        ├── model/         # Domain models and enums (Meal, Category, etc.)
        ├── data/          # JDBC access and fallback dataset
        ├── service/       # Recommendation logic
        └── persistence/   # Favorites serialization
```

---

## 🗄️ Database & Data Handling

- The application uses SQLite as a lightweight, file-based database.
- Data access is implemented using JDBC.
- The database schema is created programmatically if it does not exist.
- Initial sample data (seed data) is inserted automatically when the database is empty.
- Enum values (category, budget level, calorie level, etc.) are stored as strings.
- If the database cannot be accessed, the system automatically switches to an in-memory dataset to keep the application functional during demos.

---

## 🛠️ Technologies Used

- Java (JDK 19)
- Java Swing
- JDBC
- SQLite
- JUnit 5 (for unit testing)
- IntelliJ IDEA
- Maven

---

## ▶️ How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure dependencies are resolved (Maven).
3. Run the `MainFrame` class.
4. The database file will be created automatically if it does not exist.
5. Select your preferences and click **Suggest Meals**.

---

## 🧪 Testing

- Unit tests were implemented using JUnit 5 to verify the core recommendation logic.
- Test scenarios include:
  - Budget filtering
  - Dietary restrictions
  - Taste preferences
  - Preparation time constraints
  - Eating place filtering
- Tests focus on validating business logic independently from the UI.

## 📌 Notes

- The project prioritizes clarity, readability, and maintainability.
- The architecture is intentionally kept simple to match the scope of the course.
- The application is designed to run without external setup, making it suitable for in-class demonstrations.
