package com.isatoltar.trelloclone.shared.exception;

import com.isatoltar.trelloclone.shared.exception.payload.CustomValidationErrorResponse;
import com.isatoltar.trelloclone.shared.exception.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomValidationErrorResponse> processValidationError(MethodArgumentNotValidException ex,
                                                                                HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<CustomValidationErrorResponse.ValidationError> customFieldErrors = new ArrayList<>();

        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            customFieldErrors.add(new CustomValidationErrorResponse.ValidationError(field, message));
        }

        CustomValidationErrorResponse customValidationErrorResponse = new CustomValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Request Contains inappropriate parameters",
                request.getRequestURI()
        );
        customValidationErrorResponse.setErrors(customFieldErrors);

        return new ResponseEntity<>(customValidationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> processResourceNotFoundException(ResourceNotFoundException ex,
                                                                          HttpServletRequest request) {

        HttpStatus resourceFoundStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(
                new ErrorResponse(resourceFoundStatus.value(), ex.getMessage(), request.getRequestURI()),
                resourceFoundStatus
        );
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> processResourceAlreadyExistsException(ResourceAlreadyExistsException ex,
                                                                               HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> processBadRequestException(BadRequestException ex,
                                                                    HttpServletRequest request) {

        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                new ErrorResponse(badRequestStatus.value(), ex.getMessage(), request.getRequestURI()),
                badRequestStatus
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> processConflictException(ConflictException ex,
                                                                  HttpServletRequest request) {

        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI()),
                HttpStatus.CONFLICT
        );
    }
}
