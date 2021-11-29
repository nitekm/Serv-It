package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

    Ingredient save(Ingredient ingredient);
    List<Ingredient> findAllPlanned();
    List<Ingredient> findAllPlannedOrderByName();
    Optional<Ingredient> findById(int id);
}
