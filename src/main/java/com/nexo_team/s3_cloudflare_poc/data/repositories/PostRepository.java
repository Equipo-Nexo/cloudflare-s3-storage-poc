package com.nexo_team.s3_cloudflare_poc.data.repositories;

import com.nexo_team.s3_cloudflare_poc.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
