package com.test.controller;

import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;
import com.test.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping()
    public ResponseEntity<Optional<GeneralResponse<UserDto>>> save(@RequestBody UserDto body){

         return null;//ResponseEntity.ok(null);
    }


}
