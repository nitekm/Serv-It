package io.github.mnitek.servit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Przepis musi składać się z co najmniej jednego składnika")
    private String name;
    private boolean planned;
    @ManyToOne
    private Recipe recipe;
}
