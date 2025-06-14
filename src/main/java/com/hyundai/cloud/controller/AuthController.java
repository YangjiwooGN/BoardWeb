package com.hyundai.cloud.controller;

import com.hyundai.cloud.dto.ResponseDto;
import com.hyundai.cloud.dto.SignInDto;
import com.hyundai.cloud.dto.SignInResponseDto;
import com.hyundai.cloud.dto.SignUpDto;
import com.hyundai.cloud.entity.UserEntity;
import com.hyundai.cloud.security.TokenProvider;
import com.hyundai.cloud.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hyundai.cloud.document.UserDocument;


import java.io.Console;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    TokenProvider tokenProvider;
    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody){
        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
        return result;
    }

    @PostMapping("/checkNickname")
    public ResponseEntity<?> checkNickname(@RequestBody Map<String, String> request) {
        boolean isDuplicate = authService.isNicknameDuplicate(request.get("userNickname"));
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyPassword")
    public ResponseEntity<?> verifyPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Token is missing or empty");
        }
        String jwtToken = token.contains(" ") ? token.split(" ")[1] : token;
        String userEmail = tokenProvider.getEmailFromToken(jwtToken);
        String userPassword = request.get("password");
        boolean isValid = authService.verifyUserPassword(userEmail, userPassword);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isValid", isValid);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwtToken = token.split(" ")[1];
        String userEmail = tokenProvider.getEmailFromToken(jwtToken);
        UserDocument user = authService.getUserByEmail(userEmail);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/updateUserInfo")
    public ResponseDto<?> updateUserInfo(@RequestBody UserEntity updatedInfo, @RequestHeader String Authorization) {
        String token = Authorization.split(" ")[1];
        String userEmail = tokenProvider.getEmailFromToken(token);
        return authService.updateUserInfo(userEmail, updatedInfo);
    }

    @GetMapping("/currentUserName")
    public ResponseEntity<?> getCurrentUserName(@RequestHeader("Authorization") String token) {
        String jwtToken = token.split(" ")[1];
        String userEmail = tokenProvider.getEmailFromToken(jwtToken);
        UserDocument user = authService.getUserByEmail(userEmail);
        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("name", user.getUserName());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


}

