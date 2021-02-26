package io.github.mnitek.servit.model;

import io.github.mnitek.servit.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Pole Nazwa nie może być puste")
    private String name;
    @Pattern(regexp = "^[0-9]:[0-5][0-9]$", message = "Czas przygotowania musi być w formacie H:mm")
    private String timeToPrepare;
    @NotEmpty(message = "Przepis musi składać się z co najmniej jednego kroku")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
    @NotEmpty(message = "Przepis musi składać się z co najmniej jednego kroku")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps = new ArrayList<>();
    private boolean planned;
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;

    public Recipe() {
        steps.add(new Step());
        ingredients.add(new Ingredient());
    }

    //test
    public Recipe(String name) {
        this.name = name;
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    public void toggleIngredients() {
        for (Ingredient i:ingredients) {
            i.setPlanned(!i.isPlanned());
        }
    }
}
