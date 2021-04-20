package com.test.controller;

import com.test.api.request.UserDto;
import com.test.api.response.GeneralResponse;
import com.test.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    public ResponseEntity<Optional<GeneralResponse<UserDto>>> save(@Valid @RequestBody UserDto body){
        log.info("Body - > {}", body.toString());
         return ResponseEntity.ok(iUserService.save(body));
    }

    @PutMapping
    public ResponseEntity<Optional<GeneralResponse<UserDto>>> update(@Valid @RequestBody UserDto body){
        return ResponseEntity.ok(iUserService.update(body));
    }

    @GetMapping
    public ResponseEntity<Optional<GeneralResponse<UserDto>>> get(@PathParam("numDoc") String numDoc){
        return ResponseEntity.ok(iUserService.get(numDoc));
    }

}
