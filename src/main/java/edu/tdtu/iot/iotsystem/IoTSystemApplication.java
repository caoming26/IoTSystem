package edu.tdtu.iot.iotsystem;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IoTSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoTSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		// Tạo object và cấu hình
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
