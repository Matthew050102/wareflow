package com.matthew050102.wareflow.auth.dto;

import lombok.NonNull;

public record LoginRequest(
        @NonNull String username,
        @NonNull String password
) {}
