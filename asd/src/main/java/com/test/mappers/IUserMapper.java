package com.test.mappers;

import com.test.api.request.UserDto;
import com.test.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDto toDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserDto userDto);


}
