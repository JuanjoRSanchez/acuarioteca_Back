package com.acuarioteca.acuarioteca.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private HttpStatus status;

    private String message;

    private Date dateTime;
}
