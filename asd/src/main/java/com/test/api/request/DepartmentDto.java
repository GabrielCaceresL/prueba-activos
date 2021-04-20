package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "Name is required")
    private String name;
    private Set<String> cityEntities;

}
