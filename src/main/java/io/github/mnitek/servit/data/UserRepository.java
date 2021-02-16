package io.github.mnitek.servit.data;

import io.github.mnitek.servit.security.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
        User findByUsername(String username);
}
