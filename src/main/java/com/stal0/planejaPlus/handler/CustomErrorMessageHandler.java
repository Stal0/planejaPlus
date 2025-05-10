package com.stal0.planejaPlus.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorMessageHandler {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String msg;
}
