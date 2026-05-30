package com.nexo_team.s3_cloudflare_poc.services;

import com.nexo_team.s3_cloudflare_poc.controllers.requests.CreatePostRequest;
import com.nexo_team.s3_cloudflare_poc.controllers.responses.PostResponse;
import com.nexo_team.s3_cloudflare_poc.data.models.Post;
import com.nexo_team.s3_cloudflare_poc.data.repositories.PostRepository;
import com.nexo_team.s3_cloudflare_poc.services.interfaces.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;
    private final ImageService imageService;

    public PostService(PostRepository postRepository, ImageService imageService) {
        this.postRepository = postRepository;
        this.imageService = imageService;
    }

    public void createPost(CreatePostRequest createPostRequest, MultipartFile file) {
        try {
            String imageUrl = this.imageService.uploadImage(file);
            this.postRepository.save(
                    new Post(createPostRequest.title(), imageUrl)
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
