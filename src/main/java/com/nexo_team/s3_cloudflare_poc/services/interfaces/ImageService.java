package com.nexo_team.s3_cloudflare_poc.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file);
}
