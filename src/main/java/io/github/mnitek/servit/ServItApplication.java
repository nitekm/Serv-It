package io.github.mnitek.servit;

import io.github.mnitek.servit.data.RecipeRepository;
import io.github.mnitek.servit.data.UserRepository;
import io.github.mnitek.servit.model.Recipe;
import io.github.mnitek.servit.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ServItApplication {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ServItApplication.class, args);
    }


    //Fill recipe table with some data for test purposes and add test user
    @Bean
    public CommandLineRunner dataLoader(RecipeRepository recipeRepository, UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                /*
                recipeRepository.save(new Recipe("Schabowy"));
                recipeRepository.save(new Recipe("mielony"));
                recipeRepository.save(new Recipe("spaghetii"));
                recipeRepository.save(new Recipe("blablabla"));

                 */

                userRepository.save(new User("test", passwordEncoder.encode("test")));
            }
        };
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://beta.dorisapp.com/api/1_0/tasks");
    }


}
