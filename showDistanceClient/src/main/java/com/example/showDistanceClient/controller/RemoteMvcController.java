package com.example.showDistanceClient.controller;

import com.example.showDistanceClient.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteMvcController {

    @Autowired
    private TestService service;

    @GetMapping("/data")
    public String data(){
        return service.data();
    }

}
