package java.controller;

import java.util.HashMap;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;
    private static ScreenController instance;
    
    public static ScreenController getInstance(Scene main) {
        if(instance == null)
            instance = new ScreenController(main);
        return instance;
    }

    private ScreenController(Scene main) {
        this.main = main;
    }

    protected void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}