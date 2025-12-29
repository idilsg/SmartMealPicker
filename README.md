# Smart Meal Picker 🍽️

Smart Meal Picker is a Java desktop application developed for the SE360 Software Engineering course.  
The application helps users decide what to eat based on multiple preferences such as budget, calorie level, preparation time, dietary restrictions, and taste profile.

The project demonstrates the use of core Java concepts, Java Swing for graphical user interface development, and JDBC for database integration.

---

## ✨ Features

- Filter meals by eating place (home, outside, or any)
- Select meal category (main dish, snack, dessert, drink)
- Set a maximum preparation time
- Filter by budget level (low, medium, high)
- Filter by calorie level (low, medium, high)
- Support dietary restrictions (vegan, vegetarian, gluten-free)
- Select taste preferences (spicy, sweet, sour, savory)
- View suggested meals in a table format
- Add meals to a favorites list
- Save and load favorite meals using Java serialization
- Meal data stored in a MySQL database and accessed via JDBC
- In-memory fallback dataset if database is not available
- Unit tests implemented using JUnit 5

---

## 🧱 Project Structure

```
src/
├── ui/            # Java Swing UI (MainFrame)
├── model/         # Domain models and enums (Meal, Category, etc.)
├── data/          # Data access (JDBC and fallback dataset)
├── service/       # Recommendation logic
├── persistence/   # Favorites serialization
└── test/          # JUnit tests
```

---

## 🗄️ Database

- MySQL is used to store meal data.
- Data is accessed using JDBC.
- Enum values are stored as strings in the database.
- If the database is not available, the application automatically falls back to an in-memory dataset to ensure the program runs correctly.

---

## 🛠️ Technologies Used

- Java
- Java Swing
- JDBC (MySQL)
- MySQL Workbench
- JUnit 5
- IntelliJ IDEA

---

## ▶️ How to Run

1. Make sure MySQL Server is running.
2. Create the database and `meals` table in MySQL.
3. Update database connection details in `DbConfig.java`.
4. Run the `MainFrame` class.
5. Select preferences and click **Suggest Meals**.

---

## 🧪 Testing

- Core recommendation logic is tested using JUnit 5.
- Tests verify filtering by budget, dietary restrictions, taste preferences, preparation time, and eating place.



