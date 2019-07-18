package com.example.addDistanceClient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.addDistanceClient.exception.DeleteTransactionException;
import com.example.addDistanceClient.exception.RecordNotFoundException;
import com.example.addDistanceClient.model.EmployeeEntity;
import com.example.addDistanceClient.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> result = (List<EmployeeEntity>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }

    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }

    }

    @Transactional
    public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) {

        if (entity.getId()  == null) {
            entity = repository.save(entity);

            return entity;
        } else {

            Optional<EmployeeEntity> employee = repository.findById(entity.getId());

            if (employee.isPresent()) {

                EmployeeEntity newEntity = employee.get();
                newEntity.setDistance(entity.getDistance());
                newEntity.setCity_from(entity.getCity_from());
                newEntity.setCity_to(entity.getCity_to());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = DeleteTransactionException.class)
    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

}
