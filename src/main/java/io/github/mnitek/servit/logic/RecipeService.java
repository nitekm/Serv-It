package io.github.mnitek.servit.logic;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RecipeService {
    private RecipeRepository recipeRepo;

    public RecipeService(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Transactional
    public void togglePlanned(int id) {
        if (!recipeRepo.existsById(id)) {
            log.warn("Not planned");
            throw new IllegalArgumentException("Recipe with given id does not exist");
        }
        recipeRepo.findById(id).ifPresent(recipe -> recipe.setPlanned(!recipe.isPlanned()));
        recipeRepo.findById(id).ifPresent(Recipe::toggleIngredients);
        log.info("Planned");
    }

    public void deleteRecipe(int id) {
        Recipe recipe = recipeRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Recipe with given id does not exists!"));
        recipeRepo.delete(recipe);
        log.info("Recipe " + recipe + " deleted");
    }
}
