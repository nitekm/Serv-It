package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> findById(int id);

    @Query("select distinct i from Ingredient i where i.planned=true ")
    List<Ingredient> findAllPlanned();

    @Query("select distinct i from Ingredient i where i.planned=true order by i.name")
    List<Ingredient> findAllPlannedOrderByName();
}
