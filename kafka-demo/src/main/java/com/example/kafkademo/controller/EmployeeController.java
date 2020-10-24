package com.example.kafkademo.controller;

import com.example.kafkademo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @PostMapping(path = "employee")
    public void createEmployee(@RequestBody Employee employee){
        logger.info("Received employee:{}", employee);
        kafkaTemplate.send("employee", employee.getId().toString(), employee);
    }

}
