package com.nexo_team.s3_cloudflare_poc.services;

import com.nexo_team.s3_cloudflare_poc.controllers.requests.CreatePostRequest;
import com.nexo_team.s3_cloudflare_poc.controllers.responses.PostResponse;
import com.nexo_team.s3_cloudflare_poc.data.models.Post;
import com.nexo_team.s3_cloudflare_poc.data.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(CreatePostRequest createPostRequest) {
        try {
            this.postRepository.save(
                    new Post(createPostRequest.title(), null)
            );
        } catch (Exception e) {
            log.error("Error creating post", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating post");
        }
    }

    public List<PostResponse> getPosts() {
        try {
            return this.postRepository.findAll().stream()
                    .map(PostService::buildPostResponse)
                    .toList();
        } catch (Exception e) {
            log.error("Error getting posts", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error getting posts");
        }
    }

    private static PostResponse buildPostResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getImageUrl());
    }
}
