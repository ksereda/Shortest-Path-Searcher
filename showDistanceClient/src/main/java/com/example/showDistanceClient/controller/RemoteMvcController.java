package com.example.showDistanceClient.controller;

import com.example.showDistanceClient.model.Bucket;
import com.example.showDistanceClient.model.EmployeeEntity;
import com.example.showDistanceClient.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class RemoteMvcController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment env;

    @Autowired
    private TestService service;

    @GetMapping("/data")
    public String data(){
        return service.data();
    }

    // Using RestTemplate (REST)
    @RequestMapping("/dataWithRestTemplate")
    public Bucket getAllDataWithRestTemplate() {
        Bucket bucket = new Bucket();

        // get list of available data
        List<EmployeeEntity> objects = restTemplate.getForObject("http://add-service/show/", List.class);
        bucket.setListEmployees(objects);

        return bucket;
    }

}
