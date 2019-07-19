package com.example.showDistanceClient.service;

import com.example.showDistanceClient.model.EmployeeEntity;
import com.example.showDistanceClient.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }

    }

    public EmployeeEntity[] convertListToArray(List<EmployeeEntity> list) {
        return list.toArray(new EmployeeEntity[list.size()]);
    }

    public List deikstraAlgorithmStart(EmployeeEntity[] GRAPH, String city_from, String city_to) {
        List resultList;
        Graph g = new Graph(GRAPH);
        g.deikstra(city_from);

        resultList = g.printPath(city_from, city_to);
        return resultList;
    }

}
