package vn.edu.iuh.fit.myblog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private  String message;


    public BlogApiException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BlogApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
}
