package com.test.api.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse<T> {

    private T body;
    private String message;

}
