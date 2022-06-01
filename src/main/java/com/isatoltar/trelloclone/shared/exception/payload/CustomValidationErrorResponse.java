package com.isatoltar.trelloclone.shared.exception.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class CustomValidationErrorResponse extends ErrorResponse {

    List<ValidationError> errors;

    public CustomValidationErrorResponse(Integer status, String message, String path) {
        super(status, message, path);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ValidationError {
        String field;
        String message;
    }
}
