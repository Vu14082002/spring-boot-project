package vn.edu.iuh.fit.myblog.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.edu.iuh.fit.myblog.payload.ErrorDetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    // specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest
    ) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetail> handlerAccessDeniedException(AccessDeniedException  exception,
                                                                        WebRequest webRequest
    ) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetail> handlerBlogApiException(ResourceNotFoundException exception,
                                                               WebRequest webRequest
    ) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    // global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handlerGlobalException(Exception exception,
                                                              WebRequest webRequest
    ) {
        ErrorDetail errorDetail = new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//    VALID DATA

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
