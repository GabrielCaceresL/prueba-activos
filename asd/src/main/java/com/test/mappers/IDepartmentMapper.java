package com.test.mappers;

import com.test.api.request.DepartmentDto;
import com.test.entity.DepartmentEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ICityMapper.class},
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface IDepartmentMapper {

    IDepartmentMapper INSTANCE = Mappers.getMapper(IDepartmentMapper.class);

    @Mapping(target = "cityEntities", ignore = true)
    DepartmentDto toDto(DepartmentEntity departmentEntity);

    @InheritInverseConfiguration
    @Mapping(target = "cityEntities", ignore = true)
    DepartmentEntity toDepartmentEntity(DepartmentDto departmentDto);


}
