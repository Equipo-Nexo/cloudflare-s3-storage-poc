package com.nexo_team.s3_cloudflare_poc.controllers.responses;

import java.util.UUID;

public record PostResponse(
        UUID id,
        String title,
        String imageUrl
) {
}
