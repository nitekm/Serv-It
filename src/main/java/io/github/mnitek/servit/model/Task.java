package io.github.mnitek.servit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String content;

    public Task ingredientToTask(Ingredient ingredient) {
        content = ingredient.getName();
        return new Task(content);
    }
}
