package com.example.kafkaavrodemo;

import com.example.kafkaavrodemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private Producer producer;

    @PostMapping(path = "employee")
    public void create(@RequestBody Employee employee){
        producer.send(employee);
    }

}
