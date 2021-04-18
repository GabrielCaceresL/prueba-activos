package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    protected LocalDate purchaseDate;

}
