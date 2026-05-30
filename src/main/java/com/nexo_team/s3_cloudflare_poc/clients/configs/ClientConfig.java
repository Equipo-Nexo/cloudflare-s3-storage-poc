package com.nexo_team.s3_cloudflare_poc.clients.configs;

import com.nexo_team.s3_cloudflare_poc.clients.properties.R2StorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
@EnableConfigurationProperties(R2StorageProperties.class)
public class ClientConfig {

    private final R2StorageProperties r2StorageProperties;

    public ClientConfig(R2StorageProperties r2StorageProperties) {
        this.r2StorageProperties = r2StorageProperties;
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(r2StorageProperties.uri()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(r2StorageProperties.accessKeyId(), r2StorageProperties.secretAccessKey())
                        )
                )
                .region(Region.of("auto"))
                .build();
    }
}
