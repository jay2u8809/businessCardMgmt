package com.jayian.businesscard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing        // JPA Auditing 활성화
@SpringBootApplication(scanBasePackages = {"com.jayian.businesscard"})
public class BusinesscardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinesscardApplication.class, args);
	}

}
