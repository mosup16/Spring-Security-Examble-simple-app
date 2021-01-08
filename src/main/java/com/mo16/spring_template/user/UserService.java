package com.mo16.spring_template.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    User save(User user);

    void deleteById(Long id);

    void delete(User user);

}
