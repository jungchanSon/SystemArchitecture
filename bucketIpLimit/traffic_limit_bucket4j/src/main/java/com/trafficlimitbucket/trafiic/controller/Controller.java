package com.trafficlimitbucket.trafiic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    int number = 1;

    @GetMapping("/number")
    public int number() {

        return number++;
    }
}
