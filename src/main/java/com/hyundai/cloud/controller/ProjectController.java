package com.hyundai.cloud.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @GetMapping("/")
    public String getProject(@AuthenticationPrincipal String userEmail){
        return "로그인된 사용자는 " + userEmail + "입니다. ";
    }
}