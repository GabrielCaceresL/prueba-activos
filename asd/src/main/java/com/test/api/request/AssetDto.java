package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public abstract class AssetDto {

    @NotBlank(message = "Name is required")
    protected String name;
    protected String description;

    @NotBlank(message = "Type is required")
    protected String type;

    @NotBlank(message = "Serial is required")
    protected String serial;

    protected String numInventory;
    protected String weightKg;
    protected String highCm;
    protected String widthCm;
    protected String longAssetCm;
    protected Double purchaseValue;

    @NotNull(message = "Purchase date is required")
    protected LocalDate purchaseDate;

}
