package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAll();

    Optional<Recipe> findById(int id);

    Recipe save(Recipe recipe);

    boolean existsById(int id);

    void delete(Recipe recipe);

    @Query("select distinct r from Recipe r where r.planned=true")
    List<Recipe> findAllPlanned();
}
