package com.test.mappers;

import com.test.api.request.UserDto;
import com.test.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(source = "numDocument", target = "basicUserData.numDocument")
    @Mapping(source = "firstName", target = "basicUserData.firstName")
    @Mapping(source = "secondName", target = "basicUserData.secondName")
    @Mapping(source = "firstSurname", target = "basicUserData.firstSurname")
    @Mapping(source = "secondSurname", target = "basicUserData.secondSurname")
    @Mapping(source = "birthday", target = "basicUserData.birthday")
    UserDto toDto(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserDto userDto);


}
