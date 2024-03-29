package vn.edu.iuh.fit.myblog.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String detail;
}
