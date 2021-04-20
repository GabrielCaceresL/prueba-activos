package com.test.services;

import com.test.api.request.AssetAreaDto;
import com.test.api.request.AssetDto;
import com.test.api.request.AssetUserDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.AssetEntity;
import com.test.entity.DepartmentEntity;
import com.test.mappers.IAssetMapper;
import com.test.repository.AssetEntityRepository;
import com.test.repository.DepartmentEntityRepository;
import com.test.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.test.enums.ConstantProvider.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AssetService implements IAssetService {

    private final AssetEntityRepository assetEntityRepository;
    private final DepartmentEntityRepository departmentEntityRepository;
    private final UserEntityRepository userEntityRepository;

    @Override
    public Optional<GeneralResponse<AssetUserDto>> save(AssetUserDto assetUserDto) {
        AssetEntity assetEntity = IAssetMapper.INSTANCE.toAssetEntity(assetUserDto);
        if (assetEntityRepository.existsBySerial(assetUserDto.getSerial())) {
            throw new RuntimeException(ASSET_EXISTS);
        }
        AssetEntity assetEntityStored = userEntityRepository.findByNumDocument(assetUserDto.getDocumentUser())
                .map(userFromDb -> {
                    assetEntity.setUserEntity(userFromDb);
                    return assetEntityRepository.save(assetEntity);
                }).orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        AssetUserDto assetResponse = IAssetMapper.INSTANCE.toUserDto(assetEntityStored);
        return Optional.of(new GeneralResponse<>(assetResponse, ASSET_SAVE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<AssetAreaDto>> save(AssetAreaDto assetAreaDto) {
        AssetEntity assetEntity = IAssetMapper.INSTANCE.toAssetEntity(assetAreaDto);
        if (assetEntityRepository.existsBySerial(assetAreaDto.getSerial())) {
            throw new RuntimeException(ASSET_EXISTS);
        }
        DepartmentEntity departmentFromDb = departmentEntityRepository
                .findByName(assetAreaDto.getNameDepartment());
        if (departmentFromDb == null) {
            throw new RuntimeException(DEPARTMENT_NOT_FOUND);
        }
        assetEntity.setDepartmentEntity(departmentFromDb);
        AssetEntity assetAreaStored = assetEntityRepository.save(assetEntity);
        AssetAreaDto assetResponse = IAssetMapper.INSTANCE.toAreaDto(assetAreaStored);
        return Optional.ofNullable(new GeneralResponse<>(assetResponse, ASSET_SAVE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<AssetUserDto>> update(AssetUserDto assetUserDto) {
        if (!assetEntityRepository.existsBySerial(assetUserDto.getSerial())) {
            throw new RuntimeException(ASSET_NOT_EXISTS);
        }
        return userEntityRepository
                .findByNumDocument(assetUserDto.getDocumentUser())
                .flatMap(userFromDb ->
                        assetEntityRepository
                                .findBySerial(assetUserDto.getSerial())
                                .map(asset -> {
                                    setFieldsAsset(asset, assetUserDto);
                                    asset.setUserEntity(userFromDb);
                                    asset.setDepartmentEntity(null);
                                    return assetEntityRepository.save(asset);
                                }))
                .map(asset -> {
                    AssetUserDto assetUserDtoResponse = IAssetMapper.INSTANCE.toUserDto(asset);
                    assetUserDtoResponse.setDocumentUser(asset.getUserEntity().getNumDocument());
                    return new GeneralResponse<>(assetUserDtoResponse, ASSET_UPDATE_SUCCESSFUL);
                });
    }

    @Override
    public Optional<GeneralResponse<AssetAreaDto>> update(AssetAreaDto assetAreaDto) {
        if (!assetEntityRepository.existsBySerial(assetAreaDto.getSerial())) {
            throw new RuntimeException(ASSET_NOT_EXISTS);
        }
        return Optional.ofNullable(departmentEntityRepository
                .findByName(assetAreaDto.getNameDepartment()))
                .flatMap(departmentFromDb ->
                        assetEntityRepository
                                .findBySerial(assetAreaDto.getSerial())
                                .map(asset -> {
                                    setFieldsAsset(asset, assetAreaDto);
                                    asset.setDepartmentEntity(departmentFromDb);
                                    asset.setUserEntity(null);
                                    return assetEntityRepository.save(asset);
                                }))
                .map(asset -> {
                    AssetAreaDto assetAreaDtoResponse = IAssetMapper.INSTANCE.toAreaDto(asset);
                    assetAreaDtoResponse.setNameDepartment(asset.getDepartmentEntity().getName());
                    return new GeneralResponse<>(assetAreaDtoResponse, ASSET_UPDATE_SUCCESSFUL);
                });
    }

    private void setFieldsAsset(AssetEntity assetEntity, AssetDto assetDto) {
        assetEntity.setName(assetDto.getName());
        assetEntity.setDescription(assetDto.getDescription());
        assetEntity.setType(assetDto.getType());
        assetEntity.setSerial(assetDto.getSerial());
        assetEntity.setNumInventory(assetDto.getNumInventory());
        assetEntity.setWeightKg(assetDto.getWeightKg());
        assetEntity.setHighCm(assetDto.getHighCm());
        assetEntity.setWidthCm(assetDto.getWidthCm());
        assetEntity.setLongAssetCm(assetDto.getLongAssetCm());
        assetEntity.setPurchaseValue(assetDto.getPurchaseValue());
    }

    @Override
    public GeneralResponse<List<AssetEntity>> get(AssetDto assetDto) {
        if (!assetEntityRepository.existsBySerial(assetDto.getSerial())) {
            throw new RuntimeException(ASSET_NOT_FOUND);
        }
        List<AssetEntity> assetEntities = assetEntityRepository.findByTypeOrPurchaseDateOrSerial(assetDto.getType(), assetDto.getPurchaseDate(),
                assetDto.getSerial());
        return new GeneralResponse<>(assetEntities, ASSET_FOUND);
    }

}
