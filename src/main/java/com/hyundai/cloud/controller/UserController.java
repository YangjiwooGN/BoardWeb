package com.hyundai.cloud.controller;

import com.hyundai.cloud.dto.PatchUserResponseDto;
import com.hyundai.cloud.dto.ResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PatchMapping("/")
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserResponseDto requestBody,  @AuthenticationPrincipal String userEmail){
        return null;
    }
}

