package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AssetSearchDto {

    private String type;
    private String serial;
    private LocalDate purchaseDate;

}
