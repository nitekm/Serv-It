package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Recipe;

import java.util.List;
import java.util.Optional;


public interface RecipeRepository {

    List<Recipe> findAll();

    Optional<Recipe> findById(int id);

    Recipe save(Recipe recipe);

    boolean existsById(int id);

    void delete(Recipe recipe);
}
