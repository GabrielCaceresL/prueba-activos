package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public abstract class AssetDto {


    protected String name;
    protected String description;
    protected String type;
    protected String serial;
    protected String numInventory;
    protected String weightKg;
    protected String highCm;
    protected String widthCm;
    protected String longAssetCm;
    protected String purchaseValue;

    @NotNull(message = "The purchase is required")
    protected LocalDate purchaseDate;

}
