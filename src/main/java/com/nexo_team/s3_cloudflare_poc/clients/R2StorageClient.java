package com.nexo_team.s3_cloudflare_poc.clients;

import com.nexo_team.s3_cloudflare_poc.clients.properties.R2StorageProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Component
public class R2StorageClient {

    private final R2StorageProperties r2StorageProperties;
    private final S3Client s3Client;
    private final String environment;

    public R2StorageClient(
            R2StorageProperties r2StorageProperties,
            S3Client s3Client,
            @Value("${spring.profiles.active}")
            String environment
    ) {
        this.r2StorageProperties = r2StorageProperties;
        this.s3Client = s3Client;
        this.environment = environment;
    }

    public String upload(MultipartFile file) throws IOException {
        String key = environment + "/" + UUID.randomUUID();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(r2StorageProperties.bucketName())
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );

        return r2StorageProperties.publicUri() + "/" + key;
    }
}
