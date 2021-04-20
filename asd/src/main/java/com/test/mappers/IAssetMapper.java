package com.test.mappers;

import com.test.api.request.AssetAreaDto;
import com.test.api.request.AssetDto;
import com.test.api.request.AssetUserDto;
import com.test.entity.AssetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {IDepartmentMapper.class, IUserMapper.class})
public interface IAssetMapper {

    IAssetMapper INSTANCE = Mappers.getMapper(IAssetMapper.class);

    @Mapping(source = "userEntity.numDocument", target = "documentUser")
    AssetUserDto toUserDto(AssetEntity assetEntity);

    @Mapping(source = "departmentEntity.name", target = "nameDepartment")
    AssetAreaDto toAreaDto(AssetEntity assetEntity);

    @InheritInverseConfiguration
    AssetEntity toAssetEntity(AssetUserDto assetUserDto);

    @InheritInverseConfiguration
    AssetEntity toAssetEntity(AssetAreaDto assetAreaDto);

}
