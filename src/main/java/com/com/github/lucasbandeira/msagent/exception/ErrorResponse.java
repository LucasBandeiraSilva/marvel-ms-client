package com.com.github.lucasbandeira.msagent.exception;

import java.util.List;

public record ErrorResponse(int status, String message, List<ApiFieldErrors>errors) {
}
