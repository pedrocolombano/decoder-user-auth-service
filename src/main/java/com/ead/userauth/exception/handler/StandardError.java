package com.ead.userauth.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1678613687288142170L;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private Integer statusCode;
    private String path;

}
