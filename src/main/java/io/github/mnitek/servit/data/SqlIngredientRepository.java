package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlIngredientRepository extends IngredientRepository, JpaRepository<Ingredient, Integer> {

    @Override
    @Query("select distinct i from Ingredient i where i.planned=true ")
    List<Ingredient> findAllPlanned();
}
