package com.nexo_team.s3_cloudflare_poc.services;

import com.nexo_team.s3_cloudflare_poc.clients.R2StorageClient;
import com.nexo_team.s3_cloudflare_poc.services.interfaces.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final R2StorageClient r2StorageClient;

    public ImageServiceImpl(R2StorageClient r2StorageClient) {
        this.r2StorageClient = r2StorageClient;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            return this.r2StorageClient.upload(file);
        } catch (Exception e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }
}
