package com.example.kafkaavrodemo;

import com.example.kafkaavrodemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    public void send(Employee employee){
        kafkaTemplate.send("employee", String.valueOf(employee.getId()), employee);
    }

}
