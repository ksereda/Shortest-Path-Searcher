package com.example.addDistanceClient.controller;

import com.example.addDistanceClient.model.EmployeeEntity;
import com.example.addDistanceClient.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/")
@RestController
public class RemoteMvcController {

    @Autowired
    EmployeeService service;

    @GetMapping(path = "/show")
    public List<EmployeeEntity> getAllEmployeesList() {
        return service.getAllEmployees();
    }

}
