package com.test.services;

import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;

import java.util.Optional;

public interface IUserService {
    Optional<GeneralResponse<UserDto>> save(UserDto userDto);
    Optional<GeneralResponse<UserDto>> update(UserDto userDto);
    Optional<GeneralResponse<UserDto>> get(String numDocument);

}
