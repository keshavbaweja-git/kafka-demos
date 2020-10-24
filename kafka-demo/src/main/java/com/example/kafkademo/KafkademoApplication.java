package com.example.kafkademo;

import com.example.kafkademo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
public class KafkademoApplication {

	private final Logger logger = LoggerFactory.getLogger(KafkademoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
	}

	@Bean
	public SeekToCurrentErrorHandler errorHandler(KafkaOperations<Object, Object> kafkaOperations) {
		return new SeekToCurrentErrorHandler(
				new DeadLetterPublishingRecoverer(kafkaOperations), new FixedBackOff(1000L, 2));
	}

	@Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}

	@KafkaListener(id = "employeeLogger", topics = "employee")
	public void listen(Employee employee) {
		logger.info("Received: " + employee);
	}

	@KafkaListener(id = "employeeLogger.DLT", topics = "employeeTopic")
	public void dltListen(String in) {
		logger.info("Received from DLT: " + in);
	}

}
