package persistence;

import model.Meal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {

    private static final String FILE_NAME = "favorites.ser";

    public static List<Meal> loadFavorites() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                List<Meal> favorites = (List<Meal>) obj;
                return favorites;
            }
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveFavorites(List<Meal> favorites) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(favorites);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
