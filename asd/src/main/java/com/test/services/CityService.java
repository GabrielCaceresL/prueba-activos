package com.test.services;

import com.test.api.request.CityDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.CityEntity;
import com.test.mappers.ICityMapper;
import com.test.repository.CityEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.test.enums.ConstantProvider.*;

import java.util.Optional;

@Service
@Slf4j
public class CityService implements ICityService{

    private final CityEntityRepository cityEntityRepository;

    public CityService(CityEntityRepository cityEntityRepository) {
        this.cityEntityRepository = cityEntityRepository;
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<CityDto>> save(CityDto cityDto) {
        log.info("CityService.save cityDto -> {}",cityDto.toString());
        CityEntity cityEntity = ICityMapper.INSTANCE.toCityEntity(cityDto);
        if(cityEntityRepository.existsByName(cityEntity.getName())) {
            throw new RuntimeException(CITY_NAME_EXISTS);
        }
        if(cityEntityRepository.existsByCodeCity(cityEntity.getCodeCity())) {
            throw new RuntimeException(CITY_CODE_EXISTS);
        }
        CityEntity storeCity = cityEntityRepository.save(cityEntity);

        return Optional.ofNullable(storeCity)
                .map(ICityMapper.INSTANCE::toDto)
                .map(dto -> new GeneralResponse<>(dto, CITY_SAVE_SUCCESSFUL));
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<CityDto>> update(CityDto cityDto) {
        log.info("CityService.update cityDto -> {}",cityDto.toString());
        CityEntity cityEntity = ICityMapper.INSTANCE.toCityEntity(cityDto);
        if (!cityEntityRepository.existsByCodeCity(cityEntity.getCodeCity())) {
            throw new RuntimeException(CITY_NOT_EXISTS);
        }
        return cityEntityRepository
                .findByCodeCity(cityEntity.getCodeCity())
                .map(cityFromDb -> {
                    cityFromDb.setName(cityEntity.getName());
                    cityFromDb.setCodeCity(cityEntity.getCodeCity());
                    return cityEntityRepository.save(cityFromDb);
                })
                .map(cityFromDb -> new GeneralResponse<>(ICityMapper.INSTANCE.toDto(cityFromDb), CITY_UPDATE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<CityDto>> get(String codeCity) {
        log.info("CityService.get codeCity -> {}",codeCity);
        if (!cityEntityRepository.existsByCodeCity(codeCity)) {
            throw new RuntimeException(CITY_NOT_FOUND);
        }
        return cityEntityRepository
                .findByCodeCity(codeCity)
                .map(cityEntity -> new GeneralResponse<>(ICityMapper.INSTANCE.toDto(cityEntity), CITY_FOUND));
    }
}
