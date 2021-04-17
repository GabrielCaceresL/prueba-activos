package com.test.services;

import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.UserEntity;
import com.test.mappers.IUserMapper;
import com.test.repository.UserEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.test.enums.ConstantProvider.USER_SAVE_SUCCESSFUL;

@Service
public class UserService implements IUserService{

    private final UserEntityRepository userEntityRepository;

    public UserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<UserDto>> save(UserDto userDto) {
        UserEntity userEntity = IUserMapper.INSTANCE.toUserEntity(userDto);
        userEntityRepository.findByNumDocument(userEntity.getNumDocument()).orElseThrow(() -> new RuntimeException("El usuario ya existe"));
        UserEntity storedEntity = userEntityRepository.save(userEntity);
        return Optional.ofNullable(storedEntity)
                .map(IUserMapper.INSTANCE::toDto)
                .map(dto -> new GeneralResponse<>(dto, USER_SAVE_SUCCESSFUL));
    }


}
