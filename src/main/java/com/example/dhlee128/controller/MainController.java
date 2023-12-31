package com.example.dhlee128.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

}
