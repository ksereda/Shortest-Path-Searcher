package com.example.showDistanceClient.service;

import com.example.showDistanceClient.model.EmployeeEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Fallback implements ServiceFeignClient {

    @Override
    public List<EmployeeEntity> getAllEmployeesList() {
        return new ArrayList<>();
    }

}
