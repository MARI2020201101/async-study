package com.mariworld.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
class SampleController {

	@GetMapping("/rest/{index}")
	public String rest(@PathVariable("index")Long index) throws InterruptedException {
		log.info("rest {}", index);
		return "rest "+ index;
	}
}
@SpringBootApplication
public class AsyncStudyApplication {

	public static void main(String[] args) {

		System.setProperty("server.tomcat.max-threads" ,"1");
		SpringApplication.run(AsyncStudyApplication.class, args);
	}

}
