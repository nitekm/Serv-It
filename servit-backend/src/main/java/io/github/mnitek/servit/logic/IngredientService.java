package io.github.mnitek.servit.logic;

import io.github.mnitek.servit.data.IngredientRepository;
import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class IngredientService {

    private RestTemplate restTemplate;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private String url = "https://api.todoist.com/rest/v1/tasks";
    private String token = "Bearer 518f2340684199ab4d745ca2fbb7a2067079fccb";


    public IngredientService(final RestTemplate restTemplate, final IngredientRepository ingredientRepository, final RecipeRepository recipeRepository) {
        this.restTemplate = restTemplate;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public void createIngredientsTasks() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, token);

        ingredientRepository.findAllPlanned()
                .forEach(ingredient -> {
                    Task task = new Task();
                    task.ingredientToTask(ingredient);
                    log.info(task + " created");
                    HttpEntity<Task> request = new HttpEntity<>(task, headers);
                    restTemplate.postForEntity(url, request, Task.class);
                });
        recipeRepository.findAll().forEach(recipe -> recipe.setPlanned(false));
    }

    public void togglePlanned(int id) {
        if (!ingredientRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Ingredient with given id not found!");
        }
        ingredientRepository.findById(id)
                .ifPresent(ingredient -> {
                    ingredient.setPlanned(false);
                    ingredientRepository.save(ingredient);
                });

    }

    public List<Ingredient> getAllPlannedIngredients() {
        return ingredientRepository.findAllPlannedOrderByName();
    }

}
