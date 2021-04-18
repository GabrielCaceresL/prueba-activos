package com.test.services;

import com.test.api.request.CityDto;
import com.test.api.response.GeneralResponse;

import java.util.Optional;

public interface ICityService {

    Optional<GeneralResponse<CityDto>> save(CityDto cityDto);

    Optional<GeneralResponse<CityDto>> update(CityDto userDto, Long id);

    Optional<GeneralResponse<CityDto>> get(Long id);
}
