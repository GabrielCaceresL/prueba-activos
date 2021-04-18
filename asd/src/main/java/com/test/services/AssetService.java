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

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
        AssetEntity assetEntityStored = userEntityRepository.findByNumDocument(assetUserDto.getUserEntity())
                .map(userFromDb -> {
                    assetEntity.setUserEntity(userFromDb);
                    return assetEntityRepository.save(assetEntity);
                }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        AssetUserDto assetResponse = IAssetMapper.INSTANCE.toUserDto(assetEntityStored);
        return Optional.of(new GeneralResponse<>(assetResponse, "Correcto Usuario---"));
    }

    @Override
    public Optional<GeneralResponse<AssetAreaDto>> save(AssetAreaDto assetAreaDto) {
        AssetEntity assetEntity = IAssetMapper.INSTANCE.toAssetEntity(assetAreaDto);
        DepartmentEntity departmentFromDb = departmentEntityRepository
                .findByName(assetAreaDto.getDepartmentEntity());
        if (departmentFromDb == null) {
            throw new RuntimeException("Departamento no encontrado");
        }
        assetEntity.setDepartmentEntity(departmentFromDb);
        AssetEntity assetAreaStored = assetEntityRepository.save(assetEntity);
        AssetAreaDto assetResponse = IAssetMapper.INSTANCE.toAreaDto(assetAreaStored);
        return Optional.ofNullable(new GeneralResponse<>(assetResponse, "Correcto Area---"));
    }

    @Override
    public Optional<GeneralResponse<AssetUserDto>> update(AssetUserDto assetUserDto) {
        return userEntityRepository
                .findByNumDocument(assetUserDto.getUserEntity())
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
                    assetUserDtoResponse.setUserEntity(asset.getUserEntity().getNumDocument());
                    return new GeneralResponse<>(assetUserDtoResponse, "USer Update ----------");
                });
    }

    @Override
    public Optional<GeneralResponse<AssetAreaDto>> update(AssetAreaDto assetAreaDto) {
        return Optional.ofNullable(departmentEntityRepository
                .findByName(assetAreaDto.getDepartmentEntity()))
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
                    assetAreaDtoResponse.setDepartmentEntity(asset.getDepartmentEntity().getName());
                    return new GeneralResponse<>(assetAreaDtoResponse, "Department Update ----------");
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
        List<AssetEntity> assetEntities = assetEntityRepository.findByTypeOrPurchaseDateOrSerial(assetDto.getType(), assetDto.getPurchaseDate(),
                assetDto.getSerial());
        return new GeneralResponse<>(assetEntities,"Assets founds");
    }

}
