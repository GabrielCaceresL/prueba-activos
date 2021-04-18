package com.test.mappers;

import com.test.api.request.CityDto;
import com.test.entity.CityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICityMapper {

    ICityMapper INSTANCE = Mappers.getMapper(ICityMapper.class);

    CityDto toDto(CityEntity cityEntity);

    @InheritInverseConfiguration
    CityEntity toCityEntity(CityDto cityDto);


}
