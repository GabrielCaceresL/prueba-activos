package com.test.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetAreaDto extends AssetDto{

    @NotBlank(message = "Department name required")
    private String nameDepartment;

}
