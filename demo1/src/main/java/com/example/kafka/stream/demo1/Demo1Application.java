package com.example.kafka.stream.demo1;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Slf4j
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Bean
	public Consumer<KStream<String, String>> process() {
		return input ->
				input.foreach((key, value) -> {
					log.info("Key: " + key + " Value: " + value);
				});
	}
}
