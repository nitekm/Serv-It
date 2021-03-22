package io.github.mnitek.servit.logic;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RecipeService {
    private RecipeRepository recipeRepo;

    public RecipeService(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }


    public void togglePlanned(int id) {
        if (!recipeRepo.existsById(id)) {
            throw new IllegalArgumentException("Recipe with given id does not exist");
        }
        recipeRepo.findById(id).ifPresent(recipe -> {
            recipe.toggleIngredients();
            recipe.setPlanned(!recipe.isPlanned());
            log.info("[" + recipe.getName() + "] planned");
        });

    }

    public void deleteRecipe(int id) {
        Recipe recipe = recipeRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Recipe with given id does not exists!"));
        recipeRepo.delete(recipe);
        log.info("Recipe [" + recipe.getName() + "] deleted");
    }
}
