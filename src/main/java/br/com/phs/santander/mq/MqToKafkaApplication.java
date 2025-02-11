package br.com.phs.santander.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqToKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqToKafkaApplication.class, args);
	}

}
