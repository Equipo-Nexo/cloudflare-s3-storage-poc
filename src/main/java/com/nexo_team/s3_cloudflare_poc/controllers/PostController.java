package com.nexo_team.s3_cloudflare_poc.controllers;

import com.nexo_team.s3_cloudflare_poc.controllers.requests.CreatePostRequest;
import com.nexo_team.s3_cloudflare_poc.controllers.responses.PostResponse;
import com.nexo_team.s3_cloudflare_poc.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        this.postService.createPost(createPostRequest);
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }
}
