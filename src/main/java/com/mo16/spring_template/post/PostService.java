package com.mo16.spring_template.post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post save(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAll();

    List<Post> findAllByUserId(Long id);

    void deleteById(Long id);

    void delete(Post post);
}
