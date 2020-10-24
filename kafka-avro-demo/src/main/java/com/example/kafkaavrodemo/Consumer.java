package com.example.kafkaavrodemo;

import com.example.kafkaavrodemo.model.Employee;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "employee")
    public void consume(ConsumerRecord<String, Employee> record){
        logger.info("Consumed:{}", record.value());
    }

}
