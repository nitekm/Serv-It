package io.github.mnitek.servit;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.model.Recipe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServItApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServItApplication.class, args);
    }

/*
    //Fill recipe table with some data for test purposes and add test user
    @Bean
    public CommandLineRunner dataLoader(RecipeRepository recipeRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                recipeRepository.save(new Recipe("Schabowy"));
                recipeRepository.save(new Recipe("mielony"));
                recipeRepository.save(new Recipe("spaghetii"));
                recipeRepository.save(new Recipe("blablabla"));
            }
        };
    }

 */

}
