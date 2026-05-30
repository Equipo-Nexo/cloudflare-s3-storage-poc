package com.nexo_team.s3_cloudflare_poc.controllers;

import com.nexo_team.s3_cloudflare_poc.controllers.requests.CreatePostRequest;
import com.nexo_team.s3_cloudflare_poc.controllers.responses.PostResponse;
import com.nexo_team.s3_cloudflare_poc.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(
        @RequestPart("payload") CreatePostRequest createPostRequest,
        @RequestPart("image") MultipartFile file
    ) {
        log.info("Received request to create post with title: {}", createPostRequest.title());
        this.postService.createPost(createPostRequest, file);
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        log.info("Received request to get all posts");
        List<PostResponse> response = postService.getPosts();
        log.info("Returning {} posts", response.size());
        return response;
    }
}
