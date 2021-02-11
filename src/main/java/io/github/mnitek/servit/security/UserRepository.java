package io.github.mnitek.servit.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
