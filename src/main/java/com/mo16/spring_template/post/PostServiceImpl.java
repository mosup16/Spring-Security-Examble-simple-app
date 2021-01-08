package com.mo16.spring_template.post;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public List<Post> findAllByUserId(Long id){
        return postRepo.findAllByUserId(id);
    }

    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public void delete(Post post) {
        postRepo.delete(post);
    }
}
