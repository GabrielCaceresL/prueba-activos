package com.test.services;

import com.test.api.request.DepartmentDto;
import com.test.api.response.GeneralResponse;
import com.test.entity.CityEntity;
import com.test.entity.DepartmentEntity;
import com.test.mappers.IDepartmentMapper;
import com.test.repository.CityEntityRepository;
import com.test.repository.DepartmentEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static com.test.enums.ConstantProvider.*;


@Service
@Transactional
@Slf4j
public class DepartmentService implements IDepartmentService {

    private final DepartmentEntityRepository departmentEntityRepository;
    private final CityEntityRepository cityEntityRepository;

    public DepartmentService(DepartmentEntityRepository departmentEntityRepository, CityEntityRepository cityEntityRepository) {
        this.departmentEntityRepository = departmentEntityRepository;
        this.cityEntityRepository = cityEntityRepository;
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> save(DepartmentDto departmentDto) {
        log.info("DepartmentService.save departmentDto -> {}",departmentDto.toString());
        DepartmentEntity departmentEntity = new DepartmentEntity();
        if(departmentEntityRepository.existsByName(departmentDto.getName())){
            throw new RuntimeException(DEPARTMENT_EXISTS);
        }
        departmentEntity.setName(departmentDto.getName());
        DepartmentDto departmentResponse = getDepartmentDtoGeneralResponse(departmentDto,departmentEntity);
        return Optional.ofNullable(new GeneralResponse<>(departmentResponse, DEPARTMENT_SAVE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> update(DepartmentDto departmentDto) {
        log.info("DepartmentService.update departmentDto -> {}",departmentDto.toString());
        DepartmentEntity departmentEntity = departmentEntityRepository.findByName(departmentDto.getName());
        if (!departmentEntityRepository.existsByName(departmentDto.getName())) {
            throw new RuntimeException(DEPARTMENT_NOT_EXISTS);
        }
        DepartmentDto departmentResponse = getDepartmentDtoGeneralResponse(departmentDto,departmentEntity);
        return Optional.ofNullable(new GeneralResponse<>(departmentResponse, DEPARTMENT_UPDATE_SUCCESSFUL));
    }

    @Override
    public Optional<GeneralResponse<DepartmentDto>> get(String name) {
        log.info("DepartmentService.get name -> {}",name);
        if (!departmentEntityRepository.existsByName(name)) {
            throw new RuntimeException(DEPARTMENT_NOT_FOUND);
        }
        DepartmentEntity departmentEntity = departmentEntityRepository.findByName(name);
        DepartmentDto departmentDto = IDepartmentMapper.INSTANCE.toDto(departmentEntity);
        departmentDto.setCityEntities(departmentEntity.nameCities());
        return Optional.ofNullable(new GeneralResponse<>(departmentDto, DEPARTMENT_FOUND));
    }

    private DepartmentDto getDepartmentDtoGeneralResponse(DepartmentDto departmentDto, DepartmentEntity departmentEntity) {
        Set<CityEntity> cities = cityEntityRepository.findAllByNameIn(departmentDto.getCityEntities());
        departmentDto.getCityEntities().forEach(city -> {
            if(!cityEntityRepository.existsByName(city)){
                throw new RuntimeException(CITY_NOT_EXISTS+": "+ city);
            }
        });
        departmentEntity.setCityEntities(cities);
        DepartmentEntity departmentEntity1 = departmentEntityRepository.save(departmentEntity);
        DepartmentDto departmentDtoResponse = IDepartmentMapper.INSTANCE
                .toDto(departmentEntityRepository.save(departmentEntity));
        departmentDtoResponse.setCityEntities(departmentEntity1.nameCities());
        return departmentDtoResponse;
    }


}
