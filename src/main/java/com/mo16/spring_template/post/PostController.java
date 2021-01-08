package com.mo16.spring_template.post;

import com.mo16.spring_template.ExceptionHandeling.PostNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    @PreAuthorize("hasAnyAuthority('READ')")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/post/user/{userId}")
    @PreAuthorize("hasAnyAuthority('READ')")
    public List<Post> getAllPosts(@PathVariable Long userId) {
        return postService.findAllByUserId(userId);
    }
    @GetMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('READ')")
    public Post getPostById(@PathVariable Long postId) {
        return postService.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post id ( " + postId + " ) Not Found"));
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public Post savePost(@RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public Post updatePost(@RequestBody Post post, @PathVariable Long postId) {
        post.setId(postId);
        return postService.save(post);
    }


    @DeleteMapping("/post/{postId}")
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public void deletePostById(@PathVariable Long postId) {
        postService.deleteById(postId);
    }

    @DeleteMapping("/post")
    @PreAuthorize("hasAnyAuthority('WRITE')")
    public void deletePost(@RequestBody Post post) {
        postService.delete(post);
    }


}
