package com.esg.compliance.api.dto.register;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String username,

        @NotBlank
        String password
) {}