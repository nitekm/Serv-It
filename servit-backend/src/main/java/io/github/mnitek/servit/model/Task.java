package io.github.mnitek.servit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private String content;
    private long project_id = 2261571645L;

    public Task ingredientToTask(Ingredient ingredient) {
        content = ingredient.getName();
        return new Task(content, project_id);
    }
}
