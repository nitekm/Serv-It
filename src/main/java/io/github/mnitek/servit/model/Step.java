package io.github.mnitek.servit.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "steps")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Przepis musi składać się z co najmniej jednego kroku")
    private String description;
    @ManyToOne
    private Recipe recipe;
}
