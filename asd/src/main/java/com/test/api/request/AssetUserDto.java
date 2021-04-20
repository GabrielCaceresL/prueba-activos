package com.test.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AssetUserDto extends AssetDto {

    @NotBlank(message = "Document User is required")
    private String documentUser;


}
