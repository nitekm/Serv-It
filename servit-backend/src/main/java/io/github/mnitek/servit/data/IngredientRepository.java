package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IngredientRepository {

    List<Ingredient> findAllPlanned();
}
