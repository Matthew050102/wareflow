package com.matthew050102.wareflow.auth.dto;

import lombok.NonNull;

public record RegisterRequest(
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String username,
        @NonNull String email,
        @NonNull String password
) {}
