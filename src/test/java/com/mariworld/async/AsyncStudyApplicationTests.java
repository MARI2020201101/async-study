package com.mariworld.async;

import netscape.javascript.JSObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class AsyncStudyApplicationTests {

	private static final RestTemplate restTemplate = new RestTemplate();
	public static final String URL = "http://localhost:8080/rest/{hello}/v2/{spring}";
	/*@BeforeAll
	public static void getRestTemplate(){
		final RestTemplate restTemplate = new RestTemplate();
	}*/

	@Test
	void contextLoads() {
	}

	@Test
	public void stringObjectTest() throws Exception {

		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			String url = "http://localhost:8080/rest";
			url+="/hello";
			url+="/v2";
			url+="/spring";
			ResponseEntity<JSObject> result = restTemplate.getForEntity(url, JSObject.class);
			System.out.println(result);
		}
		long end = System.nanoTime();
		System.out.println("==================\n" +(end-start));
	}

	@Test
	public void stringReplaceObjectTest() throws Exception {
		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			URL.replace("{hello}","hello");
			URL.replace("{spring}","spring");
			ResponseEntity<JSObject> result = restTemplate.getForEntity(URL, JSObject.class);
			System.out.println(result);
		}
		long end = System.nanoTime();
		System.out.println("==================\n" +(end-start));
	}
}
