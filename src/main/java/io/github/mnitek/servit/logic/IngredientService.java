package io.github.mnitek.servit.logic;

import io.github.mnitek.servit.data.IngredientRepository;
import io.github.mnitek.servit.model.Ingredient;
import io.github.mnitek.servit.model.Task;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class IngredientService {

    private WebClient webClient;
    private IngredientRepository ingredientRepository;


    public IngredientService(final WebClient webClient, IngredientRepository ingredientRepository) {
        this.webClient = webClient;
        this.ingredientRepository = ingredientRepository;
    }

    public Mono<Task> createIngredientTask(Task task, Ingredient ingredient) {
        var todo = task.ingredientToTask(ingredient);
        return webClient.post()
                .body(Mono.just(todo), Task.class)
                .retrieve()
                .bodyToMono(Task.class);
    }

    public Flux<Task> getAllTasks() {
        return webClient.get()
                .uri("/projects")
                .retrieve()
                .bodyToFlux(Task.class);
    }

    public List<Ingredient> getAllPlannedIngredients() {
        return ingredientRepository.findAllPlanned();
    }

}
