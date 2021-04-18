package com.test.services;

import com.test.api.request.DepartmentDto;
import com.test.api.response.GeneralResponse;

import java.util.Optional;

public interface IDepartmentService {

    Optional<GeneralResponse<DepartmentDto>> save(DepartmentDto departmentDto);

    Optional<GeneralResponse<DepartmentDto>> update(DepartmentDto departmentDto);

    Optional<GeneralResponse<DepartmentDto>> get(String nameDepartment);

}
