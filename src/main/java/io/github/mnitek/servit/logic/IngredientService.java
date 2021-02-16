package io.github.mnitek.servit.logic;

import io.github.mnitek.servit.data.IngredientRepository;
import io.github.mnitek.servit.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientService {
    @Autowired
    private WebClient webClient;
    private IngredientRepository ingredientRepository;


    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Flux<Ingredient> getAllIngredients() {
        return webClient.get()
                .uri("/tasks/view_all.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Ingredient.class);
    }

    public Mono<Ingredient> createIngredientsTasks(Ingredient ingredient) {
        return webClient.post()
                .uri("tasks/create.json")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(ingredient), Ingredient.class)
                .retrieve()
                .bodyToMono(Ingredient.class);
    }
}
