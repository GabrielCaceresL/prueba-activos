package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CityDto {

    @NotBlank(message = "Name is required")
    private String name;

}
