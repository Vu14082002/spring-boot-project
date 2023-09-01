package com.edu.iuh.fit.springboot.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private LocalDateTime localDateTime;
    private String message;
    private String path;
    private String encode;

}
