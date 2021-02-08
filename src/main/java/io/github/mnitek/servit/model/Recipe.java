package io.github.mnitek.servit.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String timeToPrepare;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Step> steps = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();
    private boolean planned;

    public Recipe() {
        steps.add(new Step());
        ingredients.add(new Ingredient());
    }
}
