package io.github.mnitek.servit.model;

import lombok.Data;

@Data
public class Ingredient {
    private int id;
    private String name;
    private boolean planned;
}
