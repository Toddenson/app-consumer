package com.anbooka.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class AppConsumerApplication {

	private final Logger logger = LoggerFactory.getLogger(AppConsumerApplication.class);

	private final TaskExecutor exec = new SimpleAsyncTaskExecutor();

	public static void main(String[] args) {
		SpringApplication.run(AppConsumerApplication.class, args);
	}

	@KafkaListener(id = "fooGroup", topics = "topic1")
	public void listen(String text) {
		logger.info("Received: " + text);

		this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
	}
}
