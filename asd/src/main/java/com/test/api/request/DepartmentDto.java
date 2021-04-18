package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class DepartmentDto {

    private String name;
    private Set<String> cityEntities;

}
