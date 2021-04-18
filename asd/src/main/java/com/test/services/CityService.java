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
        CityEntity cityEntity = ICityMapper.INSTANCE.toCityEntity(cityDto);

        cityEntityRepository.findById(cityEntity.getId())
                .ifPresent(cityEntity1 -> {
                    throw new RuntimeException("La ciudad ya existe");
                });
        CityEntity storeCity = cityEntityRepository.save(cityEntity);

        return Optional.ofNullable(storeCity)
                .map(ICityMapper.INSTANCE::toDto)
                .map(dto -> new GeneralResponse<>(dto, CITY_SAVE_SUCCESSFUL));
    }

    @Override
    @Transactional
    public Optional<GeneralResponse<CityDto>> update(CityDto userDto, Long id) {
        CityEntity cityEntity = ICityMapper.INSTANCE.toCityEntity(userDto);
        log.info("userDto - > {}", userDto.toString());
        log.info("cityEntity - > {}", cityEntity.toString());
        return cityEntityRepository
                .findById(id)
                .map(cityFromDb -> {
                    cityFromDb.setName(cityEntity.getName());
                    return cityEntityRepository.save(cityFromDb);
                })
                .map(cityFromDb -> new GeneralResponse<>(ICityMapper.INSTANCE.toDto(cityFromDb), CITY_UPDATE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<CityDto>> get(Long id) {
        return cityEntityRepository
                .findById(id)
                .map(cityEntity -> new GeneralResponse<>(ICityMapper.INSTANCE.toDto(cityEntity), CITY_FOUND));
    }
}
