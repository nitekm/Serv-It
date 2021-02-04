package io.github.mnitek.servit.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Recipe {
    private int id;
    private String name;
    private String timeToPrepare;
    private List<Step> steps = new ArrayList<>();
    private List<Ingredient> ingredients = new ArrayList<>();
    private boolean planned;

    public Recipe() {
        steps.add(new Step());
        ingredients.add(new Ingredient());
    }
}
