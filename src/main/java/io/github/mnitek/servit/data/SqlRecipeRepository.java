package io.github.mnitek.servit.data;

import io.github.mnitek.servit.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {

//    @Override
//    @Query("select distinct r from Recipe r join fetch r.ingredients, r.steps")
//    List<Recipe> findAll();
}
