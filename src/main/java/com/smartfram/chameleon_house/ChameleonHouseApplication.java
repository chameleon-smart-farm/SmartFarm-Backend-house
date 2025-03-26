package com.smartfram.chameleon_house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.smartfram.chameleon_house.domain.*.dao")
public class ChameleonHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChameleonHouseApplication.class, args);
	}

}
