package com.lnreddy.friendlyecommerce.shared.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(String field, String message) {}

