package com.test.services;

import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.UserEntity;
import com.test.mappers.IUserMapper;
import com.test.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.test.enums.ConstantProvider.*;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserEntityRepository userEntityRepository;

    public UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<UserDto>> save(UserDto userDto) {
        UserEntity userEntity = IUserMapper.INSTANCE.toUserEntity(userDto);
        log.info("UserEntity -> {}", userEntity.toString());
        userEntityRepository.findByNumDocument(userEntity.getNumDocument())
                .ifPresent(userEntity1 -> {
                    throw new RuntimeException(USER_EXISTS);
                });
        UserEntity storedEntity = userEntityRepository.save(userEntity);
        log.info("storedEntity -> {}", storedEntity.toString());
        return Optional.ofNullable(storedEntity)
                .map(IUserMapper.INSTANCE::toDto)
                .map(dto -> new GeneralResponse<>(dto, USER_SAVE_SUCCESSFUL));
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<UserDto>> update(UserDto userDto) {
        UserEntity userEntity = IUserMapper.INSTANCE.toUserEntity(userDto);
        if (userEntityRepository.existsByNumDocument(userEntity.getNumDocument())) {
            throw new RuntimeException(USER_NOT_EXIST);
        }
        return userEntityRepository
                .findByNumDocument(userEntity.getNumDocument())
                .map(userFromDb -> {
                    userFromDb.setFirstName(userEntity.getFirstName());
                    userFromDb.setSecondName(userEntity.getSecondName());
                    userFromDb.setFirstSurname(userEntity.getFirstSurname());
                    userFromDb.setNumDocument(userEntity.getNumDocument());
                    return userEntityRepository.save(userFromDb);
                })
                .map(storedUser -> new GeneralResponse<>(IUserMapper.INSTANCE.toDto(storedUser), USER_UPDATE_SUCCESSFUL));
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<UserDto>> get(String numDocument) {
        if (userEntityRepository.existsByNumDocument(numDocument)) {
            throw new RuntimeException(USER_NOT_FOUND);
        }
        return userEntityRepository
                .findByNumDocument(numDocument)
                .map(userEntity -> new GeneralResponse<>(IUserMapper.INSTANCE.toDto(userEntity), USER_FOUND));
    }

}
