package com.mariworld.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
class SampleController {

	@GetMapping("/rest/{index}")
	@Async
	public String rest(@PathVariable("index")Long index) throws InterruptedException {
		log.info("rest {}", index);
		Thread.sleep(1000);
		return "rest "+ index;
	}
}
@RestController
class SampleControllerV2{
	@GetMapping("/rest/{hello}/v2/{spring}")
	public String restV2(@PathVariable("hello") String hello,
						 @PathVariable("spring") String spring){
		return hello + spring ;
	}
}

@SpringBootApplication
//@EnableAsync
public class AsyncStudyApplication {

	public static void main(String[] args) {
		//System.setProperty("server.tomcat.threads.max","1");
		SpringApplication.run(AsyncStudyApplication.class, args);
	}

}
