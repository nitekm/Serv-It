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


    //Fill recipe table with some data for test purposes
    @Bean
    public CommandLineRunner dataLoader(RecipeRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repository.save(new Recipe("Schabowy"));
                repository.save(new Recipe("mielony"));
                repository.save(new Recipe("spaghetii"));
                repository.save(new Recipe("blablabla"));
            }
        };
    }


}
