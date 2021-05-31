package main.model.dao;

import java.util.ArrayList;

public class CategoryDao {
    public final ArrayList<String> categories;

    private static CategoryDao instance;

    private CategoryDao() {
        categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Clothes");
        categories.add("Shopping");
    }

    public static CategoryDao getInstance() {
        if(instance == null)
            instance = new CategoryDao();
        return instance;
    }
}