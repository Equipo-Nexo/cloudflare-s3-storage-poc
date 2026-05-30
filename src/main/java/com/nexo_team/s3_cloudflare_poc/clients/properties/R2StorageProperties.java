package com.nexo_team.s3_cloudflare_poc.clients.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clients.cloudflare.r2-storage")
public record R2StorageProperties(
        String accessKeyId,
        String secretAccessKey,
        String bucketName,
        String uri,
        String publicUri
) {
}
