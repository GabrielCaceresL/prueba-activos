package com.test.services;

import com.test.api.request.AssetAreaDto;
import com.test.api.request.AssetDto;
import com.test.api.request.AssetUserDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.AssetEntity;

import java.util.List;
import java.util.Optional;

public interface IAssetService {

    Optional<GeneralResponse<AssetUserDto>> save(AssetUserDto assetUserDto);

    Optional<GeneralResponse<AssetAreaDto>> save(AssetAreaDto assetAreaDto);

    Optional<GeneralResponse<AssetUserDto>> update(AssetUserDto assetUserDto);

    Optional<GeneralResponse<AssetAreaDto>> update(AssetAreaDto assetAreaDto);

    GeneralResponse<List<AssetEntity>> get(AssetDto assetDto);

}
